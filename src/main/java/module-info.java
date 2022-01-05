module com.example.projectteam07phase2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectteam07phase2 to javafx.fxml;
    exports com.example.projectteam07phase2;
}