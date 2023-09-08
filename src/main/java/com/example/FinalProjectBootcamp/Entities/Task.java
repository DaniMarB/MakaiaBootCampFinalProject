package com.example.FinalProjectBootcamp.Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @Column(name = "type", nullable = false)
    private TaskType type;
    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;
    @Column(name = "dueDate", nullable = false)
    private LocalDate dueDate;
    @Column(name = "createdDate")
    private LocalDateTime createdDate;
    @Column(name = "lastUpdateDate")
    private LocalDateTime lastUpdateDate;



    public Task() {
    }

    public Task(UUID id, String name, String description, TaskStatus status, TaskType type, LocalDate startDate, LocalDate dueDate, Project projectAssociated) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.type = type;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.createdDate = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", createdDate=" + createdDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
