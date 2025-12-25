package com.example.tp2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;



import java.util.List;
import java.time.LocalDate;


public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

   
    public void addTask(String description, int priority, LocalDate dueDate) {
        tasks.add(new Task(description, priority, dueDate));
    }

    
    public boolean removeTask(int id) {
        return tasks.removeIf(t -> t.getId() == id);
    }

    public boolean updateStatus(int id, Status newStatus) {
        return tasks.stream()
                    .filter(t -> t.getId() == id)
                    .findFirst()
                    .map(t -> { t.setStatus(newStatus); return true; })
                    .orElse(false);
    }

  
    public List<Task> listTasks() {
        return new ArrayList<>(tasks);
    }

   
    public List<Task> filterByStatus(Status status) {
        return tasks.stream()
                    .filter(t -> t.getStatus() == status)
                    .collect(Collectors.toList());
    }

  
    public List<Task> filterByPriority(int priority) {
        return tasks.stream()
                    .filter(t -> t.getPriority() == priority)
                    .collect(Collectors.toList());
    }

  
    public void sortByPriority() {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
    }

    public void sortByDueDate() {
        tasks.sort(Comparator.comparing(Task::getDueDate));
    }

}
