package com.example.FinalProjectBootcamp.Controller;

import com.example.FinalProjectBootcamp.DTO.ProjectDTO;
import com.example.FinalProjectBootcamp.Entities.Project;
import com.example.FinalProjectBootcamp.Entities.Task;
import com.example.FinalProjectBootcamp.Entities.TaskStatus;
import com.example.FinalProjectBootcamp.Repositories.ProjectRepository;
import com.example.FinalProjectBootcamp.Service.ProjectService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {
    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProject() {
        Project testProject = new Project();
        when(projectService.createProject(testProject)).thenReturn(new ResponseEntity<>(testProject, HttpStatus.CREATED));
        ResponseEntity<Project> responseEntity = projectController.createProject(testProject);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testProject, responseEntity.getBody());
    }

    @Test
    public void testCreateProjectNotFound() {
        Project testProject = new Project();
        when(projectService.createProject(testProject)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        ResponseEntity<Project> responseEntity = projectController.createProject(testProject);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testEditProject() {
        UUID idTested = UUID.randomUUID();
        Project updatedProject = new Project();
        when(projectService.editProject(idTested,updatedProject)).thenReturn(new ResponseEntity<>(updatedProject,HttpStatus.OK));
        ResponseEntity responseEntity = projectController.editProject(idTested, updatedProject);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedProject, responseEntity.getBody());

    }
    @Test
    public void testEditProjectNotFound() {
        UUID idTested = UUID.randomUUID();
        Project testProject = new Project();
        when(projectService.editProject(idTested, testProject)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        ResponseEntity responseEntity = projectController.editProject(idTested, testProject);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
    @Test
    void testDeleteProject() {
        UUID idTested = UUID.randomUUID();
        when(projectService.deleteProject(idTested)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity responseEntity = projectController.deleteProject(idTested);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteProjectNotFound() {
        UUID idTested = UUID.randomUUID();
        when(projectService.deleteProject(idTested)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        ResponseEntity responseEntity = projectController.deleteProject(idTested);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void searchProject() {
        UUID idTested = UUID.randomUUID();
        Project testProject = new Project();
        when(projectService.searchProject(idTested)).thenReturn(Optional.of(testProject));
        Optional<Project> result = projectController.searchProject(idTested);
        assertEquals(testProject, result.orElse(null));
    }

    @Test
    public void testSearchProjectNotFound() {
        UUID idTested = UUID.randomUUID();
        when(projectService.searchProject(idTested)).thenReturn(Optional.empty());
        Optional<Project> result = projectController.searchProject(idTested);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetProjectsList() {
        int page = 0;
        int size = 10;
        List<ProjectDTO> testProjects = new ArrayList<>();
        Page<ProjectDTO> testPage = new PageImpl<>(testProjects, PageRequest.of(page, size), testProjects.size());
        when(projectService.getProjectsList(page, size)).thenReturn(testPage);
        Page<ProjectDTO> result = projectController.getProjectsList(page, size);
        assertEquals(testPage, result);
    }

    @Test
    void testAddTask() {
        UUID idTested = UUID.randomUUID();
        Task testTask = new Task();
        when(projectService.addTask(idTested, testTask)).thenReturn(new ResponseEntity<>(testTask, HttpStatus.CREATED));
        ResponseEntity<Task> responseEntity = projectController.addTask(idTested, testTask);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testTask, responseEntity.getBody());
    }

    @Test
    public void testAddTaskNotFound() {
        UUID idTested = UUID.randomUUID();
        Task testTask = new Task();
        when(projectService.addTask(idTested, testTask)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        ResponseEntity<Task> responseEntity = projectController.addTask(idTested, testTask);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testGetTasksByDueDate() {
        UUID idTested = UUID.randomUUID();
        List<Task> testTasks = new ArrayList<>();
        when(projectService.getTasksByDueDate(idTested)).thenReturn(testTasks);
        List<Task> result = projectController.getTasksByDueDate(idTested);
        assertEquals(testTasks, result);
    }

    @Test
    void testGetTaskByProjectID() {
        UUID idTested = UUID.randomUUID();
        Map<TaskStatus, List<Task>> testTaskMap = new HashMap<>();
        ResponseEntity<Map<TaskStatus, List<Task>>> testResponseEntity =
                new ResponseEntity<>(testTaskMap, HttpStatus.OK);
        when(projectService.getTaskByProjectID(idTested)).thenReturn(testResponseEntity);
        ResponseEntity<Map<TaskStatus, List<Task>>> responseEntity = projectController.getTaskByProjectID(idTested);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testTaskMap, responseEntity.getBody());
    }
}