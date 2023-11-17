package com.example.dictionary.TranslateSentence;

import com.google.cloud.translate.Language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Data {
    private Map<String, String> allLanguages = new LinkedHashMap<>();
    private List<String> languageList = new ArrayList<>();
    public Data() {
        try {
            BufferedReader languageReader = new BufferedReader(new FileReader("New_Project_Architecture/Dictionary/src/main/resources/com/example/dictionary/Models/Database/All-Languages.txt"));

            String line;
            while ((line = languageReader.readLine()) != null) {
                String[] parts = line.split(" ");

                if (parts.length == 2) {
                    String lang = parts[0].trim();
                    String langCode = parts[1].trim();

                    allLanguages.put(lang, langCode);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLanguageList() {
        for (Map.Entry<String, String> entry : allLanguages.entrySet()) {
            languageList.add(entry.getKey());
        }
        return languageList;
    }

    public String getLanguageIndex(String country) {
        return allLanguages.get(country);
    }
}