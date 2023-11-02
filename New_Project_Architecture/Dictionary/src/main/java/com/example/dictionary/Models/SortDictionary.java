package com.example.dictionary.Models;

import java.util.Comparator;

public class SortDictionary implements Comparator<Word> {
    @Override
    public int compare(Word word1, Word word2) {
        return word1.getTarget().compareTo(word2.getTarget());
    }
}

