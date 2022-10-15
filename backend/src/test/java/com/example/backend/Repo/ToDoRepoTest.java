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
    void listReturnEqualWithRepoList() {
        //GIVEN
        ToDo testToDo = new ToDo("testDescription", ToDoStatus.OPEN,"1");
        //WHEN
        List<ToDo> actual =  new ToDoRepo(List.of(testToDo)).listToDo();
        List<ToDo> expected = new ArrayList<>(List.of(testToDo));
        //THEN
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Check if a ToDo is added to Repo by using toString match")
    void addToDoToRepo() {
        //GIVEN
        ToDo testToDo = new ToDo("testDescription", ToDoStatus.OPEN,"1");
        ToDoRepo testRepo = new ToDoRepo();
        //WHEN
        testRepo.addToDoToRepo(testToDo);
        String actual = testRepo.toString();
        String expected = "ToDoRepo(toDoRepo=[ToDo(description=testDescription, status=OPEN, id=1)])";
        //THEN
        assertEquals(expected,actual);
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