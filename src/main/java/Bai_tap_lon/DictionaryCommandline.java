package Bai_tap_lon;

import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement dicManage = new DictionaryManagement();

    public void dictionaryAdvanced(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Welcome to My Application!");
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
            System.out.print("Your action: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Action not supported");
                continue;
            }

            switch (choice){
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                case 1:
                    dicManage.insertFromCommandLine();
                case 2:
                    dicManage.removeWord();
                case 3:
                case 4:
                    dicManage.showAllWords();
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
            }
        }
    }
}
