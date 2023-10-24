# SISTEMA KANBAN PARA LA GESTION DE PROYECTOS
El presente proyecto tiene como finalidad aplicar los conocimientos adquiridos a través del transcurso del curso de desarrollo web Backend. 

Dichos conocimientos se pusieron en practica mediante la creación de una aplicación diseñada para la gestión de proyectos mediante un tablero Kanban la cual permite crear, editar, eliminar y buscar proyectos, sus respectivas tareas y demás funciones relacionadas con estos objetos.

Para el desarrollo de este proyecto se utilizaron tacnologías como:

<ol>
	<li>Java</li>
	<li>MySQL Workbench</li>
  <li>Postman</li>
</ol>

Adiconalmente, el proyecto se realizó utilizando SpringBoot, por lo cual emplearon las siguientes librerías

<ol>
	<li>Rest-api</li>
	<li>Swagger</li>
	<li>Spring data</li>
	<li>Spring security</li>
</ol>

La documentación y e integración continua se realizaron con Swagger y GitHub Actions respectivamente. El despliegue se realizó utilizando la plataforma Railway. El dominio es el siguiente
### https://kanbanboardforpm.up.railway.app/swagger-ui/index.html#/

# Endpoints:
### POST: localhost:8080/vi/projects 
Permite crear un proyecto ingresando el nombre del proyecto y opcionalmente la descripcion del mismo.


Ejemplo del body de la petición:
  ```java {.highlight .highlight-source-java .bg-black}
    {
    "name": "Final project",
    "description": "Optional description"
}
```

### PUT: localhost:8080/vi/projects/{id}
Permite editar un proyecto del cual se debe ingresar su id para ser reconocido.


Ejemplo del body de la petición:
  ```java {.highlight .highlight-source-java .bg-black}
  {
    "name": "Project edited",
    "description":"Description edited"
}
```

### DELETE: localhost:8080/vi/projects/{id}
Permite eliminar un proyecto del cual se debe ingresar su id para ser reconocido.

### GET: localhost:8080/vi/projects/{id}
Permite encontrar un proyecto del cual se debe ingresar su id para ser reconocido.

### GET: localhost:8080/vi/projects/ProjectList/
Permite mostrar una lista paginada de los proyectos creados hasta el momento con su nombre, descripción y estado del proyecto y fecha de creación.

### DELETE: localhost:8080/vi/tasks/{id}
Permite eliminar una tarea utilizando el id de la misma

### GET: localhost:8080/vi/tasks/{id}
Permite encontrar una tarea utilizando el id de la misma

### POST: localhost:8080/vi/projects/{id}
Permite crear una tarea relacionada a un proyecto, es necesario especificar el id del proyecto para que sea posible crear la tarea y asociarla al mismo. Además, es requerido ingresar el nombre, descripcion, tipo, fecha de inicio y fecha de entrega de la tarea para poder ser creada.

Ejemplo del body de la petición:
  ```java {.highlight .highlight-source-java .bg-black}
{
    "name": "Task test",
    "description": "description test",
    "type": "STORY",
    "startDate": "2023-09-17",
    "dueDate": "2023-12-29"
}
```

### PATCH localhost:8080/vi/tasks/{id}
Permite actualizar el estado de una tarea. Es indispensable seguir el orden secuencial logico de los estados.

### GET localhost:8080/vi/projects/{id}/due-task
Permite mostrar las tareas de un proyecto específico, identificado por su id, cuyas fechas de entrega ya hayan pasado, es decir, las tareas vencidas

### GET localhost:8080/vi/projects/{id}
Permite traer las tareas de un proyecto específico y agrupa las mismas por el estado en el que estén

# Diagrama Entidad-Relación
![DER](https://github.com/DaniMarB/MakaiaBootCampFinalProject/blob/master/Diagrama%20entidad-relacion.jpg?raw=true)










