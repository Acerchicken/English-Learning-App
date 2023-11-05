package com.example.dictionary.Models;

import java.util.Scanner;

public class DictionaryCommandline {
    private final DictionaryManagement dicManage = new DictionaryManagement();

    public void dictionaryAdvanced(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("----------------------------");
            System.out.println("|Welcome to My Application!|");
            System.out.println("| [0] Exit                 |");
            System.out.println("| [1] Add                  |");
            System.out.println("| [2] Remove               |");
            System.out.println("| [3] Update               |");
            System.out.println("| [4] Display              |");
            System.out.println("| [5] Lookup               |");
            System.out.println("| [6] Search               |");
            System.out.println("| [7] Game                 |");
            System.out.println("| [8] Import from file     |");
            System.out.println("| [9] Export to file       |");
            System.out.println("----------------------------");
            System.out.print("Your action: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
                System.out.println("\nProcessing...");
            } catch (NumberFormatException e) {
                System.out.println("Action not supported\n" +
                        "------------------------------");
                continue;
            }

            switch (choice){
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Enter the number of words: ");
                    int numberOfWords = scanner.nextInt();
                    scanner.nextLine();
                    dicManage.insertFromCommandLine(numberOfWords);
                    break;
                case 2:
                    System.out.println("Enter the word to remove: ");
                    String target = scanner.nextLine();
                    dicManage.removeWord(target);
                    break;
                case 3:
                    System.out.println("Enter the word you want to update: ");
                    String targetWord = scanner.nextLine();
//                    dicManage.updateWord(targetWord);
                    break;
                case 4:
                    dicManage.showAllWords();
                    break;
                case 5:
                    dicManage.lookUpWords();
                    break;
                case 6:
                    System.out.println("Enter the word you want to search: ");
                    String searchedWord = scanner.nextLine();

                    break;
                case 7:

                    break;
                case 8:
                    dicManage.importFromFile();
                    break;
                case 9:
                    dicManage.exportToFile();
                    break;
                default:
                    System.out.println("Action not supported\n" +
                            "------------------------------");
                    break;
            }
        }
    }
}
