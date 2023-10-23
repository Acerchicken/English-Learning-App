package Cmd_Line;

import Cmd_Line.Small_Func.Small_Function;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private ArrayList<Word> words;
    Small_Function Func = new Small_Function();
    Scanner scanner = new Scanner(System.in);

    //Choose 1
    public void insertFromCommandLine() {
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
    public void removeWord() {
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

    }

    //Choose 3
    public void updateWord() {
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
    }

    //Choose 4
    public void showAllWords() {
        Func.displayList(words);
    }
}
