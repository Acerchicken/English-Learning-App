package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddController extends Application {
    @FXML
    private TextField wordTextField;
    @FXML
    private TextField wordExplainTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    private Stage stage;
    private Scene scene;

    public void save(ActionEvent event) throws IOException {
        getDic().addWord(wordTextField.getText(), wordExplainTextField.getText());
        getDic().exportToFile();
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
