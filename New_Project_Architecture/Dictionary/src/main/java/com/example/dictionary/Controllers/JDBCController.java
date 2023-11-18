package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import com.example.dictionary.EnglishDefinition.JDBC;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class JDBCController extends Application implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML
    Button removeButton;
    @FXML
    Button backButton;
    @FXML
    TextField searchWord;
    @FXML
    Button searchButton;
    @FXML
    ListView<String> suggestWordList;
    @FXML
    TextFlow EnglishDefinition;
    @FXML
    ScrollPane scrollPane = new ScrollPane(EnglishDefinition);

    String wordTarget;
    public void search(ActionEvent event) {
        wordTarget = searchWord.getText();
        getWordFromSuggestList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchWord.textProperty().addListener((observable, oldValue, newValue) -> {
            String query = "select word from entries where word like " + "'" + newValue;
            if (query.contains(String.valueOf('%'))) {
                query.replace(String.valueOf('%'), "");
            }
            query += "%'";
            JDBC suggest = new JDBC();
            ArrayList<String> suggestWords;
            suggestWords = suggest.lookUpWords(query);
            if (!suggestWordList.getItems().isEmpty()) {
                suggestWordList.getItems().clear();
                suggestWords.clear();
                suggestWords = suggest.lookUpWords(query);
            }
            suggestWordList.getItems().addAll(suggestWords);
        });

        suggestWordList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            wordTarget = suggestWordList.getSelectionModel().getSelectedItem();
            getWordFromSuggestList();
        });
    }

    private void getWordFromSuggestList() {
        String query = "select wordtype, definition from entries where word = " + "\"" + wordTarget + "\"";
        JDBC test = new JDBC();
        Text text = new Text(test.executeQuery(query));
        if (!EnglishDefinition.getChildren().isEmpty()) {
            EnglishDefinition.getChildren().clear();
        }
        EnglishDefinition.getChildren().add(text);
    }

    public void removeWordController() {
        wordTarget = searchWord.getText();
        String query = "delete from entries where word = " + "\"" + wordTarget + "\"";
        JDBC remove = new JDBC();
        remove.removeWord(query);
    }

    public void switchToAppScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Views/MainAppView.fxml"));
        // Lấy stage hiện tại
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
