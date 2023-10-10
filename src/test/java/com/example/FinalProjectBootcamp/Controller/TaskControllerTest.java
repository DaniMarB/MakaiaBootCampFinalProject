package com.example.FinalProjectBootcamp.Controller;

import com.example.FinalProjectBootcamp.Entities.Task;
import com.example.FinalProjectBootcamp.Entities.TaskStatus;
import com.example.FinalProjectBootcamp.Repositories.TaskRepository;
import com.example.FinalProjectBootcamp.Service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;


    @Mock
    private TaskService taskService;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testUpdateTaskStatus() {
        UUID idTested = UUID.randomUUID();
        String testStatus = "DONE";
        when(taskService.updateTasksStatus(idTested, testStatus)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity responseEntity = taskController.updateTaskStatus(idTested, testStatus);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateTaskStatusIncorrect() {
        UUID idTested = UUID.randomUUID();
        String testStatus = "OTHER";
        when(taskService.updateTasksStatus(idTested, testStatus)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        ResponseEntity responseEntity = taskController.updateTaskStatus(idTested, testStatus);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


    @Test
    void testDeleteTask() {
        UUID idTested = UUID.randomUUID();
        when(taskService.deleteTask(idTested)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        ResponseEntity responseEntity = taskController.deleteTask(idTested);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteTaskNotFound() {
        UUID idTested = UUID.randomUUID();
        when(taskService.deleteTask(idTested)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        ResponseEntity responseEntity = taskController.deleteTask(idTested);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testSearchTask() {
        UUID idTested = UUID.randomUUID();
        Task testTask = new Task();
        testTask.setId(idTested);
        testTask.setName("Tarea de prueba");
        when(taskService.getTask(idTested)).thenReturn(Optional.of(testTask));
        Optional<Task> result = taskController.searchTask(idTested);
        assertEquals(testTask, result.orElse(null));
    }
}