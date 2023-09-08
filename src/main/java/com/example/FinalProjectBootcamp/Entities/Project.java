package com.example.FinalProjectBootcamp.Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "status", nullable = false)
    private ProjectStatus status;
    @Column(name = "createdDate", nullable = false)
    private LocalDate createdDate;
    @Column(name = "lastUpdateDate", nullable = false)
    private LocalDate lastUpdateDate;



    public Project(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.status = ProjectStatus.ACTIVE;
        this.createdDate = LocalDate.now();
    }
    public Project(String name, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.status = ProjectStatus.ACTIVE;
        this.createdDate = LocalDate.now();
    }

    @OneToMany(mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
