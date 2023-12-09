package com.example.backgroungimage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverController {
    public static int score = 0;
    @FXML
    public Text myScore;
    @FXML
    public Text myScore1;

    private Stage stage;
    private Scene scene;
    private Parent root;
    void Update()
    {
        myScore.setText(Integer.toString(score));
    }
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
    public void setGameScore(String s)
    {
        myScore.setText(s);
    }
    public void setGameHighScore(String s)
    {
        myScore1.setText(s);
    }

}
