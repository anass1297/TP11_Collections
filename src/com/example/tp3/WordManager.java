package com.example.tp3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordManager {
    private final String rawText;

    private final Set<String> hashSet;
    private final Set<String> linkedSet; 
    private final Set<String> treeSet;
    private final Map<String, Integer> wordCount;

    public WordManager(String text) {
        this.rawText   = text;
        this.hashSet   = new HashSet<>();
        this.linkedSet = new LinkedHashSet<>();
        this.treeSet   = new TreeSet<>(
                Comparator.comparingInt(String::length)
                          .thenComparing(String::compareToIgnoreCase)
        );
        this.wordCount = new HashMap<>();
    }

   
    public void parseText() {
        String[] tokens = rawText
                .toLowerCase(Locale.ROOT)
                .split("[^a-zA-Z]+");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            hashSet.add(token);
            linkedSet.add(token);
            treeSet.add(token);

     
            wordCount.put(token, wordCount.getOrDefault(token, 0) + 1);
        }
    }

   
    public void displayAll() {
        System.out.println("--- HashSet (ordre indéfini) ---");
        System.out.println(hashSet);

        System.out.println("\n--- LinkedHashSet (ordre d'insertion) ---");
        System.out.println(linkedSet);

        System.out.println("\n--- TreeSet (tri longueur + alphabétique) ---");
        System.out.println(treeSet);

        System.out.println("\n--- Comptage des occurrences ---");
        System.out.println(wordCount);
    }

   
    public boolean contains(String word) {
        return hashSet.contains(word.toLowerCase(Locale.ROOT));
    }


    public boolean remove(String word) {
        String w = word.toLowerCase(Locale.ROOT);
        boolean removed = false;

        removed |= hashSet.remove(w);
        removed |= linkedSet.remove(w);
        removed |= treeSet.remove(w);
        removed |= (wordCount.remove(w) != null);

        return removed;
    }


    public static String loadFromFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(" ");
            }
        }
        return sb.toString();
    }
}
