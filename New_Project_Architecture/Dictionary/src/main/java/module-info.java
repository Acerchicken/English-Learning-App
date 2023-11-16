module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires google.cloud.translate;
    requires com.google.auth;
    requires com.google.auth.oauth2;
    requires google.cloud.core;
    requires gtranslateapi;
    requires freetts;


    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
    exports com.example.dictionary.Controllers;
    opens com.example.dictionary.Controllers to javafx.fxml;
}