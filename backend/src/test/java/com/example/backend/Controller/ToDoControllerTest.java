package com.example.backend.Controller;

import com.example.backend.Model.ToDo;
import com.example.backend.Model.ToDoStatus;
import com.example.backend.Repo.ToDoRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ToDoRepo toDoRepo;



    @Test
    @DirtiesContext
    @DisplayName("Request should response 200ok and JSON should match with expected")
    void getAllToDos() throws Exception {

        //GIVEN
        toDoRepo.addToDoToRepo(new ToDo("test", ToDoStatus.OPEN, "1"));


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
    @DisplayName("Request should response 200OK " +
            "after request the size of the toDoRepo List should be higher than before ")
    @DirtiesContext
    void addToDo() throws Exception {

        //GIVEN
        int before = toDoRepo.listToDo().size();

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post(("/api/todo"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                //THEN
                .andExpect(status().isOk());


        int after = toDoRepo.listToDo().size();

        assertThat(before).isLessThan(after);

    }

    @Test
    @DirtiesContext
    @DisplayName("the given Requestbody should change the status of the id matching todo inside the repolist" +
            "and should response the changed ToDo")
    void changeStatusOfToDo() throws Exception {

        toDoRepo.addToDoToRepo(new ToDo("test", ToDoStatus.OPEN, "1"));


        String expected = """
                 {
                        "description": "description",
                        "status": "IN_PROGRESS",
                        "id": "1"
                    }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(expected))

                .andExpect(status().isOk())
                .andExpect(content().json(expected));

        ToDoStatus actual = toDoRepo.getToDoById("1").getStatus();
        assertEquals(ToDoStatus.IN_PROGRESS, actual);

    }

    @Test
    @DirtiesContext
    @DisplayName("after deleting request the repolist should have less ToDos and the response " +
            "should be the deleted toDo")
    void deleteToDo() throws Exception {
        toDoRepo.addToDoToRepo(new ToDo("test", ToDoStatus.OPEN, "1"));

        int before = toDoRepo.listToDo().size();

        String expected = """
                {"description":"test","status":"OPEN","id":"1"}
                """;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{1}"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

        int after = toDoRepo.listToDo().size();

        assertThat(after).isLessThan(before);
    }

    @Test
    @DirtiesContext
    @DisplayName("Request should respone the right ToDo from Repo")
    void editToDo() throws Exception {
        toDoRepo.addToDoToRepo(new ToDo("test", ToDoStatus.OPEN, "1"));

        String expected = """
                {"description":"test","status":"OPEN","id":"1"}
                """;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{1}"))


                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }
}