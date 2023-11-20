package com.example.dictionary;

import com.example.dictionary.Models.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch(args);
    }

    DictionaryManagement dic = new DictionaryManagement();

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            dic.importFromFile();
            Parent root = FXMLLoader.load(getClass().getResource("Controllers/Views/MainAppView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("English Learning App");
//            Image icon = new Image("Image/iconApp.png");
//            primaryStage.getIcons().add(icon);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DictionaryManagement getDic() {
        return dic;
    }

    public void setDic(DictionaryManagement dic) {
        this.dic = dic;
    }
}
