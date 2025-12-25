package com.example.tp3;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String text;

        System.out.print("Entrer le chemin du fichier texte (ou ENTER pour texte par défaut) : ");
        String path = scanner.nextLine().trim();

        if (!path.isEmpty()) {
            try {
                text = WordManager.loadFromFile(path);
            } catch (IOException e) {
                System.out.println("Erreur de lecture du fichier. Texte par défaut utilisé.");
                text = getDefaultText();
            }
        } else {
            text = getDefaultText();
        }

        WordManager manager = new WordManager(text);
        manager.parseText();
        manager.displayAll();

        while (true) {
            System.out.print("\n> Entrer un mot à rechercher (ou 'exit') : ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;

            boolean found = manager.contains(input);
            System.out.printf("Présence de '%s' ? %b%n", input, found);

            System.out.print("Souhaitez-vous supprimer ce mot ? (y/n) ");
            String confirm = scanner.nextLine().trim();

            if (confirm.equalsIgnoreCase("y")) {
                boolean removed = manager.remove(input);
                System.out.printf(
                        removed
                                ? "Le mot '%s' a été supprimé.%n"
                                : "Le mot '%s' n'existait pas.%n",
                        input
                );
                manager.displayAll();
            }
        }

        scanner.close();
        System.out.println("Fin du programme.");
    }

    private static String getDefaultText() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
             + "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    }
}
