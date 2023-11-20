package com.example.dictionary.Models;

import com.example.dictionary.Trie.Trie;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    Scanner scanner = new Scanner(System.in);

    //Choose 1
    public void insertFromCommandLine(int numberOfWords) {
        try {
            for (int i = 0; i < numberOfWords; i++) {
                System.out.println("Enter English word " + (i + 1) + ": ");
                String target = scanner.nextLine();
                System.out.println("Enter Vietnamese meaning: ");
                String explain = scanner.nextLine();
                Word word = new Word(target, explain);
                Dictionary.addWord(word);
                getWords().add(word);
            }
            System.out.println("You have successfully added " + numberOfWords + " getWords().\n\n\n");
        } catch (Exception e) {
            System.out.println("Action 1 cannot completed");
        }
    }

    public void addWord(String wordTarget, String wordExplain) {
        Word wordAdded = new Word(wordTarget, wordExplain);
        getWords().add(wordAdded);
    }

    //Choose 2
    public void removeWord(String target) {
        try {
            DictionaryManagement dic = new DictionaryManagement();
            Word removeWord = dic.searchWords(target);
            getWords().remove(removeWord);
            exportToFile();
//            ArrayList<Word> selectedWords = Func.lookUpWord(words, target);
//
//            //In ra danh sach tu co the xoa sau khi search
//            System.out.println("These is the word(s) that you may want to delete:");
//            Func.displayList(selectedWords);
//
//            System.out.println("Select the words you want to delete (options separated by spaces, choose 0 if you want to cancel): ");
//            String[] indices = scanner.nextLine().split(" ");
//
//            for (String indexStr : indices) {
//                int index = Integer.parseInt(indexStr);
//                if (index > 0 && index <= selectedWords.size()) {
//                    words.remove(selectedWords.get(index - 1));
//                    System.out.println("Word '" + selectedWords.get(index - 1).getTarget() + "' has been removed.");
//                } else if (index == 0) {
//                    System.out.println("No word has been removed.");
//                } else {
//                    System.out.println("Invalid index: " + index);
//                }
//            }
        } catch (Exception e) {
//            System.out.println("Action 2 cannot completed");
            throw (e);
        }
    }

    //Choose 3
    public void updateWord(String wordTarget, String wordExplain) {
        try {
            Word updateWord = searchWords(wordTarget);
            updateWord.setExplain(wordExplain);
            exportToFile();
//            ArrayList<Word> selectedWords = Func.lookUpWord(words, targetWord);
//
//            //In ra danh sach tu co the xoa sau khi search
//            System.out.println("These is the word(s) that you may want to update:");
//            Func.displayList(selectedWords);
//
//            System.out.println("Select index of the word you want to update(choose 0 if want to cancel): ");
//            int index = scanner.nextInt();
//
//            System.out.println("Select option: 0(Cancel) 1(Update English) 2(Update Vietnamese): 3(Update All)");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//            if (index > 0 && index <= selectedWords.size()) {
//                String newTarget, newExplain;
//                switch (choice) {
//                    case 0:
//                        System.out.println("Canceling... No word has been update.");
//                        break;
//                    case 1:
//                        System.out.println("Enter the English content you want to update: ");
//                        newTarget = scanner.nextLine();
//                        words.remove(selectedWords.get(index - 1));
//                        words.add(new Word(newTarget, selectedWords.get(index - 1).getExplain()));
//                        break;
//                    case 2:
//                        System.out.println("Enter the Vietnamese content you want to update: ");
//                        newExplain = scanner.nextLine();
//                        words.remove(selectedWords.get(index - 1));
//                        words.add(new Word(selectedWords.get(index - 1).getTarget(), newExplain));
//                        break;
//                    case 3:
//                        System.out.println("Enter the English content you want to update: ");
//                        newTarget = scanner.nextLine();
//                        System.out.println("Enter the Vietnamese content you want to update: ");
//                        newExplain = scanner.nextLine();
//                        words.remove(selectedWords.get(index - 1));
//                        words.add(new Word(newTarget, newExplain));
//                }
//                System.out.println("Word '" + selectedWords.get(index - 1).getTarget() + "' has been updated.");
//            } else if (index == 0) {
//                System.out.println("Canceling... No word has been update.");
//            } else {
//                System.out.println("Invalid index: " + index);
//            }
        } catch (Exception e) {
            System.out.println("Action 3 cannot completed");
        }
    }

    //Choose 4
    public void showAllWords() {
        displayList(getWords());
    }

    //Choose 5
//    public void lookUpWords() {
//        try {
//            System.out.println("Enter the word you want to look up: ");
//            String target = scanner.nextLine();
//            ArrayList<Word> selectedWords = Func.lookUpWord(words, target);
//
//            //In ra danh sach tu sau khi search
//            System.out.println("These is the word(s) that you may want to see:");
//            Func.displayList(selectedWords);
//        } catch (Exception e) {
//            System.out.println("Action 5 cannot completed");
//        }
//    }

    //Choose 6
    public Word searchWords(String targetWord) {
        Word result = new Word();
        boolean found = false;
//
//        for (Word word : getWords()) {
//            if (word.getTarget().equalsIgnoreCase(targetWord)) {
//                result = word;
//                found = true;
//                break;
//            }
//        }
        int left = 0;
        int right = getWords().size();
        while (left <= right) {
            int leftMid = left + (right - left) / 3;
            int rightMid = right - (right - left) / 3;

            if (getWords().get(leftMid).getTarget().equals(targetWord)) {
                result = getWords().get(leftMid);
                found = true;
                break;
            }

            if (getWords().get(rightMid).getTarget().equals(targetWord)) {
                result = getWords().get(rightMid);
                found = true;
                break;
            }

            if (getWords().get(rightMid).getTarget().compareTo(targetWord) < 0) {
                left = rightMid + 1;
            } else if (getWords().get(leftMid).getTarget().compareTo(targetWord) > 0) {
                right = leftMid - 1;
            } else {
                left = leftMid + 1;
                right = rightMid - 1;
            }
        }
        if (!found) {
            result.setTarget(targetWord);
            result.setExplain("");
            System.out.println("Word not found.");
        }
        return result;
    }

    public ArrayList<String> lookUpWord(ArrayList<Word> root, String key) {
        //        for (Word word : root) {
//            if (word.getTarget().startsWith(key)) {
//                lookUp.add(word.getTarget());
//            }
//        }
        Trie trie = new Trie();
        for (Word word : root) {
            trie.insert(word.getTarget());
        }
        return new ArrayList<>(trie.findAllPrefixWords(key));
    }

    //Choose 8
    public void importFromFile() {
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("New_Project_Architecture/Dictionary/src/main/resources/com/example/dictionary/Models/Database/dictionaries.txt"));
            String line;
            Word newWord = new Word();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("|")) {
                    if (!newWord.getExplain().isEmpty() && !newWord.getTarget().isEmpty()) {
                        getWords().add(newWord);
                        newWord = new Word();
                    }
                    newWord.setTarget(line.substring(1));
                } else {
                    newWord.setExplain(newWord.getExplain() + line + "\n");
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    //Choose 9
    public void exportToFile() {
        try {
            String path = "New_Project_Architecture/Dictionary/src/main/resources/com/example/dictionary/Models/Database/dictionaries.txt";
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : getWords()) {
                bufferedWriter.write("|" + word.getTarget() + "\n" + word.getExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public void displayList(ArrayList<Word> words) {
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
