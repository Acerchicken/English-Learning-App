package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import javafx.scene.control.Alert;

public class AlertController extends Application {
    public static Alert confirmationAlert(String header, String content) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation!!!");
        confirmationAlert.setHeaderText(header);
        confirmationAlert.setContentText(content);
        return confirmationAlert;
    }
    public static Alert warningAlert(String header, String content) {
        Alert warningAlert = new Alert(Alert.AlertType.WARNING);
        warningAlert.setTitle("Warning!!!");
        warningAlert.setHeaderText(header);
        warningAlert.setContentText(content);
        return warningAlert;
    }
    public static void generalAlert(String target, String explain) {
        if (target == null || target.isEmpty()) {
            Alert warningAlert =
                    warningAlert("Bạn chưa nhập từ hoặc chưa tìm từ!", "Vui lòng hãy nhập từ hoặc bấm tìm từ!");
            warningAlert.showAndWait();
        } else if (explain == null || explain.isEmpty()) {
            Alert warningAlert =
                    warningAlert("Từ không tồn tại!", "Bạn hãy kiểm tra lại từ vựng!");
            warningAlert.showAndWait();
        }
    }
}
