package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import com.example.dictionary.Models.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchController extends Application {
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Label wordExplainLabel;

    String wordTarget;
    String wordExplain;


    public void search(ActionEvent event) {
        wordTarget = searchTextField.getText();
        wordExplain = getDic().searchWords(wordTarget).getExplain();
        wordExplainLabel.setText(String.valueOf(wordExplain));
    }
    public void remove(ActionEvent event) {

    }
}
