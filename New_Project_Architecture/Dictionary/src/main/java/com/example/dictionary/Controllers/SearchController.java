package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import com.example.dictionary.Models.DictionaryManagement;
import com.example.dictionary.Models.Small_Function;
import com.example.dictionary.Models.Word;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringJoiner;

import static com.example.dictionary.Models.Dictionary.words;

public class SearchController extends Application implements Initializable {
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Label wordExplainLabel;
    @FXML
    private ListView<String> wordListView;
    @FXML
    String wordTarget;
    String wordExplain;

    ArrayList<String> listTarget;

    ArrayList<Word> listWord;
    public void search(ActionEvent event) {
        wordTarget = searchTextField.getText();
        wordExplain = getDic().searchWords(wordTarget).getExplain();
        wordExplainLabel.setText(String.valueOf(wordExplain));
    }
    Small_Function sf = new Small_Function();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listTarget = sf.lookUpWord(words, searchTextField.getText().trim());
        searchTextField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
//                listTarget.clear();
                handleType();
            }
        });
    }

    public void handleType() {
        wordListView.getItems().clear();
        listTarget = sf.lookUpWord(words, searchTextField.getText().trim());
        wordListView.getItems().addAll(listTarget);
    }
}
