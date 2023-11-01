module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
    exports com.example.dictionary.Controllers;
    opens com.example.dictionary.Controllers to javafx.fxml;
}