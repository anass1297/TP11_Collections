package com.example.tp4;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DictionaryManager {

    private final Map<String,String> hashMap = new HashMap<>();
    private final Map<String,String> linkedMap = new LinkedHashMap<>();
    private final NavigableMap<String,String> treeMap =
        new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public void addEntry(String eng, String fr) {
        hashMap.put(eng, fr);
        linkedMap.put(eng, fr);
        treeMap.put(eng, fr);
    }

    public String getTranslation(String eng) {
        return hashMap.get(eng);
    }

    public boolean removeEntry(String eng) {
        boolean existed = hashMap.remove(eng) != null;
        linkedMap.remove(eng);
        treeMap.remove(eng);
        return existed;
    }

    public void displayAll() {
        System.out.println("=== HashMap (unordered) ===");
        hashMap.forEach((k,v) -> System.out.printf("%-10s → %s%n", k, v));

        System.out.println("\n=== LinkedHashMap (insertion order) ===");
        linkedMap.forEach((k,v) -> System.out.printf("%-10s → %s%n", k, v));

        System.out.println("\n=== TreeMap (alphabetical order) ===");
        treeMap.forEach((k,v) -> System.out.printf("%-10s → %s%n", k, v));
    }

    /** Recherche par préfixe (version Stream existante). */
    public Map<String,String> searchByPrefix(String prefix) {
        String low = prefix.toLowerCase(Locale.ROOT);
        return treeMap.entrySet().stream()
            .filter(e -> e.getKey().toLowerCase(Locale.ROOT).startsWith(low))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a,b)->a,
                LinkedHashMap::new
            ));
    }

    public Map<String,String> autoComplete(String prefix) {
        return treeMap.subMap(
                prefix,
                prefix + Character.MAX_VALUE
        );
    }

    public int countEntries() {
        return hashMap.size();
    }

    public List<String> topNLongestWords(int n) {
        return treeMap.keySet().stream()
                .sorted((a,b) -> Integer.compare(b.length(), a.length()))
                .limit(n)
                .collect(Collectors.toList());
    }
    
    public void saveToJson(String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("{\n");
            Iterator<Map.Entry<String,String>> it = treeMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String,String> e = it.next();
                bw.write("  \"" + e.getKey() + "\": \"" + e.getValue() + "\"");
                if (it.hasNext()) bw.write(",");
                bw.write("\n");
            }
            bw.write("}");
        }
    }

    public void loadFromJson(String filePath) throws IOException {
        hashMap.clear();
        linkedMap.clear();
        treeMap.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.lines()
              .map(String::trim)
              .filter(line -> line.contains(":"))
              .forEach(line -> {
                  String cleaned = line
                          .replace(",", "")
                          .replace("\"", "");
                  String[] parts = cleaned.split(":");
                  if (parts.length == 2) {
                      addEntry(parts[0].trim(), parts[1].trim());
                  }
              });
        }
    }
}
