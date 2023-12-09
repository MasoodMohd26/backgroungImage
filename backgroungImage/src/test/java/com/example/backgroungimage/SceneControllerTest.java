package com.example.backgroungimage;

import javafx.scene.transform.Rotate;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SceneControllerTest {

    @Test
    void updateHighScore1() {

        SceneController sceneController = new SceneController();
        int currentHighScore = 1000;
        int newHighScore = 1500;

        int updatedHighScore= sceneController.updateHighScore(newHighScore);

        assertEquals(newHighScore, updatedHighScore, "High score should be updated");
    }

    @Test
    void updateHighScore2() {
        SceneController sceneController = new SceneController();

        int currentHighScore = 1000;
        int newHighScore = 700;

        int updatedHighScore= sceneController.updateHighScore(newHighScore);

        assertNotEquals(1000, updatedHighScore, "updated high score should be 700");
    }



    @Test
    void stickDown_shouldIncrementAngle() {
        Rotate rotate = new Rotate(45);
        SceneController sceneController = new SceneController();
        sceneController.stickDown(rotate);
        assertEquals(46, (int) rotate.getAngle(), "Angle should be incremented by 1");
    }


    @Test
    void readHighScore() throws IOException {
        SceneController sceneController = new SceneController();
        int newHighScore = 96;
        int updatedHighScore= sceneController.updateHighScore(newHighScore);
        int highScoreRead=sceneController.readHighScore();
        assertEquals(highScoreRead,updatedHighScore,"They should be equal!");
    }
}