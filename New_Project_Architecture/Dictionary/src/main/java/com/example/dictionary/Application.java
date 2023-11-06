package com.example.dictionary;

import com.example.dictionary.Models.DictionaryManagement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            Parent root = FXMLLoader.load(getClass().getResource("Views/SearchView.fxml"));
            Scene scene = new Scene(root);
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