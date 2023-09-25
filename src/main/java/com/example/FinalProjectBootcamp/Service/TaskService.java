package com.example.FinalProjectBootcamp.Service;

import com.example.FinalProjectBootcamp.Entities.Project;
import com.example.FinalProjectBootcamp.Entities.Task;
import com.example.FinalProjectBootcamp.Entities.TaskStatus;
import com.example.FinalProjectBootcamp.Repositories.ProjectRepository;
import com.example.FinalProjectBootcamp.Repositories.TaskRepository;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    ArrayList<Task> tasksList = new ArrayList<>();

    public void deleteTask (UUID id){
        Task taskFound = taskRepository.getReferenceById(id);
        if(!taskRepository.findById(id).isEmpty()){
            taskRepository.delete(taskFound);
        }
    }

    public Optional<Task> getTask(UUID id){
        return taskRepository.findById(id);
    }


    public ResponseEntity updateTasksStatus(UUID id, String status){
        Task taskFound = taskRepository.findById(id).orElse(null);
        if(status.equals("TODO")||status.equals("INPROGRESS")||status.equals("BLOCKED")||status.equals("DONE")){
            if(taskFound != null){
                if(taskFound.getStatus().equals(TaskStatus.TODO)){
                    if(TaskStatus.valueOf(status).equals(TaskStatus.INPROGRESS)) {
                        taskFound.setStatus(TaskStatus.valueOf(status));
                        taskFound.setLastUpdateDate(LocalDateTime.now());
                        taskRepository.save(taskFound);
                        return ResponseEntity.ok(taskFound);
                    }else{return new ResponseEntity<>("No es posible asignar al estado "+status.toString()+" una tarea con estado "+taskFound.getStatus().toString(),HttpStatus.BAD_REQUEST);}
                }else if(taskFound.getStatus().equals(TaskStatus.INPROGRESS)){
                    if(TaskStatus.valueOf(status).equals(TaskStatus.BLOCKED)||status.equals(TaskStatus.DONE)) {
                        taskFound.setStatus(TaskStatus.valueOf(status));
                        taskFound.setLastUpdateDate(LocalDateTime.now());
                        taskRepository.save(taskFound);
                        return ResponseEntity.ok(taskFound);
                    }else{return new ResponseEntity<>("No es posible asignar al estado "+status.toString()+" una tarea con estado "+taskFound.getStatus().toString(),HttpStatus.BAD_REQUEST);}
                }else if (taskFound.getStatus().equals(TaskStatus.BLOCKED)) {
                    if(TaskStatus.valueOf(status).equals(TaskStatus.INPROGRESS)||status.equals(TaskStatus.DONE)) {
                        taskFound.setStatus(TaskStatus.valueOf(status));
                        taskFound.setLastUpdateDate(LocalDateTime.now());
                        taskRepository.save(taskFound);
                        return ResponseEntity.ok(taskFound);
                    }else{return new ResponseEntity<>("No es posible asignar al estado "+status.toString()+" una tarea con estado "+taskFound.getStatus().toString(),HttpStatus.BAD_REQUEST);}
                }
            }
            return new ResponseEntity<>("Task wasn't found in the list",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("El estado "+ status + " no es valido",HttpStatus.BAD_REQUEST);

    }

}
