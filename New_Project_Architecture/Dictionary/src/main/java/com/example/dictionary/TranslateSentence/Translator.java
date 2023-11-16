package com.example.dictionary.TranslateSentence;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.FileInputStream;
import java.io.IOException;

public class Translator {
    String credentialsPath = "/Users/mac/Documents/GitHub/English-Learning-App/New_Project_Architecture/Dictionary/target/classes/GoogleTranslateAPICredentials/testapi-402514-71998670d628.json";
    private Translate translate;
    public Translator() {
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsPath));
            this.translate = TranslateOptions.newBuilder().setCredentials(credentials).build().getService();
        } catch(IOException e){
            throw new RuntimeException("Không thể đọc credentials từ tệp JSON.", e);
        }
    }

    public String translateSentence(String rootSentence, Language language) {
        Translation translation = translate.translate(rootSentence, Translate.TranslateOption.sourceLanguage(language.getSourceLanguage()), Translate.TranslateOption.targetLanguage(language.getTargetLanguage()));
        return translation.getTranslatedText();
    }

    public void swapLanguage(Language language) {
        String tmp = language.getSourceLanguage();
        language.setSourceLanguage(language.getTargetLanguage());
        language.setTargetLanguage(tmp);
    }
}
