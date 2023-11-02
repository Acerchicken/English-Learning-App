package com.example.dictionary.Controllers;

import Cmd_Line.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SearchController extends DictionaryManagement {
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Label wordExplainLabel;

    String wordTarget;
    StringBuilder wordExplain;

    DictionaryManagement dic = new DictionaryManagement();


    public void search(ActionEvent event) {
        dic.importFromFile();
        wordTarget = searchTextField.getText();
        wordExplain = dic.searchWords(wordTarget);
        wordExplainLabel.setText(String.valueOf(wordExplain));
    }
}
