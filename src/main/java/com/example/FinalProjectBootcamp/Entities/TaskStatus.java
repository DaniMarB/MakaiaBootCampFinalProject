package com.example.FinalProjectBootcamp.Entities;

public enum TaskStatus {
    TODO("TODO"),
    INPROGRESS("INPROGRESS"),
    BLOCKED("BLOCKED"),
    DONE("DONE");

    private String name;

    TaskStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
