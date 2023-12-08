module com.example.backgroungimage {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.backgroungimage to javafx.fxml;
    exports com.example.backgroungimage;
}