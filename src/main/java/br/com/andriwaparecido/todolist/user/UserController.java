package br.com.andriwaparecido.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserrepository userrepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){

        var user = this.userrepository.findByusername(userModel.getUsername());
        if(user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuário já cadastrado");           
        }

        var hashpassword = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(hashpassword);
        
        var createdeUser =  this.userrepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(createdeUser);
    }

}
