package com.example.dictionary.Models;

import java.util.ArrayList;

public class Dictionary {
    public static ArrayList<Word> words = new ArrayList<Word>();

    public static ArrayList<Word> getWords() {
        return words;
    }

    public static void addWord(Word word) {
        words.add(word);
    }
}
