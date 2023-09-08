package com.example.FinalProjectBootcamp.Service;

import com.example.FinalProjectBootcamp.Entities.Task;
import com.example.FinalProjectBootcamp.Entities.TaskStatus;

import java.util.ArrayList;
import java.util.Optional;

public class TaskService {

    ArrayList<Task> tasksList = new ArrayList<>();

    public void deleteTask (String id){
        tasksList.removeIf(t -> t.getId().equals(id));
    }

    public Optional<Task> getTask(String id){
        return this.tasksList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public String createTask(Task nTask){
        tasksList.add(nTask);
        //System.out.println(nProject.toString());
        return ("Task created. UUID related: " + nTask.getId());
    }

    public void updateTasksStatus(String id, TaskStatus status){
        for(int i = 0; i < tasksList.size();i++){
                if(tasksList.get(i).getId().equals(id)){
                    if(tasksList.get(i).getStatus().equals(TaskStatus.TODO)){
                        if(status.equals(TaskStatus.INPROGRESS)) {

                        }
                    }else if(tasksList.get(i).getStatus().equals(TaskStatus.INPROGRESS)){
                        if(status.equals(TaskStatus.BLOCKED)||status.equals(TaskStatus.DONE)) {
                            tasksList.get(i).setStatus(status);
                        }
                    }else if (tasksList.get(i).getStatus().equals(TaskStatus.BLOCKED)) {
                        if(status.equals(TaskStatus.INPROGRESS)||status.equals(TaskStatus.DONE)) {
                            tasksList.get(i).setStatus(status);
                        }
                    }else{
                        System.out.println("No es posible asignar al estado "+status.toString()+" una tarea con estado "+tasksList.get(i).getStatus().toString());
                    }
                }else{
                    System.out.println("Task wasn't found in the list");
                }
        }
    }
}
