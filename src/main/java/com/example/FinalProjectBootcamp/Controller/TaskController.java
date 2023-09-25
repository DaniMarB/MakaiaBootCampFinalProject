package com.example.FinalProjectBootcamp.Controller;

import com.example.FinalProjectBootcamp.Entities.Project;
import com.example.FinalProjectBootcamp.Entities.Task;
import com.example.FinalProjectBootcamp.Entities.TaskStatus;
import com.example.FinalProjectBootcamp.Service.TaskService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.JsonPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("vi/tasks")
public class TaskController {
    @Autowired
    TaskService service = new TaskService();

    @PatchMapping ("/{id}")
    public ResponseEntity updateTaskStatus(@PathVariable UUID id, @RequestBody String status){
        return service.updateTasksStatus(id,status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTask(@PathVariable UUID id){
        return service.deleteTask(id);
    }

    @GetMapping("{id}")
    public Optional<Task> searchTask(@PathVariable UUID id){
        return service.getTask(id);
    }
}
