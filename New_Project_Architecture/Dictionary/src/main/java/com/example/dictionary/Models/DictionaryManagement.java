package com.example.dictionary.Models;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    Small_Function Func = new Small_Function();
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
                words.add(word);
            }
            System.out.println("You have successfully added " + numberOfWords + " words.\n\n\n");
        } catch (Exception e) {
            System.out.println("Action 1 cannot completed");
        }
    }
    public void addWord(String wordTarget, String wordExplain) {
        Word wordAdded = new Word(wordTarget, wordExplain);
        words.add(wordAdded);
    }
    //Choose 2
    public void removeWord(String target) {
        try {
            DictionaryManagement dic = new DictionaryManagement();
            Word removeWord = dic.searchWords(target);
            words.remove(removeWord);
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
        Func.displayList(words);
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
        try {
            boolean found = false;

            for (Word word : words) {
                if (word.getTarget().equalsIgnoreCase(targetWord)) {
                    result = word;
                    break;
                }
            }

            if (!found) {
                System.out.println("Word not found.");
            }

        } catch (Exception e) {
            System.out.println("Action 6 cannot completed");
        }
        return result;
    }

    //Choose 8
    public void importFromFile() {
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Github Place\\English-Learning-App\\New_Project_Architecture\\Dictionary\\src\\main\\resources\\com\\example\\dictionary\\Models\\Database\\dictionaries.txt"));


            String line;
            Word newWord = new Word();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("|")) {
                    if (!newWord.getExplain().isEmpty() && !newWord.getTarget().isEmpty()) {
                        words.add(newWord);
                        newWord = new Word();
                    }
                    newWord.setTarget(line.substring(1));
                } else {
                    newWord.setExplain(newWord.getExplain() + line);
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
            String path = "D:\\Github Place\\English-Learning-App\\New_Project_Architecture\\Dictionary\\src\\main\\resources\\com\\example\\dictionary\\Models\\Database\\dictionaries.txt";
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : words) {
                bufferedWriter.write("|" + word.getTarget() + "\n" + word.getExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }
}

//try{
//
//        }catch (Exception e){
//        System.out.println("Action  cannot completed");
//    }