package com.example.dictionary.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateController extends MainAppController implements Initializable {
    @FXML
    TextField wordTextField;
    @FXML
    TextArea wordExplainTextArea;
    @FXML
    Button updateButton;
    @FXML
    Button backButton;

    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordTextField.setText(getWordTarget());
        wordExplainTextArea.setText(getWordExplain());
    }

    public void update(ActionEvent event) {
        getDic().updateWord(wordTextField.getText(), wordExplainTextArea.getText());
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
