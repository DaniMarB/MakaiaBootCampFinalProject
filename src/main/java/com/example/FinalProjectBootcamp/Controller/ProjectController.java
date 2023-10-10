package com.example.FinalProjectBootcamp.Controller;

import com.example.FinalProjectBootcamp.DTO.ProjectDTO;
import com.example.FinalProjectBootcamp.Entities.Project;
import com.example.FinalProjectBootcamp.Entities.Task;
import com.example.FinalProjectBootcamp.Entities.TaskStatus;
import com.example.FinalProjectBootcamp.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("vi/projects")
public class ProjectController {

    @Autowired
    ProjectService service = new ProjectService();


    @PostMapping()
    public ResponseEntity<Project> createProject(@RequestBody() Project nProject){
        return service.createProject(nProject);
    }
    @PutMapping("{id}")
    public ResponseEntity editProject(@PathVariable UUID id, @RequestBody()Project project){
        return service.editProject(id, project);

    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteProject(@PathVariable("id")UUID id){
        return service.deleteProject(id);
    }
    @GetMapping("{id}")
    public Optional<Project> searchProject(@PathVariable("id")UUID id){
        return service.searchProject(id);
    }
    @GetMapping("/ProjectList")
    public Page<ProjectDTO> getProjectsList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10")int size) {

        return service.getProjectsList(page,size);
    }

    @PostMapping("{id}/tasks")
    public ResponseEntity<Task> addTask(@Valid @PathVariable("id")UUID id, @RequestBody Task nTask){
        return service.addTask(id,nTask);
    }

    @GetMapping("{id}/due-task")
    public List<Task> getTasksByDueDate(@PathVariable("id")UUID id){
        return service.getTasksByDueDate(id);
    }

    @GetMapping("{id}/board")
    public ResponseEntity<Map<TaskStatus,List<Task>>> getTaskByProjectID(@PathVariable("id")UUID id){
        return service.getTaskByProjectID(id);
    }


}
