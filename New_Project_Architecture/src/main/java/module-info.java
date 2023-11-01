module com.example.new_project_architecture {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.new_project_architecture to javafx.fxml;
    exports com.example.new_project_architecture;
}