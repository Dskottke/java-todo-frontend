package com.example.backend.Controller;

import com.example.backend.Model.ToDo;
import com.example.backend.Model.ToDoStatus;
import com.example.backend.Repo.ToDoRepo;
import com.example.backend.Service.ToDoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ToDoRepo toDoRepo;
    @Autowired
    private ToDoService toDoService;

    @Test
    @DirtiesContext
    @DisplayName("Request should response 200ok and JSON should match with expected")
    void getAllToDos() throws Exception {

        //GIVEN
        toDoRepo.addToDoToRepo(new ToDo("test",ToDoStatus.OPEN,"1"));


        //WHEN
        String expected = """
                        [{"description":"test","status":"OPEN","id":"1"}]
                        """;


        mockMvc.perform(MockMvcRequestBuilders.get(("/api/todo")))

        //THEN
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void addToDo() {
    }

    @Test
    void changeStatusOfToDo() {
    }

    @Test
    void deleteToDo() {
    }

    @Test
    void editToDo() {
    }
}