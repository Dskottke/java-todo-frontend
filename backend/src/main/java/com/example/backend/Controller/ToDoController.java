package com.example.backend.Controller;

import com.example.backend.Model.ToDo;
import com.example.backend.Service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")

public class ToDoController {

    private final ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    @GetMapping()
    public List<ToDo> getAllToDos() {

        return service.getToDos();
    }

    @PostMapping()
    public ToDo addToDo(@RequestBody ToDo todo) {
        return service.addNewToDo(todo);
    }

    @PutMapping("{id}")
    public ToDo changeStatusOfToDo(@RequestBody ToDo todo) {

        return service.changeToDoProperties(todo);
    }

    @DeleteMapping("{id}")
    public ToDo deleteToDo(@PathVariable String id) {
        return service.deleteToDoById(id);
    }

    @GetMapping("{id}")
    public ToDo editToDo(@PathVariable String id) {
        return service.getToDoById(id);

    }
}

