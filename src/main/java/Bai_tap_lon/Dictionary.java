package Bai_tap_lon;

import java.util.ArrayList;

public class Dictionary {
    private static ArrayList<Word> words = new ArrayList<>();

    public static ArrayList<Word> getWords() {
        return words;
    }

    public static void addWord(Word word) {
        words.add(word);
    }
}
