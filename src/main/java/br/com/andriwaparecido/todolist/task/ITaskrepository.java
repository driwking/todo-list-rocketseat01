package br.com.andriwaparecido.todolist.task;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskrepository extends JpaRepository<TaskModel, UUID>{
    List<TaskModel> findByIdUser(UUID idUser);
    // TaskModel findByIdAndIdUser(UUDI id, UUID iduser);
}
