package br.com.andriwaparecido.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.repository.cdi.Eager;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_task")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private String priority;

    @CreationTimestamp
    private LocalDateTime createAt;
    private LocalDateTime startAt; // "yyyy-mm-ddTHH:mm:ss"
    private LocalDateTime endAt; // "yyyy-mm-ddTHH:mm:ss"

    private UUID idUser;
    
    public void setTitle(String title) throws Exception{
        if(title.length() > 50){
            throw  new Exception("O campo title deve conter no máximo 50 caracteres!");
        }
        this.title = title;
    }
}