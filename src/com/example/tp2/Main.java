package com.example.tp2;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TaskManager mgr = new TaskManager();

   
        mgr.addTask("Écrire la doc", 2, LocalDate.of(2025, 12, 28));
        mgr.addTask("Corriger les bugs", 1, LocalDate.of(2025, 12, 25));
        mgr.addTask("Préparer démo", 3, LocalDate.of(2025, 12, 30));
        mgr.addTask("Envoyer emails", 4, LocalDate.of(2025, 12, 26));

        System.out.println("=== Toutes les tâches ===");
        mgr.listTasks().forEach(System.out::println);
        System.out.println();

        System.out.println("=== Tri par date d’échéance ===");
        mgr.sortByDueDate();
        mgr.listTasks().forEach(System.out::println);
        System.out.println();

        System.out.println("=== Tâche #2 en cours ===");
        mgr.updateStatus(2, Status.IN_PROGRESS);
        mgr.listTasks().forEach(System.out::println);
    }
}
