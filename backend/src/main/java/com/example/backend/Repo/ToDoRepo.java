package com.example.backend.Repo;

import com.example.backend.Model.ToDo;
import com.example.backend.Model.ToDoStatus;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Data
public class ToDoRepo {
    private final List<ToDo> toDoRepo;

    public ToDoRepo() {
        this.toDoRepo = new ArrayList<>();
        toDoRepo.add(new ToDo("description", ToDoStatus.OPEN,"1"));
    }

    public ToDoRepo(List<ToDo> toDoRepo) {
        this.toDoRepo = toDoRepo;
    }

    public List<ToDo> listToDo() {
        return toDoRepo;
    }

    public ToDo addToDoToRepo(ToDo todo) {
        toDoRepo.add(todo);
        return todo;
    }

    public ToDo getToDoByBody(ToDo toDoToFind) {

        return   toDoRepo.stream()
                .filter(toDo -> toDo.getId().equals(toDoToFind.getId()))
                .findFirst()
                .orElseThrow();

    }
    public ToDo getToDoById(String id) {

        return  toDoRepo.stream()
                .filter(toDo -> toDo.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
    public ToDo changeToDoProperties(ToDo update) {

        ToDo toDoToUpdate = getToDoByBody(update);

        toDoToUpdate.setDescription(update.getDescription());
        toDoToUpdate.setStatus(update.getStatus());

        return toDoToUpdate;
    }
    public ToDo deleteToDoById(String id) {

        ToDo toDoToFind = getToDoById(id);
        toDoRepo.remove(getToDoById(id));

        return toDoToFind;
    }

}
