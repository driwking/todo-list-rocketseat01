package br.com.andriwaparecido.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.andriwaparecido.todolist.user.IUserrepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class FilterTaskAuth extends OncePerRequestFilter{
    
    @Autowired
    private IUserrepository userrepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
                    var servletPath = request.getServletPath();
                    System.out.println("PATH" + servletPath);

            if(servletPath.startsWith("/Task/")){
                //pegar autentificação user e senha
               var authorization = request.getHeader("Authorization");              
               var authEncoded = authorization.substring("Basic".length()).trim();

               byte[] authDecode = Base64.getDecoder().decode(authEncoded);

               var authString = new String(authDecode);

               String[] crendentials = authString.split(":");
               String username = crendentials[0];
               String password = crendentials[1];
               System.out.println("authorization");
               System.out.println(username);
               System.out.println(password);


                //validar user
                var user = this.userrepository.findByusername(username);
                if(user == null){
                    response.sendError(401);
                }else{
                    //validar senha
                    var passwordverify =  BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());                    
                    if(passwordverify.verified){
                        //continuar
                        request.setAttribute("idUser", user.getId());
                        filterChain.doFilter(request,response);

                    }else{
                        response.sendError(401);
                    }
                }
                
            }else{
                    System.out.println("users");
                    filterChain.doFilter(request,response);
            }

    }

 
    
}
