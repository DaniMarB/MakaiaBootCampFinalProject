package com.example.FinalProjectBootcamp.Controller;


import com.example.FinalProjectBootcamp.Entities.Task;
import com.example.FinalProjectBootcamp.Service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("vi/tasks")
@Api(tags= "Task Controller", description = "Esta URL controla todos los endpoints relacionados con las tareas.")
public class TaskController {
    @Autowired
    TaskService service = new TaskService();

    @ApiOperation(value = "Actualizar estado de tarea", notes = "Se recibe por path el ID de la tarea a actualizar y por el body el nuevo estado de la tarea")
    @PatchMapping ("/{id}")
    public ResponseEntity updateTaskStatus(@PathVariable UUID id, @RequestBody String status){
        return service.updateTasksStatus(id,status);
    }

    @ApiOperation(value = "Eliminar una tarea", notes = "Se recibe por path el ID de la tarea a eliminar, si se encuentra el ID, se elimina la tarea")
    @DeleteMapping("{id}")
    public ResponseEntity deleteTask(@PathVariable UUID id){
        return service.deleteTask(id);
    }

    @ApiOperation(value = "Buscar tarea por ID", notes = "Se recibe por path el ID de la tarea a buscar, si lo encuentra devuelve el contenido de la tarea")
    @GetMapping("{id}")
    public Optional<Task> searchTask(@PathVariable UUID id){
        return service.getTask(id);
    }
}
