package com.example.dictionary.TranslateSentence;

import com.google.cloud.translate.Language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
    private Map<String, String> allLanguages = new HashMap<>();
    private List<String> languageList = new ArrayList<>();
    public Data() {
        try {
            BufferedReader languageReader = new BufferedReader(new FileReader("src/main/resources/com/example/dictionary/Models/Database/All-Languages.txt"));

            String line;
            while ((line = languageReader.readLine()) != null) {
                String[] parts = line.split(",");

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

    public List<String> getLanguageList(Map<String, String> allLanguages) {
        for (Map.Entry<String, String> entry : allLanguages.entrySet()) {
            languageList.add(entry.getKey());
        }
        return languageList;
    }
}