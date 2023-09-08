package com.example.FinalProjectBootcamp.Service;

import com.example.FinalProjectBootcamp.Entities.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    ArrayList<Project> projectsList = new ArrayList<>();

    public String createProject(Project nProject){
        projectsList.add(nProject);
        //System.out.println(nProject.toString());
        return ("Project created. UUID related: " + nProject.getId());
    }

    public void editProject(UUID id, Project updatedProject){
        if(projectsList.stream().filter(p -> p.getId().equals(id))!=null){
            projectsList.removeIf(p -> p.getId().equals(id));
            updatedProject.setId(id);
            projectsList.add(updatedProject);
        }

    }

    public void deleteProject(UUID id){projectsList.removeIf(p -> p.getId().equals(id));
    }

    public Optional<Project> searchProject(UUID id){
        return this.projectsList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public ArrayList<Project> getProjectsList(){
        return projectsList;
    }
}
