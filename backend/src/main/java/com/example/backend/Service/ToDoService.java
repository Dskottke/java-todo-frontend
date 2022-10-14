package com.example.backend.Service;

import com.example.backend.Model.ToDo;
import com.example.backend.Repo.ToDoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ToDoService {

    private ToDoRepo toDoRepo;

    public ToDoService(ToDoRepo toDoRepo) {
        this.toDoRepo = toDoRepo;
    }


    public List<ToDo> getToDos() {
        return toDoRepo.listToDo();
    }

    public ToDo addNewToDo(ToDo todo) {
        String id = UUID.randomUUID().toString();
        todo.setId(id);
        return toDoRepo.addToDoToRepo(todo);
    }

    public ToDo changeToDoProperties(ToDo id) {return toDoRepo.changeToDoProperties(id);}

    public ToDo deleteToDoById(String id) {return toDoRepo.deleteToDoById(id);}

    public ToDo getToDoById(String id) {return toDoRepo.getToDoById(id);}


}
