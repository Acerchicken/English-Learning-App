package com.example.dictionary.Models;


import java.util.ArrayList;
import java.util.Collections;

public class Small_Function {
    public ArrayList<Word> lookUpWord(ArrayList<Word> root, String key){
        ArrayList<Word> lookUp = new ArrayList<>();
        for(Word word : root){
            if(word.getTarget().contains(key)){
                lookUp.add(word);
            }
        }
        return lookUp;
    }

    public void displayList(ArrayList<Word> words){
        //Sort theo alphabet
        Collections.sort(words, new SortDictionary());
        //Can chinh | cho thang hang
        int maxIndex = (int) Math.log10(words.size()) + 1;
        int maxEnglishLength = 0;
        int maxVietnameseLength = 0;
        for (Word word : words) {
            int englishLength = word.getTarget().length();
            int vietnameseLength = word.getExplain().length();
            if (englishLength > maxEnglishLength) {
                maxEnglishLength = englishLength;
            }
            if (vietnameseLength > maxVietnameseLength) {
                maxVietnameseLength = vietnameseLength;
            }
        }

        //Hien thi tieu de cot
        System.out.printf("%-" + maxIndex + "s | %-"
                + maxEnglishLength + "s | %-"
                + maxVietnameseLength + "s%n", "Index", "English", "Vietnamese");

        //In danh sach
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            System.out.printf("%-" + maxIndex + "s | %-"
                    + maxEnglishLength + "s | %-"
                    + maxVietnameseLength + "s%n", i + 1, word.getTarget(), word.getExplain()
            );
        }
    }
}
