package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
    private Label wordExplainLabel;
    @FXML
    private ListView<String> wordListView;
    @FXML
    private static String wordTarget;
    @FXML
    private static String wordExplain;
    @FXML
    private AnchorPane mainPane;
    private ArrayList<String> listTarget;

    // CÁC ATTRIBUTE CHO SWITCH SCENE, REMOVE WORD
    @FXML
    Button addWordButton;
    @FXML
    Button updateButton;
    @FXML
    Button removeButton;
    private Stage stage;
    private Scene scene;
    private final AlertController alertController = new AlertController();

    public static String getWordTarget() {
        return wordTarget;
    }

    public static String getWordExplain() {
        return wordExplain;
    }

    //lấy từ vựng được nhập trong thanh search, tìm kiếm và trả về nghĩa của từ đó trên wordExplainLabel
    public void search(ActionEvent event) {
        wordTarget = searchTextField.getText();
        wordExplain = getDic().searchWords(wordTarget).getExplain();
        wordExplainLabel.setText(String.valueOf(wordExplain));
        if (wordTarget == null || wordExplain == null || wordTarget.isEmpty() || wordExplain.isEmpty())
            generalAlert(wordTarget, wordExplain);
    }

    // khởi tạo listView chứa các từ vựng, khi gõ trên thanh search sẽ nhận keyEvent để cập nhật listView
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listTarget = getDic().lookUpWord(words, searchTextField.getText().trim());
            searchTextField.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    updateListView();
                }
            });
            // chọn từ trong list view
            wordListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    wordTarget = wordListView.getSelectionModel().getSelectedItem();
                    wordExplain = getDic().searchWords(wordTarget).getExplain();
                    searchTextField.setText(wordTarget);
                    wordExplainLabel.setText(String.valueOf(wordExplain));
                }
            });
        } catch (Exception e) {
            throw e;
        }
    }

    // cập nhật lại listView danh sách từ vựng
    public void updateListView() {
        wordListView.getItems().clear();
        listTarget.clear();
        listTarget = getDic().lookUpWord(words, searchTextField.getText());
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

    public void remove(ActionEvent event) {
        wordTarget = searchTextField.getText();
        if (wordTarget == null || wordExplain == null || wordTarget.isEmpty() || wordExplain.isEmpty())
            generalAlert(wordTarget, wordExplain);
        else {
            Alert removeAlert = alertController
                    .confirmationAlert("Bạn đang xóa từ: " + wordTarget, "Bạn có chắc chắn muốn xóa từ này?");
            if (removeAlert.showAndWait().get() == ButtonType.OK) {
                getDic().removeWord(wordTarget);
                searchTextField.setText("");
                wordExplainLabel.setText("");
            }
        }
    }

    public void exit(ActionEvent event) {
        Alert exitAlert = alertController
                .confirmationAlert("Bạn đang thoát ra!", "Bạn có chắc chắn muốn thoát?");
        if (exitAlert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
        }
    }

    public void generalAlert(String target, String explain) {
        if (target == null || target.isEmpty()) {
            Alert warningAlert = alertController
                    .warningAlert("Bạn chưa nhập từ!", "Vui lòng hãy nhập từ!");
            warningAlert.showAndWait();

        } else if (explain == null || explain.isEmpty()) {
            Alert warningAlert = alertController
                    .warningAlert("Từ không tồn tại!", "Bạn hãy kiểm tra lại từ vựng!");
            warningAlert.showAndWait();
        }

    }
}
