package com.example.tp4;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DictionaryManager dict = new DictionaryManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                
                ===== MENU DICTIONNAIRE =====
                1. Ajouter un mot
                2. Traduire un mot
                3. Autocomplétion
                4. Supprimer un mot
                5. Afficher le dictionnaire
                6. Statistiques
                7. Sauvegarder (JSON)
                8. Charger (JSON)
                0. Quitter
                """);

            System.out.print("Choix : ");
            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Mot anglais : ");
                        String eng = sc.nextLine();
                        System.out.print("Traduction française : ");
                        String fr = sc.nextLine();
                        dict.addEntry(eng, fr);
                    }

                    case "2" -> {
                        System.out.print("Mot à traduire : ");
                        String w = sc.nextLine();
                        System.out.println("→ " +
                                dict.getTranslation(w));
                    }

                    case "3" -> {
                        System.out.print("Préfixe : ");
                        String p = sc.nextLine();
                        Map<String,String> res = dict.autoComplete(p);
                        res.forEach((k,v) ->
                                System.out.printf("%-10s → %s%n", k, v));
                    }

                    case "4" -> {
                        System.out.print("Mot à supprimer : ");
                        String r = sc.nextLine();
                        System.out.println(
                                dict.removeEntry(r)
                                        ? "Supprimé."
                                        : "Introuvable."
                        );
                    }

                    case "5" -> dict.displayAll();

                    case "6" -> {
                        System.out.println("Nombre d’entrées : "
                                + dict.countEntries());
                        System.out.println("Top 3 mots les plus longs : "
                                + dict.topNLongestWords(3));
                    }

                    case "7" -> {
                        dict.saveToJson("dictionary.json");
                        System.out.println("Sauvegardé avec succès.");
                    }

                    case "8" -> {
                        dict.loadFromJson("dictionary.json");
                        System.out.println("Chargé avec succès.");
                    }

                    case "0" -> {
                        System.out.println("Fin du programme.");
                        sc.close();
                        return;
                    }

                    default -> System.out.println("Choix invalide.");
                }
            } catch (IOException e) {
                System.out.println("Erreur fichier : " + e.getMessage());
            }
        }
    }
}
