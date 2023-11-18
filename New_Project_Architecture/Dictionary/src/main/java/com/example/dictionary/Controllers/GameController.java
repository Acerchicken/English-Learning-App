package com.example.dictionary.Controllers;

import com.example.dictionary.Game.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
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
    private Button[][] boardButtons;

    private final int GRIDSIZE = 14;
    private final int SIZE = 480;

    private final int GAP = 1;

    private ArrayList<String> wordList;
    private char[][] gameBoard;

    private int firstPointX;
    private int firstPointY;


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
        boardPane.setLayoutY((double) (600 - SIZE) / 2);
        boardButtons = new Button[GRIDSIZE][GRIDSIZE];
        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                boardButtons[j][i] = new Button("Button #" + (j + i * GRIDSIZE + 1));
                boardButtons[j][i].setMinSize((double) size / GRIDSIZE, (double) size / GRIDSIZE);
                boardButtons[j][i].setFont(new Font(8));
                boardButtons[j][i].setStyle("-fx-background-color: yellow");
                boardPane.setGridLinesVisible(false);
                int finalI = i;
                int finalJ = j;
                boardButtons[j][i].setOnMouseEntered(event -> {
                    int rowIndex = GridPane.getRowIndex(boardButtons[finalJ][finalI]);
                    int columnIndex = GridPane.getColumnIndex(boardButtons[finalJ][finalI]);
                    if (rowIndex == firstPointX) {
                        if (firstPointY <= columnIndex) {
                            for (int k = firstPointY; k <= columnIndex; k++) {
                                boardButtons[k][rowIndex].setStyle("-fx-background-color: green;");
                            }
                        } else {
                            for (int k = firstPointY; k >= columnIndex; k--) {
                                boardButtons[k][rowIndex].setStyle("-fx-background-color: green;");
                            }
                        }
                    } else if (columnIndex == firstPointY) {
                        if (firstPointX <= rowIndex) {
                            for (int k = firstPointX; k <= rowIndex; k++) {
                                boardButtons[columnIndex][k].setStyle("-fx-background-color: green;");
                            }
                        } else {
                            for (int k = firstPointX; k >= rowIndex; k--) {
                                boardButtons[columnIndex][k].setStyle("-fx-background-color: green;");
                            }
                        }
                    } else if (Math.abs(rowIndex - firstPointX) == Math.abs(columnIndex - firstPointY)) {
                        if (firstPointX <= rowIndex) {
                            if (firstPointY <= columnIndex) {
                                for (int k = 0; k <= rowIndex - firstPointX; k++) {
                                    boardButtons[firstPointY + k][firstPointX + k].setStyle("-fx-background-color: green;");
                                }
                            } else {
                                for (int k = 0; k <= rowIndex - firstPointX; k++) {
                                    boardButtons[firstPointY - k][firstPointX + k].setStyle("-fx-background-color: green;");
                                }
                            }
                        } else {
                            if (firstPointY <= columnIndex) {
                                for (int k = firstPointX - rowIndex; k >= 0; k--) {
                                    boardButtons[firstPointY + k][firstPointX - k].setStyle("-fx-background-color: green;");
                                }
                            } else {
                                for (int k = firstPointX - rowIndex; k >= 0; k--) {
                                    boardButtons[firstPointY - k][firstPointX - k].setStyle("-fx-background-color: green;");
                                }
                            }
                        }
                    } else {
                        for (int k = 0; k < GRIDSIZE; k++) {
                            for (int l = 0; l < GRIDSIZE; l++) {
                                boardButtons[l][k].setStyle("-fx-background-color: yellow;");
                            }
                        }
                        boardButtons[firstPointY][firstPointX].setStyle("-fx-background-color: green;");
                    }
                });
                boardButtons[j][i].setOnMouseExited(event -> {
                    boardButtons[finalJ][finalI].setStyle("-fx-background-color: yellow;");
                });
                boardButtons[j][i].setOnMouseClicked(event -> {
                    firstPointX = GridPane.getRowIndex(boardButtons[finalJ][finalI]);
                    firstPointY = GridPane.getColumnIndex(boardButtons[finalJ][finalI]);
                    boardButtons[finalJ][finalI].setStyle("-fx-background-color: green;");
                });
            }
        }

        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                boardButtons[j][i].setText(String.valueOf(gameBoard[i][j]));
                boardPane.add(boardButtons[j][i], j, i);

            }
        }
        mainGamePane.getChildren().add(boardPane);
    }
}
