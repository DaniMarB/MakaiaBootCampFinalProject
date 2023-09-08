package com.example.FinalProjectBootcamp.Controller;

import com.example.FinalProjectBootcamp.Entities.Project;
import com.example.FinalProjectBootcamp.Service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("vi/projects")
public class ProjectController {

    ProjectService service = new ProjectService();

    @PostMapping()
    public String createProject(@RequestBody() Project nProject){
        return service.createProject(nProject);
    }
    @PutMapping("{id}")
    public void editProject(@PathVariable UUID id, @RequestBody()Project project){
        service.editProject(id, project);

    }
    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable("id")UUID id){
        service.deleteProject(id);
    }
    @GetMapping("{id}")
    public Optional<Project> searchProject(@PathVariable("id")UUID id){
        return service.searchProject(id);
    }
    @GetMapping("/ProjectList")
    public ArrayList<Project> getProjectsList(){
        return service.getProjectsList();
    }

}
