package br.com.andriwaparecido.todolist.user;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserrepository  extends JpaRepository<UserModel, UUID>{
    UserModel findByusername(String userModel);
}
