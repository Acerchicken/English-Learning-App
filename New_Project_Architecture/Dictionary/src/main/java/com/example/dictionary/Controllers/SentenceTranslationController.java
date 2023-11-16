package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import com.example.dictionary.TranslateSentence.Data;
import com.example.dictionary.TranslateSentence.Language;
import com.example.dictionary.TranslateSentence.Translator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SentenceTranslationController extends Application implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    TextArea rootSentence;
    @FXML
    TextFlow translatedSentence;
    @FXML
    private Button translate;
    @FXML
    private Button swapLanguageButton;
    @FXML
    private ComboBox<String> listRootLanguage;
    @FXML
    private ComboBox<String> listTargetLanguage;
    @FXML
    private Button back;
    Data listLanguage = new Data();

    Language language = new Language();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listRootLanguage.getItems().addAll(listLanguage.getLanguageList());
        listTargetLanguage.getItems().addAll(listLanguage.getLanguageList());
    }

    public void switchToAppScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Views/MainAppView.fxml"));
        // Lấy stage hiện tại
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void sentenceTranslation(ActionEvent event) throws Exception {
        if (!translatedSentence.getChildren().isEmpty()) {
            translatedSentence.getChildren().clear();
        }
        language.setSourceLanguage(listLanguage.getLanguageIndex(listRootLanguage.getValue()));
        language.setTargetLanguage(listLanguage.getLanguageIndex(listTargetLanguage.getValue()));
        Translator translator = new Translator();
        Text text = new Text(translator.translateSentence(rootSentence.getText(), language));
        translatedSentence.getChildren().add(text);
    }

    public void setSwapLanguageButton() {
        String tmp = listRootLanguage.getValue();
        listRootLanguage.setValue(listTargetLanguage.getValue());
        listTargetLanguage.setValue(tmp);
        if (!Objects.equals(rootSentence.getText(), "") && !translatedSentence.getChildren().isEmpty()) {
            Text previousRootSentence = new Text(rootSentence.getText());
            rootSentence.clear();
            StringBuilder stringBuilder = new StringBuilder();
            for (Node node : translatedSentence.getChildren()) {
                if (node instanceof Text) {
                    String text = ((Text) node).getText();
                    stringBuilder.append(text);
                }
            }
            rootSentence.setText(stringBuilder.toString());
            translatedSentence.getChildren().clear();
            translatedSentence.getChildren().add(previousRootSentence);
        }
    }
}
