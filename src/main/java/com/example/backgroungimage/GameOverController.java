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
    @FXML
    public Text myGameOverCherry;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void initialize()
    {
        try {
            System.out.println("Hi");
            myGameOverCherry.setText((Integer.toString(SceneController.readCherryCnt())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//    void Update()
//    {
//        myScore.setText(Integer.toString(score));
//        try {
//            System.out.println("Hi");
//            myGameOverCherry.setText((Integer.toString(SceneController.readCherryCnt())));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public void switchToHome(ActionEvent e) throws IOException
    {
        SceneController.writeScore(0);
        Parent root = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
//        setupKeyHandlers();
        stage.show();

    }
    public void switchToGame(ActionEvent e) throws IOException
    {
        SceneController.writeScore(0);


        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
//        setupKeyHandlers();
        stage.show();

    }
    public void switchToGame1(ActionEvent e) throws IOException
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
    public void revive(ActionEvent e)
    {
        int current_cherry = Integer.parseInt(myGameOverCherry.getText());
        System.out.println("Current cherry"+current_cherry);
        if (current_cherry>=3)
        {
            current_cherry -=3;
            try {
                SceneController.writeCherryCnt(current_cherry);
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
            try {
                switchToGame1(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else{

            try {
                SceneController.writeScore(0);
                switchToHome(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
