package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import javafx.scene.control.Alert;

public class AlertController extends Application {
    public Alert confirmationAlert(String header, String content) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation!!!");
        confirmationAlert.setHeaderText(header);
        confirmationAlert.setContentText(content);
        return confirmationAlert;
    }
    public Alert warningAlert(String header, String content) {
        Alert warningAlert = new Alert(Alert.AlertType.WARNING);
        warningAlert.setTitle("Warning!!!");
        warningAlert.setHeaderText(header);
        warningAlert.setContentText(content);
        return warningAlert;
    }
}
