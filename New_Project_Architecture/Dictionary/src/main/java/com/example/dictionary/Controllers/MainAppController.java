package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import com.example.dictionary.Models.Small_Function;
import com.example.dictionary.Models.Word;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.dictionary.Models.Dictionary.words;

public class MainAppController extends Application implements Initializable {
    // CÁC ATTRIBUTE CHO SEARCH
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Label wordExplainLabel;
    @FXML
    private ListView<String> wordListView;
    @FXML
    private static String wordTarget;
    private static String wordExplain;

    private ArrayList<String> listTarget;

    private ArrayList<Word> listWord;

    private Small_Function sf = new Small_Function();

    // CÁC ATTRIBUTE CHO SWITCH SCENE
    @FXML Button addWordButton;
    private Stage stage;

    public static String getWordTarget() {
        return wordTarget;
    }

    public static String getWordExplain() {
        return wordExplain;
    }

    private Scene scene;

    //lấy từ vựng được nhập trong thanh search, tìm kiếm và trả về nghĩa của từ đó trên wordExplainLabel
    public void search(ActionEvent event) {
        wordTarget = searchTextField.getText();
        wordExplain = getDic().searchWords(wordTarget).getExplain();
        wordExplainLabel.setText(String.valueOf(wordExplain));
    }

    // khởi tạo listView chứa các từ vựng, khi gõ trên thanh search sẽ nhận keyEvent để cập nhật listView
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listTarget = sf.lookUpWord(words, searchTextField.getText().trim());
            searchTextField.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    updateListView();
                }
            });
        }
        catch (Exception e) {
            throw e;
        }
    }

    // cập nhật lại listView danh sách từ vựng
    public void updateListView() {
        wordListView.getItems().clear();
        listTarget.clear();
        listTarget = sf.lookUpWord(words, searchTextField.getText());
        wordListView.getItems().addAll(listTarget);
    }

    public void switchToAddScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Views/AddView.fxml"));
        // Lấy stage hiện tại
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUpdateScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Views/UpdateView.fxml"));
        // Lấy stage hiện tại
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
