package com.example.backend.Repo;

import com.example.backend.Model.ToDo;
import com.example.backend.Model.ToDoStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoRepoTest {

    @Test
    @DisplayName("the returned list should match with the expected ")
    void listToDo() {
        //GIVEN
        ToDoRepo testRepo = new ToDoRepo();
        ToDo testToDo = new ToDo("testDescription", ToDoStatus.OPEN,"1");
        testRepo.addToDoToRepo(testToDo);

        //WHEN
        List<ToDo> actual = testRepo.listToDo();
        List<ToDo> expected = new ArrayList<>(List.of(testToDo));

        //THEN
        assertEquals(expected,actual);
    }
    @Test
    void addToDoToRepo() {
    }

    @Test
    void getToDoByBody() {
    }

    @Test
    void getToDoById() {
    }

    @Test
    void changeToDoProperties() {
    }

    @Test
    void deleteToDoById() {
    }
}