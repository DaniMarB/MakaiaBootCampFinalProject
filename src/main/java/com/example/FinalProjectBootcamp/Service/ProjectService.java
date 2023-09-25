package com.example.FinalProjectBootcamp.Service;

import com.example.FinalProjectBootcamp.DTO.ProjectDTO;
import com.example.FinalProjectBootcamp.Entities.Project;
import com.example.FinalProjectBootcamp.Entities.Task;
import com.example.FinalProjectBootcamp.Entities.TaskStatus;
import com.example.FinalProjectBootcamp.Repositories.ProjectRepository;
import com.example.FinalProjectBootcamp.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;
    ArrayList<Project> projectsList = new ArrayList<>();

    public ResponseEntity<Project> createProject(Project nProject){
        projectRepository.save(nProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(nProject);
    }

    public ResponseEntity editProject(UUID id, Project updatedProject){
        Project projectFound = projectRepository.getReferenceById(id);
        if(projectFound != null){
            projectRepository.delete(projectFound);
            updatedProject.setLastUpdateDate(LocalDate.now());
            projectRepository.save(updatedProject);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedProject);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<Object> deleteProject(UUID id){
        Project projectFound = projectRepository.getReferenceById(id);
        if(projectFound != null){
            projectRepository.delete(projectFound);
            return ResponseEntity.ok("Project deleted");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public Optional<Project> searchProject(UUID id){
        return projectRepository.findById(id);
    }

    public Page<ProjectDTO> getProjectsList(int page, int size){
        PageRequest pageable = PageRequest.of(page,size);
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOS = new ArrayList<>();

        for(Project project : projects){
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setName(project.getName());
            projectDTO.setDescription(project.getDescription());
            projectDTO.setStatus(project.getStatus());
            projectDTO.setCreatedDate(project.getCreatedDate());
            projectDTOS.add(projectDTO);
        }
        return new PageImpl<>(projectDTOS,PageRequest.of(page,size),projects.size());


    }

    public List<Task> getTasksByDueDate(UUID id){
        Optional<Project> projectFound = projectRepository.findById(id);
        return projectFound.get().getTasks().stream().
                filter(t -> t.getDueDate().isBefore(LocalDate.now())).collect(Collectors.toList());
    }

    public ResponseEntity<Task> addTask(UUID id, Task task){
                Optional<Project> projectOptional = projectRepository.findById(id);
                if(!projectOptional.isPresent()){
                    return ResponseEntity.unprocessableEntity().build();
                }
                task.setProject(projectOptional.get());
                Task taskSaved = taskRepository.save(task);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(taskSaved.getId()).toUri();
                return ResponseEntity.created(location).body(taskSaved);
    }

    public ResponseEntity<Map<TaskStatus,List<Task>>> getTaskByProjectID(UUID id){
        Project projectFound = projectRepository.findById(id).orElse(null);
        if(projectFound == null){
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Project-ID", String.valueOf(projectFound.getId()));
        headers.add("Project-Name", projectFound.getName());
        Map<TaskStatus, List<Task>> tasksByStatus = projectFound.getTasks().stream()
                .collect(Collectors.groupingBy(Task::getStatus));

        return ResponseEntity.ok().headers(headers).body(tasksByStatus);

    }

}
