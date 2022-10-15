package com.example.backend.Repo;

import com.example.backend.Model.ToDo;
import com.example.backend.Model.ToDoStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
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
    @DisplayName("the ToDoToFind should be equals with the ToDo return of the Method getToDoByBody ")
    void getToDoByBody() {
        //GIVEN
        List<ToDo> givenToDos = new ArrayList<>(List.of(
                new ToDo("testDescription1", ToDoStatus.OPEN,"1"),
                new ToDo("testDescription2", ToDoStatus.OPEN,"2"),
                new ToDo("testDescription3", ToDoStatus.OPEN,"3")
        ));
        ToDoRepo toDoRepo = new ToDoRepo(givenToDos);
        //WHEN
        ToDo actual = toDoRepo.getToDoByBody(givenToDos.get(1));
        ToDo expected = givenToDos.get(1);
        //THEN
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("the Method should return the ToDo with matching ID ")
    void getToDoById() {
        //GIVEN
        List<ToDo> givenToDos = new ArrayList<>(List.of(
                new ToDo("testDescription1", ToDoStatus.OPEN,"1"),
                new ToDo("testDescription2", ToDoStatus.OPEN,"2"),
                new ToDo("testDescription3", ToDoStatus.OPEN,"3")
        ));
        ToDoRepo toDoRepo = new ToDoRepo(givenToDos);
        //WHEN
        ToDo actual = toDoRepo.getToDoById("1");
        ToDo expected = givenToDos.get(0);
        //THEN
        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("The update ToDo's properties (description and status) " +
            "should update the Properties of the ID matching ToDo inside the repolist")
    void changeToDoProperties() {
        //GIVEN
        List<ToDo> givenToDos = new ArrayList<>(List.of(
                new ToDo("testDescription1", ToDoStatus.OPEN,"1"),
                new ToDo("testDescription2", ToDoStatus.OPEN,"2"),
                new ToDo("testDescription3", ToDoStatus.OPEN,"3")
        ));
        ToDoRepo toDoRepo = new ToDoRepo(givenToDos);
        ToDo update = new ToDo("update", ToDoStatus.DONE,"2");
        //WHEN
        ToDo actual = toDoRepo.changeToDoProperties(update);
        ToDo expected = update;
        //THEN
        assertEquals(actual,update);
    }

    @Test
    @DisplayName("after deleting a ToDo the Lists size should be less than before")
    void deleteToDoById() {
        //GIVEN

        List<ToDo> givenToDos = new ArrayList<>(List.of(
                new ToDo("testDescription1", ToDoStatus.OPEN,"1"),
                new ToDo("testDescription2", ToDoStatus.OPEN,"2"),
                new ToDo("testDescription3", ToDoStatus.OPEN,"3")));
        ToDoRepo toDoRepo = new ToDoRepo(givenToDos);

        int before = givenToDos.size();
        //WHEN
        toDoRepo.deleteToDoById("1");
        int after = givenToDos.size();
        //THEN
        assertThat(after).isLessThan(before);
    }
}