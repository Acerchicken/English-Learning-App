package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddController extends Application {
    @FXML
    private TextField wordTextField;
    @FXML
    private TextArea wordExplainTextArea;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    private Stage stage;
    private Scene scene;

    public void save(ActionEvent event) throws IOException {
        if (wordTextField.getText() == null || wordExplainTextArea.getText() == null || wordTextField.getText().isEmpty() || wordExplainTextArea.getText().isEmpty()) {
            AlertController.warningAlert(wordTextField.getText(), wordExplainTextArea.getText());
        }
        else
        {
            Alert updateConfirmation = AlertController.confirmationAlert("Bạn đang thêm từ vựng", "Bạn có chắc chắn bạn muốn thêm?");
            if (updateConfirmation.showAndWait().get() == ButtonType.OK) {
                getDic().addWord(wordTextField.getText(), wordExplainTextArea.getText());
                getDic().exportToFile();
                wordTextField.clear();
                wordExplainTextArea.clear();
            }
        }
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
