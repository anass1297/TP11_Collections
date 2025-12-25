package com.example.tp2;

import java.time.LocalDate;


public class Task {
    private static int counter = 0;
    private final int id;
    private String description;
    private int priority;    
    private Status status;
    private LocalDate dueDate; 

    public Task(String description, int priority, LocalDate dueDate) {
        this.id = ++counter;
        this.description = description;
        this.priority = priority;
        this.status = Status.PENDING;
        this.dueDate = dueDate;
    }


    public int getId() { return id; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    @Override
    public String toString() {
        return String.format("[%d] (prio=%d) %s — %s (due: %s)",
                             id, priority, status, description, dueDate);
    }
}
