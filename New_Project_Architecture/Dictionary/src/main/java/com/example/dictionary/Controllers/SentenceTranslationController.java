package com.example.dictionary.Controllers;

import com.example.dictionary.Application;
import com.example.dictionary.TranslateSentence.Language;
import com.example.dictionary.TranslateSentence.Translator;

public class SentenceTranslationController extends Application {

    //Test chuc nang dich
    public String testTranslateSentence() {
        Translator translator = new Translator();

        Language language = new Language();
        language.setSourceLanguage("en");
        language.setTargetLanguage("vi");
        String rootSentence = "Hello world";

        return translator.translateSentence(rootSentence, language);
    }
}
