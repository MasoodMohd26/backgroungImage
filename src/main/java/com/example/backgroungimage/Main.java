package com.example.backgroungimage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("startMenu.fxml"));
        System.out.println(getClass());
        System.out.println("hello world!!!");
        System.out.println("heoooo");
        Parent root = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setHeight(420);
        stage.setWidth( 600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}