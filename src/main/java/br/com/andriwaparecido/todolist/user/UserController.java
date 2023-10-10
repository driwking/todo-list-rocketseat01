package br.com.andriwaparecido.todolist.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/users")
    public void read(){
        System.out.println("Funcionou");
    }

    @PostMapping("/")
    public void create(@RequestBody UserModel userModel){
        System.out.println(userModel.name);
    }

}
