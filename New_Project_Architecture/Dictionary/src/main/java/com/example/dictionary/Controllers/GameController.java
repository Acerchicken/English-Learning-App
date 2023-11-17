package com.example.dictionary.Controllers;

import com.example.dictionary.Game.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController extends Game implements Initializable {
    private GridPane boardPane;
    @FXML
    private AnchorPane mainGamePane;
    private Scene scene;
    private Button[] boardButtons;

    private final int GRIDSIZE = 20;
    private final int SIZE = 480;

    private final int GAP = 1;

    private ArrayList<String> wordList;
    private char[][] gameBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Game game = new Game();
        wordList = game.randomPickWord(3);
        gameBoard = game.createGameBoard(wordList, GRIDSIZE);
        createBoard(SIZE);
    }
    public void createBoard(int size) {
        boardPane = new GridPane();
        boardPane.setMinSize(size, size);
        boardPane.setMaxSize(size, size);
        boardPane.setLayoutX(900 - SIZE);
        boardPane.setLayoutY((double) (600 - SIZE) /2);
        boardButtons = new Button[GRIDSIZE*GRIDSIZE];
        for (int i = 0; i < GRIDSIZE*GRIDSIZE; i++) {
            boardButtons[i] = new Button("Button #" + (i + 1));
            boardButtons[i].setMinSize((double) size /GRIDSIZE, (double) size /GRIDSIZE);
            boardButtons[i].setFont(new Font(8));
        }
        int buttonIndex = 0;
        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                boardButtons[buttonIndex].setText(String.valueOf(gameBoard[i][j]));
                boardPane.add(boardButtons[buttonIndex], j, i);
                buttonIndex++;
            }
        }
        mainGamePane.getChildren().add(boardPane);
    }
}
