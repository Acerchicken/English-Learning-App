package com.example.dictionary.Game;

import com.example.dictionary.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.dictionary.Models.Dictionary.words;


public class Game extends Application {
    public Map<String, String> randomPickWord(int n, int length) {
        Map<String, String> randomListWord = new HashMap<String, String>();
        getDic().importFromFile();
        Random random = new Random();
        int size = words.size();
        while (randomListWord.size() < n) {
            int randomIndex = random.nextInt(Math.abs(size));
            String randomElement = words.get(randomIndex).getTarget().replace(" ", "").replace("'","").replace("-", "").toUpperCase();
            if (randomElement.length() >= 2 && randomElement.length() <= length && !randomListWord.containsValue(randomElement)) {
                randomListWord.put(randomElement,words.get(randomIndex).getTarget());
            }
        }
        return randomListWord;
    }

    public char[][] createGameBoard(Map<String, String> randomMapWord, int gridSize) {
        // Step
        int stepX;
        int stepY;
        // Location
        int xPos;
        int yPos;
        // Ending
        int xEnding;
        int yEnding;
        char[][] board = new char[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                board[i][j] = '_';
            }
        }
        String[] orientations = {"left-right", "up-down", "diagonalup", "diagonaldown"};
        for (String word : randomMapWord.keySet()) {
            int wordTargetLength = word.length();
            boolean placed = false;
            while (!placed) {
                Random random = new Random();
                int randomIndex = random.nextInt(Math.abs(4));
                // pick orientation
                String orientation = orientations[randomIndex];
                switch (orientation) {
                    case "up-down":
                        stepX = 0;
                        stepY = 1;
                        break;
                    case "diagonalup":
                        stepX = 1;
                        stepY = -1;
                        break;
                    case "diagonaldown":
                        stepX = 1;
                        stepY = 1;
                        break;
                    default:
                        stepX = 1;
                        stepY = 0;
                        break;
                }
                // pick position
                xPos = random.nextInt(Math.abs(gridSize));
                yPos = random.nextInt(Math.abs(gridSize));
                xEnding = xPos + wordTargetLength * stepX;
                yEnding = yPos + wordTargetLength * stepY;
                if (xEnding < 0 || xEnding >= gridSize) {
                    continue;
                }
                if (yEnding < 0 || yEnding >= gridSize) {
                    continue;
                }

                boolean failed = false;
                // check if the orientation and position satisfy the needs or not
                for (int i = 0; i < wordTargetLength; i++) {
                    char character = word.charAt(i);
                    int newXPos = xPos + i * stepX;
                    int newYPos = yPos + i * stepY;

                    char newPosCharacter = board[newXPos][newYPos];
                    if (newPosCharacter != '_') {
                        if (newPosCharacter == character) {
                            continue;
                        } else {
                            failed = true;
                            break;
                        }
                    }
                }
                // if we cant place in these orientation and position, we need to re-pick them.
                if (failed) {
                    continue;
                }
                // fill the word to the board
                else {
                    for (int i = 0; i < wordTargetLength; i++) {
                        char character = word.charAt(i);
                        int newXPos = xPos + i * stepX;
                        int newYPos = yPos + i * stepY;
                        board[newXPos][newYPos] = character;
                    }
                    placed = true;
                }
            }
        }
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (board[i][j] == '_') {
                    Random random = new Random();
                    char c = (char) (random.nextInt(26) + 'A');
                    board[i][j] = c;
                }
            }
        }
        return board;
    }

    public static void main(String[] args) {
        Game g = new Game();
        Map<String, String> wordList = g.randomPickWord(4, 7);
        char[][] gameBoard = g.createGameBoard(wordList, 13);
        for (String s : wordList.keySet()) {
            System.out.println(s);
        }
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }
}
