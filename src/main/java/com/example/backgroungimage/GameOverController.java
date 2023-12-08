package com.example.backgroungimage;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToHome(ActionEvent e) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
//        setupKeyHandlers();
        stage.show();

    }
    public void switchToGame(ActionEvent e) throws IOException
    {

        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
//        setupKeyHandlers();
        stage.show();

    }
//    public void switchToGameMouseClick(MouseEvent e) throws IOException
//    {
//        try {
//            switchToGame( e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

}
