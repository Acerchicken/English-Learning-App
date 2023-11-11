package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SettingController extends Application {
    @FXML
    TextField wordTargetTextField;
    @FXML
    TextField wordExplainTextField;
    @FXML
    Button searchButton;
    @FXML
    Button addButton;
    @FXML
    Button updateButton;
    @FXML
    Button removeButton;

    String wordTarget;
    String wordExplain;
    public void search(ActionEvent event) {
        wordTarget = wordTargetTextField.getText();
        wordExplain = getDic().searchWords(wordTarget).getExplain();
        wordExplainTextField.setText(String.valueOf(wordExplain));
    }
    public void add(ActionEvent event) {
        getDic().addWord(wordTargetTextField.getText(), wordExplainTextField.getText());
    }
    public void remove(ActionEvent event) {
        getDic().removeWord(wordTargetTextField.getText());
        wordTargetTextField.setText("");
        wordExplainTextField.setText("");
    }
    public void update(ActionEvent event) {
        getDic().updateWord(wordTargetTextField.getText(), wordExplainTextField.getText());
    }
}
