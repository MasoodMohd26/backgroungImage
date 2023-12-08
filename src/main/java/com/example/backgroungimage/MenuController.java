package com.example.backgroungimage;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene2(ActionEvent e) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
//        setupKeyHandlers();
        stage.show();

    }
}
