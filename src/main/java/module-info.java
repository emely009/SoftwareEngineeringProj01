module com.example.seproj01 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.seproj01 to javafx.fxml;
    exports com.example.seproj01;
}