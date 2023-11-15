package com.example.dictionary.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

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

    AtomicBoolean checkTarget;
    AtomicBoolean checkExplain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordTextField.setText(getWordTarget());
        wordExplainTextArea.setText(getWordExplain());
        updateButton.setDisable(true);
        checkTarget = new AtomicBoolean(false);
        checkExplain = new AtomicBoolean(false);
        wordTextField.textProperty().addListener((observable1, oldValue1, newValue1) -> {
            String oldTextTarget = getWordTarget().trim();
            String newTextTarget = wordTextField.getText().trim();
            if (!oldTextTarget.equals(newTextTarget) || checkExplain.get()) {
                updateButton.setDisable(false);
                if (!oldTextTarget.equals(newTextTarget)) {
                    checkTarget.set(true);
                }
                else {
                    checkTarget.set(false);
                }
            }
            else {
                updateButton.setDisable(true);
            }
        });
        wordExplainTextArea.textProperty().addListener((observable2, oldValue2, newValue2) -> {

            String oldTextExplain = getWordExplain().trim();
            String newTextExplain = wordExplainTextArea.getText().trim();
            if (!oldTextExplain.equals(newTextExplain) || checkTarget.get()) {
                updateButton.setDisable(false);
                if (!oldTextExplain.equals(newTextExplain)) {
                    checkExplain.set(true);
                }
                else {
                    checkExplain.set(false);
                }
            }
            else {
                updateButton.setDisable(true);
            }
        });
    }

    public void update(ActionEvent event) {
        if (wordTextField.getText() == null || wordExplainTextArea.getText() == null || wordTextField.getText().isEmpty() || wordExplainTextArea.getText().isEmpty()) {
            AlertController.warningAlert(wordTextField.getText(), wordExplainTextArea.getText());
        }
        else
        {
            Alert updateConfirmation = AlertController.confirmationAlert("Bạn đang cập nhật từ vựng", "Bạn có chắc chắn bạn muốn cập nhật?");
            if (updateConfirmation.showAndWait().get() == ButtonType.OK) {
                getDic().updateWord(wordTextField.getText(), wordExplainTextArea.getText());
            }

        }
    }

    public void switchToAppScene(ActionEvent event) throws IOException {
        if (!updateButton.isDisable()) {
            Alert backConfirmation = AlertController.confirmationAlert("Bạn đang chỉnh sửa!", "Bạn có chắc chắn muốn thoát?");
            if (backConfirmation.showAndWait().get() == ButtonType.OK) {
                Parent root = FXMLLoader.load(getClass().getResource("Views/MainAppView.fxml"));
                // Lấy stage hiện tại
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else {
            Parent root = FXMLLoader.load(getClass().getResource("Views/MainAppView.fxml"));
            // Lấy stage hiện tại
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
