package Bai_tap_lon;

import Bai_tap_lon.Dictionary;
import Bai_tap_lon.Small_Func.Comparator_Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class DictionaryManagement {
    private ArrayList<Word> words = Dictionary.getWords();

    //Choose 1
    public void insertFromCommandLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of words: ");
        int numberWords = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberWords; i++) {
            System.out.println("Enter English word " + (i + 1) + ": ");
            String target = scanner.nextLine();
            System.out.println("Enter Vietnamese meaning: ");
            String explain = scanner.nextLine();
            Word word = new Word(target, explain);
            Dictionary.addWord(word);
        }

        System.out.println("You have successfully added" + numberWords + " words.\n\n\n");
    }

    //Choose 2
    public void removeWord(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word to remove: ");
        String target = scanner.nextLine();
        ArrayList<Word> removedWords = new ArrayList<>();

        //Tim va loai bo word target
        Iterator<Word> iterator = words.iterator();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            if (word.getTarget().equals(target)) {
                iterator.remove();
                removedWords.add(word);
            }
        }
    }

    //Choose 4
    public void showAllWords() {
        //Sort theo alphabet
        Collections.sort(words, new Comparator_Word());

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
