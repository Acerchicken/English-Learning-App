package Cmd_Line;

import Cmd_Line.Small_Func.Small_Function;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    Small_Function Func = new Small_Function();
    Scanner scanner = new Scanner(System.in);

    //Choose 1
    public void insertFromCommandLine() {
        try {
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

            System.out.println("You have successfully added " + numberWords + " words.\n\n\n");
        } catch (Exception e) {
            System.out.println("Action 1 cannot completed");
        }
    }

    //Choose 2
    public void removeWord() {
        try {
            System.out.println("Enter the word to remove: ");
            String target = scanner.nextLine();

            ArrayList<Word> selectedWords = Func.lookUpWord(words, target);

            //In ra danh sach tu co the xoa sau khi search
            System.out.println("These is the word(s) that you may want to delete:");
            Func.displayList(selectedWords);

            System.out.println("Select the words you want to delete (options separated by spaces, choose 0 if want to cancel): ");
            String[] indices = scanner.nextLine().split(" ");

            for (String indexStr : indices) {
                int index = Integer.parseInt(indexStr);
                if (index > 0 && index <= selectedWords.size()) {
                    words.remove(selectedWords.get(index - 1));
                    System.out.println("Word '" + selectedWords.get(index - 1).getTarget() + "' has been removed.");
                } else if (index == 0) {
                    System.out.println("No word has been removed.");
                } else {
                    System.out.println("Invalid index: " + index);
                }
            }
        } catch (Exception e) {
            System.out.println("Action 2 cannot completed");
        }
    }

    //Choose 3
    public void updateWord() {
        try {
            System.out.println("Enter the word you want to update: ");
            String target = scanner.nextLine();
            ArrayList<Word> selectedWords = Func.lookUpWord(words, target);

            //In ra danh sach tu co the xoa sau khi search
            System.out.println("These is the word(s) that you may want to update:");
            Func.displayList(selectedWords);

            System.out.println("Select index of the word you want to update(choose 0 if want to cancel): ");
            int index = scanner.nextInt();

            System.out.println("Select option: 0(Cancel) 1(Update English) 2(Update Vietnamese): 3(Update All)");
            int choice = scanner.nextInt();

            if (index > 0 && index <= selectedWords.size()) {
                String newTarget, newExplain;
                switch (choice) {
                    case 0:
                        System.out.println("Canceling... No word has been update.");
                        break;
                    case 1:
                        System.out.println("Enter the English content you want to update: ");
                        newTarget = scanner.nextLine();
                        words.remove(selectedWords.get(index - 1));
                        words.add(new Word(newTarget, selectedWords.get(index - 1).getExplain()));
                        break;
                    case 2:
                        System.out.println("Enter the Vietnamese content you want to update: ");
                        newExplain = scanner.nextLine();
                        words.remove(selectedWords.get(index - 1));
                        words.add(new Word(selectedWords.get(index - 1).getTarget(), newExplain));
                        break;
                    case 3:
                        System.out.println("Enter the English content you want to update: ");
                        newTarget = scanner.nextLine();
                        System.out.println("Enter the Vietnamese content you want to update: ");
                        newExplain = scanner.nextLine();
                        words.remove(selectedWords.get(index - 1));
                        words.add(new Word(newTarget, newExplain));
                }
                System.out.println("Word '" + selectedWords.get(index - 1).getTarget() + "' has been updated.");
            } else if (index == 0) {
                System.out.println("Canceling... No word has been update.");
            } else {
                System.out.println("Invalid index: " + index);
            }
        } catch (Exception e) {
            System.out.println("Action 3 cannot completed");
        }
    }

    //Choose 4
    public void showAllWords() {
        Func.displayList(words);
    }

    //Choose 5
    public void lookUpWords() {
        try {
            System.out.println("Enter the word you want to look up: ");
            String target = scanner.nextLine();
            ArrayList<Word> selectedWords = Func.lookUpWord(words, target);

            //In ra danh sach tu sau khi search
            System.out.println("These is the word(s) that you may want to see:");
            Func.displayList(selectedWords);
        } catch (Exception e) {
            System.out.println("Action 5 cannot completed");
        }
    }

    //Choose 6
    public void searchWords() {
        try {
            System.out.println("Enter the word you want to search: ");
            String target = scanner.nextLine();

            boolean found = false;

            for (Word word : words) {
                if (word.getTarget().equalsIgnoreCase(target)) {
                    System.out.println("Word found:");
                    System.out.println("English: " + word.getTarget());
                    System.out.println("Vietnamese: " + word.getExplain());
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Word not found.");
            }

        } catch (Exception e) {
            System.out.println("Action 6 cannot completed");
        }
    }

    //Choose 8
    public void importFromFile() {
        try {
            System.out.println("Import the file path: ");
            BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Desktop\\Oasis java OOP\\src\\main\\java\\Cmd_Line\\dictionaries.txt"));
            String line;
            Word newWord = new Word();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("|")) {
                    if (!newWord.getExplain().isEmpty() && !newWord.getTarget().isEmpty() ) {
                        words.add(newWord);
                        newWord = new Word();
                    }
                    newWord.setTarget(line.substring(1));
                } else {
                    newWord.setExplain(newWord.getExplain()+line);
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
            System.out.println("Export the file to the path: ");
            String path = scanner.nextLine();
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