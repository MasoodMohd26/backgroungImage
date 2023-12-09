module com.example.backgroungimage {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.backgroungimage to javafx.fxml;
    exports com.example.backgroungimage;
}