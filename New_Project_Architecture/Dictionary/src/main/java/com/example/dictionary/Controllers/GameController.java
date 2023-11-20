package com.example.dictionary.Controllers;

import com.example.dictionary.Game.Game;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class GameController extends Game implements Initializable {
    private GridPane boardPane;
    @FXML
    private AnchorPane mainGamePane;
    private Scene scene;
    private Button[][] boardButtons;

    private final int GRIDSIZE = 10;
    private final int SIZE = 480;

    private final int GAP = 1;

    private Map<String, String> wordMap;
    private char[][] gameBoard;

    private int firstPointX;
    private int firstPointY;

    private boolean status;

    private StringBuilder res;
    @FXML
    private ListView<String> winList;
    @FXML
    private Label meaning;
    @FXML
    private Label questionLabel;
    @FXML
    private Button play;

    private boolean winStatus;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startGame(SIZE);
    }

    public void startGame(int size) {
        winStatus = false;
        Game game = new Game();
        wordMap = game.randomPickWord(3, 7);
        gameBoard = game.createGameBoard(wordMap, GRIDSIZE);
        status = false;
        res = new StringBuilder("");
        StringBuilder question = new StringBuilder("A word with:\n");
        for (String s : wordMap.keySet()) {
            question.append("- ").append(s.length()).append(" characters\n");
            System.out.println(s);
        }
        questionLabel.setText(String.valueOf(question));
        for (String s : wordMap.values()) {
            System.out.println(s);
        }
        winList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String wordTarget = winList.getSelectionModel().getSelectedItem();
                String wordExplain = getDic().searchWords(wordTarget).getExplain();
                meaning.setText(String.valueOf(wordExplain));
            }
        });
        winList.getItems().clear();
        boardPane = new GridPane();
        boardPane.setMinSize(size, size);
        boardPane.setMaxSize(size, size);
        boardPane.setLayoutX(900 - SIZE);
        boardPane.setLayoutY((double) (600 - SIZE) / 2);
        boardButtons = new Button[GRIDSIZE][GRIDSIZE];
        boardPane.setDisable(true);
        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                boardButtons[j][i] = new Button("Button #" + (j + i * GRIDSIZE + 1));
                boardButtons[j][i].setMinSize((double) size / GRIDSIZE, (double) size / GRIDSIZE);
                boardButtons[j][i].setFont(new Font(25 - GRIDSIZE));
                boardButtons[j][i].setStyle("-fx-background-color: yellow; -fx-font-weight: bold;");
                boardPane.setGridLinesVisible(false);
            }
        }
        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                int finalI = i;
                int finalJ = j;
                boardButtons[j][i].setOnMouseEntered(event -> {
                    int rowIndex = GridPane.getRowIndex(boardButtons[finalJ][finalI]);
                    int columnIndex = GridPane.getColumnIndex(boardButtons[finalJ][finalI]);
                    if (status) {
                        if (rowIndex == firstPointX) {
                            if (firstPointY <= columnIndex) {
                                res = new StringBuilder("");
                                for (int k = firstPointY; k <= columnIndex; k++) {
                                    res.append(boardButtons[k][rowIndex].getText());
                                    boardButtons[k][rowIndex].setStyle("-fx-background-color: green;-fx-font-weight: bold;");
                                }
                            } else {
                                res = new StringBuilder("");
                                for (int k = firstPointY; k >= columnIndex; k--) {
                                    res.append(boardButtons[k][rowIndex].getText());
                                    boardButtons[k][rowIndex].setStyle("-fx-background-color: green;-fx-font-weight: bold;");
                                }
                            }
                        } else if (columnIndex == firstPointY) {
                            if (firstPointX <= rowIndex) {
                                res = new StringBuilder("");
                                for (int k = firstPointX; k <= rowIndex; k++) {
                                    res.append(boardButtons[columnIndex][k].getText());
                                    boardButtons[columnIndex][k].setStyle("-fx-background-color: green;-fx-font-weight: bold;");
                                }
                            } else {
                                res = new StringBuilder("");
                                for (int k = firstPointX; k >= rowIndex; k--) {
                                    res.append(boardButtons[columnIndex][k].getText());
                                    boardButtons[columnIndex][k].setStyle("-fx-background-color: green;-fx-font-weight: bold;");
                                }
                            }
                        } else if (Math.abs(rowIndex - firstPointX) == Math.abs(columnIndex - firstPointY)) {
                            if (firstPointX <= rowIndex) {
                                if (firstPointY <= columnIndex) {
                                    res = new StringBuilder("");
                                    for (int k = 0; k <= rowIndex - firstPointX; k++) {
                                        res.append(boardButtons[firstPointY + k][firstPointX + k].getText());
                                        boardButtons[firstPointY + k][firstPointX + k].setStyle("-fx-background-color: green;-fx-font-weight: bold;");
                                    }
                                } else {
                                    res = new StringBuilder("");
                                    for (int k = 0; k <= rowIndex - firstPointX; k++) {
                                        res.append(boardButtons[firstPointY - k][firstPointX + k].getText());
                                        boardButtons[firstPointY - k][firstPointX + k].setStyle("-fx-background-color: green;-fx-font-weight: bold;");
                                    }
                                }
                            } else {
                                if (firstPointY <= columnIndex) {
                                    res = new StringBuilder("");
                                    for (int k = firstPointX - rowIndex; k >= 0; k--) {
                                        res.append(boardButtons[firstPointY + k][firstPointX - k].getText());
                                        boardButtons[firstPointY + k][firstPointX - k].setStyle("-fx-background-color: green;-fx-font-weight: bold;");
                                    }
                                } else {
                                    res = new StringBuilder("");
                                    for (int k = firstPointX - rowIndex; k >= 0; k--) {
                                        res.append(boardButtons[firstPointY - k][firstPointX - k].getText());
                                        boardButtons[firstPointY - k][firstPointX - k].setStyle("-fx-background-color: green;-fx-font-weight: bold;");
                                    }
                                }
                            }
                        } else {
                            for (int k = 0; k < GRIDSIZE; k++) {
                                for (int l = 0; l < GRIDSIZE; l++) {
                                    boardButtons[l][k].setStyle("-fx-background-color: yellow; -fx-font-weight: bold;");
                                }
                            }
                            boardButtons[firstPointY][firstPointX].setStyle("-fx-background-color: green;-fx-font-weight: bold;");
                        }
                    }
                });
                boardButtons[j][i].setOnMouseExited(event -> {
                    boardButtons[finalJ][finalI].setStyle("-fx-background-color: yellow; -fx-font-weight: bold;");
                });
                boardButtons[j][i].setOnMouseClicked(event -> {
                    if (!status) {
                        status = true;
                        firstPointX = GridPane.getRowIndex(boardButtons[finalJ][finalI]);
                        firstPointY = GridPane.getColumnIndex(boardButtons[finalJ][finalI]);
                        for (int k = 0; k < GRIDSIZE; k++) {
                            for (int l = 0; l < GRIDSIZE; l++) {
                                boardButtons[l][k].setStyle("-fx-background-color: yellow; -fx-font-weight: bold;");
                            }
                        }
                        boardButtons[firstPointY][firstPointX].setStyle("-fx-background-color: green;-fx-font-weight: bold;");
                    }
                    else {
                        status = false;
                        if (wordMap.containsKey(res.toString()) || wordMap.containsKey(res.reverse().toString())) {
                            winList.getItems().add(wordMap.get(res.toString()));
                            String wordTarget = wordMap.get(res.toString());
                            String wordExplain = getDic().searchWords(wordTarget).getExplain();
                            meaning.setText(String.valueOf(wordExplain));
                            wordMap.remove(res.toString());
                            wordMap.remove(res.reverse().toString());
                        }
                        else {
                            System.out.println("WRONG");
                        }
                        if (wordMap.isEmpty()) {
                            System.out.println("you win the game");
                            boardPane.setDisable(true);
                            winStatus = true;
                        }
                        for (int k = 0; k < GRIDSIZE; k++) {
                            for (int l = 0; l < GRIDSIZE; l++) {
                                boardButtons[l][k].setStyle("-fx-background-color: yellow; -fx-font-weight: bold;");
                            }
                        }
                    }
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

    public void control(ActionEvent event) {
        if (boardPane.isDisable()) {
            boardPane.setDisable(false);
        }
        else {
            boardPane.setDisable(true);
        }
        if (winStatus) {
            startGame(SIZE);
            boardPane.setDisable(false);
        }
    }
}
