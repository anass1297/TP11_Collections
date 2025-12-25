package com.example.tp1;

import java.util.Scanner;

/**
 * Application console pour interagir avec ShoppingList.
 */
public class Main {
    public static void main(String[] args) {
        ShoppingList list = new ShoppingList();
        Scanner sc = new Scanner(System.in);

        System.out.println("Gestion de la liste de courses");

        while (true) {
            System.out.println("\nCommandes : add, remove, find, show, exit");
            System.out.print("Entrez une commande : ");

            String cmd = sc.nextLine().trim().toLowerCase();
            switch (cmd) {
                case "add":
                    System.out.print("Article à ajouter : ");
                    list.add(sc.nextLine().trim());
                    break;
                case "remove":
                    System.out.print("Article à supprimer : ");
                    list.remove(sc.nextLine().trim());
                    break;
                case "find":
                    System.out.print("Article à rechercher : ");
                    list.contains(sc.nextLine().trim());
                    break;
                case "show":
                    list.display();
                    break;
                case "exit":
                    System.out.println("Au revoir !");
                    sc.close();
                    return;
                default:
                    System.out.println("Commande inconnue.");
            }
        }
    }
}