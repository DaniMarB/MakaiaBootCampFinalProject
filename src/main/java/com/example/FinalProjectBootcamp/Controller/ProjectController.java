package com.example.FinalProjectBootcamp.Controller;

import com.example.FinalProjectBootcamp.DTO.ProjectDTO;
import com.example.FinalProjectBootcamp.Entities.Project;
import com.example.FinalProjectBootcamp.Entities.Task;
import com.example.FinalProjectBootcamp.Entities.TaskStatus;
import com.example.FinalProjectBootcamp.Service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Api(tags= "Project Controller", description = "Esta URL controla todos los endpoints relacionados con los proyectos.")
public class ProjectController {

    @Autowired
    ProjectService service = new ProjectService();


    @ApiOperation(value = "Crear un proyecto", notes = "Se recibe por el body un Proyecto y este se registra en la base de datos")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Se creó el proyecto correctamente"),
            @ApiResponse(code = 201, message = "Se creó el proyecto correctamente"),
            @ApiResponse(code = 400, message = "Datos insuficientes para la creación de un proyecto"),
            @ApiResponse(code = 401, message = "El usuario no está autorizado para utilizar esta acción"),
            @ApiResponse(code = 403, message = "El usuario no tiene acceso para utilizar esta acción"),
            @ApiResponse(code = 404, message = "Metodo no encontrado"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")

    })
    @PostMapping()
    public ResponseEntity<Project> createProject(@RequestBody() Project nProject){
        return service.createProject(nProject);
    }
    @ApiOperation(value = "Editar un proyecto", notes = "Se recibe por path el ID del proyecto a editar, por el body el proyecto con los datos actualizados y esta actualización se registra en la base de datos")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Proyecto editado correctamente"),
            @ApiResponse(code = 201, message = "Proyecto editado correctamente"),
            @ApiResponse(code = 400, message = "Datos insuficientes para la edición del proyecto"),
            @ApiResponse(code = 401, message = "El usuario no está autorizado para utilizar esta acción"),
            @ApiResponse(code = 403, message = "El usuario no tiene acceso para utilizar esta acción"),
            @ApiResponse(code = 404, message = "Metodo no encontrado"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PutMapping("{id}")
    public ResponseEntity editProject(@PathVariable UUID id, @RequestBody()Project project){
        return service.editProject(id, project);

    }

    @ApiOperation(value = "Eliminar un proyecto", notes = "Se recibe por path el ID del proyecto a eliminar y si se encuentra el ID, se elimina este registro de la base de datos")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Proyecto eliminado correctamente"),
            @ApiResponse(code = 201, message = "Proyecto eliminado correctamente"),
            @ApiResponse(code = 400, message = "Datos insuficientes para eliminar el proyecto"),
            @ApiResponse(code = 401, message = "El usuario no está autorizado para utilizar esta acción"),
            @ApiResponse(code = 403, message = "El usuario no tiene acceso para utilizar esta acción"),
            @ApiResponse(code = 404, message = "Metodo no encontrado"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @DeleteMapping("{id}")
    public ResponseEntity deleteProject(@PathVariable("id")UUID id){
        return service.deleteProject(id);
    }
    @ApiOperation(value = "Buscar un proyecto", notes = "Se recibe por path el ID del proyecto a buscar, y si se encuentra el ID, devuelve el contenido del proyecto")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Proyecto encontrado"),
            @ApiResponse(code = 201, message = "Proyecto encontrado"),
            @ApiResponse(code = 400, message = "Datos ingresados correctamente, verifica que el ID esté en formato UUID"),
            @ApiResponse(code = 401, message = "El usuario no está autorizado para utilizar esta acción"),
            @ApiResponse(code = 403, message = "El usuario no tiene acceso para utilizar esta acción"),
            @ApiResponse(code = 404, message = "Metodo no encontrado"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("{id}")
    public Optional<Project> searchProject(@PathVariable("id")UUID id){
        return service.searchProject(id);
    }
    @ApiOperation(value = "Mostrar lista de proyectos", notes = "Muestra de manera paginada los proyectos creados")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Lista de proyectos mostrada"),
            @ApiResponse(code = 201, message = "Lista de proyectos mostrada"),
            @ApiResponse(code = 400, message = "Error inesperado"),
            @ApiResponse(code = 401, message = "El usuario no está autorizado para utilizar esta acción"),
            @ApiResponse(code = 403, message = "El usuario no tiene acceso para utilizar esta acción"),
            @ApiResponse(code = 404, message = "Metodo no encontrado"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("/ProjectList")
    public Page<ProjectDTO> getProjectsList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10")int size) {

        return service.getProjectsList(page,size);
    }

    @ApiOperation(value = "Agregar tarea a un proyecto", notes = "Se recibe por path el ID del proyecto al cual se le agregará una tarea, si encuentra el ID, se adicionará la tarea al proyecto")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Tarea agregada correctamente"),
            @ApiResponse(code = 201, message = "Tarea agregada correctamente"),
            @ApiResponse(code = 400, message = "Verifica los datos de la tarea a crear y el ID del proyecto asociado"),
            @ApiResponse(code = 401, message = "El usuario no está autorizado para utilizar esta acción"),
            @ApiResponse(code = 403, message = "El usuario no tiene acceso para utilizar esta acción"),
            @ApiResponse(code = 404, message = "Metodo no encontrado"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping("{id}/tasks")
    public ResponseEntity<Task> addTask(@Valid @PathVariable("id")UUID id, @RequestBody Task nTask){
        return service.addTask(id,nTask);
    }

    @ApiOperation(value = "Mostrar tareas vencidas", notes = "Se recibe por path el ID del proyecto del cual se van a consultar las tareas vencidas")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Mostrando tareas vencidas del proyecto ingresado"),
            @ApiResponse(code = 201, message = "Mostrando tareas vencidas del proyecto ingresado"),
            @ApiResponse(code = 400, message = "Verifica el ID del proyecto asociado"),
            @ApiResponse(code = 401, message = "El usuario no está autorizado para utilizar esta acción"),
            @ApiResponse(code = 403, message = "El usuario no tiene acceso para utilizar esta acción"),
            @ApiResponse(code = 404, message = "Metodo no encontrado"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("{id}/due-task")
    public List<Task> getTasksByDueDate(@PathVariable("id")UUID id){
        return service.getTasksByDueDate(id);
    }


    @ApiOperation(value = "Mostrar tareas de un proyecto", notes = "Se recibe por path el ID del proyecto del cual se van a consultar las tareas, se muestran las tareas agrupadas por el estado que tenga cada una")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Mostrando tareas agrupadas por estado de las mismas"),
            @ApiResponse(code = 201, message = "Mostrando tareas agrupadas por estado de las mismas"),
            @ApiResponse(code = 400, message = "Verifica el ID del proyecto asociado"),
            @ApiResponse(code = 401, message = "El usuario no está autorizado para utilizar esta acción"),
            @ApiResponse(code = 403, message = "El usuario no tiene acceso para utilizar esta acción"),
            @ApiResponse(code = 404, message = "Metodo no encontrado"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("{id}/board")
    public ResponseEntity<Map<TaskStatus,List<Task>>> getTaskByProjectID(@PathVariable("id")UUID id){
        return service.getTaskByProjectID(id);
    }


}
