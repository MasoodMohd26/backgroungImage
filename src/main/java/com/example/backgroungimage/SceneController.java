package com.example.backgroungimage;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.Random;

public class SceneController {
    Rotate rotate;
    Timeline timeline;
    private boolean stickLock=true;

    @FXML
    private ImageView myStickHero;
    @FXML
    private Rectangle myPillar1;
    @FXML
    private Rectangle myPillar2;
    @FXML
    private Rectangle myStick;
    @FXML
    private Text myScore;
    @FXML
    private ImageView cherry;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean spaceBarPressed = false;
    private int myHighScore;


    @FXML
    private AnchorPane anchorPane;
    private boolean isAlive;
    private int aliveCnt;
    Rectangle pillar;
    private boolean isSPressed = false;
    RotateTransition flipTransition;
    Timeline heroDrops;
    private boolean isCherry =false;
    private int cherryCount=0;
    @FXML
    private Text myCherryCnt;


    //    public void createStick() throws IOException {
//        // Create a new rectangle (stick) and add it to the anchorPane
////        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
//        stick = new Rectangle(10, 50, Color.BLACK); // Initial dimensions, adjust as needed
//        anchorPane.getChildren().add(stick);

//    }



    public static void writeHighScore(int newHighScore) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("HScore.txt"))) {
            writer.write(String.valueOf(newHighScore));
        }
    }
    public static int readHighScore() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("HScore.txt"))) {
            String line = reader.readLine();
            return (line != null) ? Integer.parseInt(line) : 0;
        }
    }
    public static void writeCherryCnt(int chcnt) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CherryCnt.txt"))) {
            writer.write(String.valueOf(chcnt));
        }
    }
    public static int readCherryCnt() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("CherryCnt.txt"))) {
            String line = reader.readLine();
            return (line != null) ? Integer.parseInt(line) : 0;
        }
    }

    public static void writeScore(int chcnt) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Score.txt"))) {
            writer.write(String.valueOf(chcnt));
        }
    }
    public static int readScore() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Score.txt"))) {
            String line = reader.readLine();
            return (line != null) ? Integer.parseInt(line) : 0;
        }
    }


    public static int updateHighScore(int currentScore) {
        int highScore = 0;
        try {
            // Read the current high score from the file
            highScore = readHighScore();

            // Compare the current score with the high score
            if (currentScore > highScore) {

                // If the current score is greater, update the high score in the file
                System.out.println("update"+ highScore);
                writeHighScore(currentScore);
                System.out.println("Congratulations! New high score: " + currentScore);
                highScore = currentScore;
            } else {
                System.out.println("not updated"+ highScore);
                System.out.println("Current score is not higher than the existing high score.");
            }
        } catch (IOException e) {
            System.err.println("Error reading or writing high score: " + e.getMessage());
        }
        finally
        {
            return highScore;
        }
    }

    public void switchToGameOver(ActionEvent e) throws IOException
    {

//        Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));

        myHighScore = updateHighScore(aliveCnt);

//        GameOverController.score = aliveCnt;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
        root = loader.load();
        GameOverController g = loader.getController();
        String sc =Integer.toString(aliveCnt);
        g.setGameScore(sc);
        g.setGameHighScore(Integer.toString(myHighScore));


        // Get the stage from the event's source
        Stage stage = (Stage) anchorPane.getScene().getWindow();

        // Set the new scene on the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private Rectangle createPillar() {
        // Create a new rectangle (pillar)
        pillar = new Rectangle(20, 150, Color.BLUE); // Adjust dimensions as needed
        double min = 50+myPillar2.getWidth();
        double max = 300;
        double min_w = 15;
        double max_w = 100;
        double randomX = Math.random() * (max - min) + min;
        double randomX1 = Math.random() * (max_w - min_w) + min_w;
//        cherry = new ImageView("D:\\Coding\\Java\\javaPractice\\backgroungImage\\src\\main\\resources\\com\\example\\backgroungimage\\cherry-clipart-animated-6-removebg-preview.png");
//        cherry.setLayoutX(1);
       // anchorPane.getChildren().add(cherry);
//        cherry.setFitHeight(20);
//        cherry.setFitWidth(20);
        double minch = 10+myPillar2.getWidth();
        double maxch = myPillar2.getLayoutX()+randomX-30;
//       double randomcherry = Math.random()*(maxch-minch)+minch;

        pillar.setLayoutX(myPillar2.getLayoutX() + randomX); // Adjust the initial X position as needed
//        cherry.setLayoutX(myPillar2.getLayoutX() + randomcherry);
//        cherry.setLayoutY(291);
        pillar.setLayoutY(281); // Adjust the initial Y position as needed
        pillar.setWidth(randomX1);

        // Add the rectangle to the AnchorPane
//        anchorPane.getChildren().add(pillar);

        return pillar;
    }
    public void heroMarching()
    {
        Timeline heroMarching;
        if (isAlive){
            heroMarching = new Timeline(
                    new KeyFrame(Duration.seconds(2), new KeyValue(myStickHero.layoutXProperty(), myPillar2.getLayoutX() + myPillar2.getWidth() - 25)));
        }
        else{
            heroMarching = new Timeline(new KeyFrame(Duration.seconds(2), new KeyValue(myStickHero.layoutXProperty(),myStickHero.getLayoutX()+myStick.getHeight() + 40)));
        }

        heroMarching.setOnFinished(event1 -> {
            cherry.setOpacity(0);
            myScore.setText(Integer.toString(aliveCnt));
            if (isAlive) {
                myStick.setOpacity(0);
                System.out.println("Pillar height:" + myPillar2.getLayoutY() + myPillar2.getY());
                rotate.setAngle(0);
                if (isCherry){
                    cherryCount++;
                    myCherryCnt.setText(Integer.toString(cherryCount));
                }
                isCherry=false;
                pullBack();
            }
            else{
                Media sound = new Media(getClass().getResource("GameOverMusic.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(1); // Loop indefinitely
        MediaView mediaView=new MediaView();
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
                Timeline heroFalling = new Timeline(new KeyFrame(Duration.millis(500),new KeyValue(myStickHero.layoutYProperty(),myStickHero.getLayoutY()+300)));
                heroFalling.play();
                Timeline stickFalling = new Timeline(new KeyFrame(Duration.millis(10),f->{
                    stickDown(rotate);
                }));
                stickFalling.setOnFinished(e -> {
                    try {
                        writeCherryCnt(cherryCount);
                        writeScore(Integer.parseInt(myScore.getText()));
                        switchToGameOver(e);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                stickFalling.setCycleCount(90);
                stickFalling.play();
            }
        });
          heroMarching.play();
    }

    public void pullBack() {


        Rectangle newPillar1 = createPillar();

        myPillar1.setHeight(myPillar2.getHeight());
        myPillar1.setWidth(myPillar2.getWidth());
        myPillar1.setLayoutX(myPillar2.getLayoutX());
        myPillar1.setLayoutY(myPillar2.getLayoutY());

        myPillar2.setHeight(newPillar1.getHeight());
        myPillar2.setWidth(newPillar1.getWidth());
        myPillar2.setLayoutX(newPillar1.getLayoutX());
        myPillar2.setLayoutY(newPillar1.getLayoutY());

        // Create a Timeline for each node
        System.out.println("Pillar height at pullback start:"+myPillar2.getLayoutY()+ myPillar2.getY());
        System.out.println("Y coordinate of stick at start of pullback: "+myStick.getLayoutY());
        double distance = myPillar2.getLayoutX()-myPillar1.getLayoutX();
        KeyValue keyValuePillar1 = new KeyValue(myPillar1.layoutXProperty(), myPillar1.getLayoutX() - distance);
        KeyValue keyValuePillar2 = new KeyValue(myPillar2.layoutXProperty(), myPillar2.getLayoutX() - distance);
        KeyValue keyValueStickHero = new KeyValue(myStickHero.layoutXProperty(), myStickHero.getLayoutX() - distance);
        KeyValue keyValueCherry = new KeyValue(cherry.layoutXProperty(),cherry.getLayoutX()-distance);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5),
                keyValuePillar1,
                keyValuePillar2,
                keyValueStickHero,keyValueCherry
        );

        // Create the Timeline
        Timeline timeline = new Timeline(keyFrame);

        timeline.setOnFinished(event -> {
            System.out.println("Now thread has ended!");
            stickLock = true;
            myStick.setLayoutX(myPillar1.getLayoutX()+myPillar1.getWidth());
            System.out.println(myPillar2.getLayoutY()+"  "+myStick.getHeight());
            System.out.println(myPillar2.getLayoutY()-myStick.getHeight());
            myStick.setHeight(0);
            System.out.println("y coordinate of my :"+myStick.getLayoutY());
            myStick.setLayoutY(myPillar1.getLayoutY()-myStick.getHeight()); myStick.setOpacity(1);
            System.out.println("Pullback animation finished!");
//            cherry = new ImageView("D:\\Coding\\Java\\javaPractice\\backgroungImage\\src\\main\\resources\\com\\example\\backgroungimage\\cherry-clipart-animated-6-removebg-preview.png");
//            anchorPane.getChildren().add(cherry);
//            cherry.setFitHeight(20);
//            cherry.setFitWidth(20);
//            double randomcherry = Math.random();
            cherry.setOpacity(1);
            double xMin = myPillar1.getLayoutX()+myPillar1.getWidth() + 5;
            double xMax = myPillar2.getLayoutX() - 5;
            Random random = new Random();
            double bc = random.nextDouble(xMin, xMax + 1);
            cherry.setLayoutX(bc);

            double yMax= myPillar1.getLayoutY()+50;
            double yMin = myPillar1.getLayoutY()-50;
            double bc1 = random.nextDouble(yMin, yMax + 1);
            cherry.setLayoutY(bc1);

        });

        // Play the Timeline
        timeline.play();
    }

    public void createStick() {
//    FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
//    Parent root = loader.load();

        // Create a new rectangle (stick)
        Rectangle stick = new Rectangle(5, 50, Color.BLACK); // Initial dimensions, adjust as needed
        stick.setX(myStickHero.getLayoutX() + 60) ;
        stick.setY(myStickHero.getLayoutY()) ;

        // Add the rectangle to the AnchorPane
        anchorPane.getChildren().add(stick);
    }
    private void rotateStick() {
        double pivotX = myStick.getX();
        double pivotY = myStick.getY() + myStick.getHeight();

        rotate = new Rotate(0, pivotX, pivotY);
        myStick.getTransforms().add(rotate);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(10), e -> {
                    update(rotate);
                })
        );
        timeline.setCycleCount(90); // Set the number of cycles for the animation
        timeline.setOnFinished(e1 ->{
            heroMarching();
        });
        timeline.play();
    }

    private void update(Rotate rotate) {
        int angle = (int) rotate.getAngle();
        angle++;
        if (angle<=90){
            rotate.setAngle(angle);
        }
        else{
            System.out.println("Hello sir");
            timeline.stop();
        }
    }
    private void stickDown(Rotate rotate)
    {
        int angle = (int) rotate.getAngle();
        angle ++;
        rotate.setAngle(angle);

    }
    private void antiRotateStick() {
        System.out.println("Hi");
//        double pivotX = myStick.getX() + myStick.getWidth()/2;
        double pivotX = myStick.getLayoutX();
//        double pivotY = myStick.getY() + myStick.getHeight();
        double pivotY = myStick.getLayoutY();
        System.out.println(myStick.getHeight());

        Rotate rotate = new Rotate(0, pivotX, pivotY);
        myStick.getTransforms().add(rotate);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    rotate.setAngle(90);
                }),
                new KeyFrame(Duration.millis(20), e -> {
                    rotate.setAngle(-90);
                })
        );
        timeline.play();
    }

    private void setupKeyHandlers() {
        final double initialHeight = myStick.getHeight();
        final double maxHeight = 400; // Set the maximum height as needed

        Timeline increaseHeightTimeline = new Timeline(
                new KeyFrame(Duration.millis(10), e -> {
                    // Check if the maximum height is reached
                    if (myStick.getHeight() < maxHeight) {
                        // Increase the height
                        double oldY = myStick.getLayoutY();
                        myStick.setHeight(myStick.getHeight() + 1);
                        // Adjust the y coordinate to keep the bottom fixed
                        myStick.setLayoutY(oldY - 1);
                    }
                })
        );

        // Set the cycle count to indefinite to keep increasing the height until the key is released
        increaseHeightTimeline.setCycleCount(Timeline.INDEFINITE);

        anchorPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A) {
                if (stickLock) {
                    spaceBarPressed = true;
                    myStick.setOpacity(1);
                    increaseHeightTimeline.play();
                }
            }
            else if(event.getCode() == KeyCode.UP){

            }
            else if(event.getCode() == KeyCode.DOWN){

            }
        });

        anchorPane.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.A) {
                if (stickLock) {
                    spaceBarPressed = false;

                    // ht increase stopped
                    increaseHeightTimeline.stop();
                    double distanceNear = myPillar2.getLayoutX() - myPillar1.getWidth() - myPillar1.getLayoutX();
                    double distanceFar = myPillar2.getLayoutX() + myPillar2.getWidth() - (myPillar1.getLayoutX() + myPillar1.getWidth());
                    if (myStick.getHeight() >= distanceNear && myStick.getHeight() <= distanceFar) {
                        isAlive = true;
                        aliveCnt++;
//                    myScore.setText(Integer.toString(aliveCnt));
                        System.out.println(aliveCnt);
                    } else {
                        isAlive = false;
                    }

                    double d = (myPillar1.getLayoutX() + myPillar1.getWidth() - myStickHero.getLayoutX() - 6) + myStick.getHeight();
//                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(2), myPillar1);
//                translateTransition1.setByX(-1*d);
//                TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(2), myPillar2);
//
//
//
                    rotateStick();
                    stickLock = false;
                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                System.out.println("Pillar height before:"+myPillar2.getLayoutY()+" "+ myPillar2.getY());

                System.out.println("during"+myStickHero.getLayoutX());
//                myStick.setOpacity(0);

            }
            else if (event.getCode() == KeyCode.UP){
                isSPressed=false;
                flipTransition = new RotateTransition(Duration.seconds(0.1), myStickHero);
                flipTransition.setAxis(Rotate.X_AXIS);
                flipTransition.setByAngle(-180); // Adjust the angle as needed
                heroDrops = new Timeline(new KeyFrame(Duration.millis(100),new KeyValue(myStickHero.layoutYProperty(),myStickHero.getLayoutY()-35)));
                heroDrops.setCycleCount(1);
                heroDrops.play();
                flipTransition.play();

            }
            else if (event.getCode() == KeyCode.DOWN){
                flipTransition = new RotateTransition(Duration.seconds(0.1), myStickHero);
                flipTransition.setAxis(Rotate.X_AXIS);
                flipTransition.setByAngle(180); // Adjust the angle as needed

                heroDrops = new Timeline(new KeyFrame(Duration.millis(100),new KeyValue(myStickHero.layoutYProperty(),myStickHero.getLayoutY()+35)));
                heroDrops.setCycleCount(1);
                heroDrops.play();
                flipTransition.play();
            }
        });

    }

    //    private void increaseHeight(double initialHeight, double maxHeight) {
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.millis(20), e -> {
//                    // Check if the maximum height is reached
//                    if (myStick.getHeight() < maxHeight) {
//                        // Increase the height
//                        myStick.setHeight(myStick.getHeight() + 1);
//                    }
//                })
//        );
//
//        // Set the cycle count to indefinite to keep increasing the height until the key is released
//        timeline.setCycleCount(Timeline.INDEFINITE);
//
//        timeline.play();
//    }
    AnimationTimer cherryEatingTimer = new AnimationTimer(){
        @Override
        public void handle(long l){
            cherryCollision();
        }
    };
    private void cherryCollision(){
        Shape playerShape = new Rectangle(myStickHero.getLayoutX(), myStickHero.getLayoutY(), myStickHero.getBoundsInParent().getWidth(), myStickHero.getBoundsInParent().getHeight());
        Shape cherryShape = new Rectangle(cherry.getLayoutX(),cherry.getLayoutY(),cherry.getBoundsInParent().getWidth(),cherry.getBoundsInParent().getHeight());

        if (playerShape.getBoundsInParent().intersects(cherryShape.getBoundsInParent())){
            isCherry=true;
            cherry.setOpacity(0);
            System.out.println("Cherry count: "+cherryCount);
        }

    }
    AnimationTimer collisionTimer = new AnimationTimer(){
        @Override
        public void handle(long l) {
            handleCollision();
        }
    };
    private void handleCollision() {

        Shape playerShape = new Rectangle(myStickHero.getLayoutX(), myStickHero.getLayoutY(), myStickHero.getBoundsInParent().getWidth(), myStickHero.getBoundsInParent().getHeight());
        Shape pillar2Shape = new Rectangle(myPillar2.getLayoutX(), myPillar2.getLayoutY(), myPillar2.getWidth(), myPillar2.getHeight());

        if (playerShape.getBoundsInParent().intersects(pillar2Shape.getBoundsInParent())) {
            System.out.println("PLAYER COLLIDES WITH PILLAR !!!!");
            if (isAlive) {
                aliveCnt--;
            }
            collisionTimer.stop();
            Timeline heroFalling = new Timeline(new KeyFrame(Duration.millis(500), new KeyValue(myStickHero.layoutYProperty(), myStickHero.getLayoutY() + 300)));
            heroFalling.play();
            Timeline stickFalling = new Timeline(new KeyFrame(Duration.millis(10), f -> stickDown(rotate)));
            stickFalling.setCycleCount(90);
            stickFalling.play();
            stickFalling.setOnFinished(e -> {
                try {
                    switchToGameOver(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

        }
    }

    //    private void increaseHeight(double initialHeight, double maxHeight) {
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.millis(20), e -> {
//                    // Check if the maximum height is reached
//                    if (myStick.getHeight() < maxHeight) {
//                        // Increase the height
//                        myStick.setHeight(myStick.getHeight() + 1);
//                    }
//                })
//        );
//
//        // Set the cycle count to indefinite to keep increasing the height until the key is released
//        timeline.setCycleCount(Timeline.INDEFINITE);
//
//        timeline.play();
//    }
    @FXML
    public void initialize()
    {
        try {
            cherryCount = readCherryCnt();
            aliveCnt=readScore();
            myScore.setText(Integer.toString(readScore()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        myCherryCnt.setText(Integer.toString(cherryCount));

        scene = anchorPane.getScene();
        setupKeyHandlers();
        String musicFile = "BGMUSIC.mp3";
        Media sound = new Media(getClass().getResource(musicFile).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop indefinitely
        MediaView mediaView=new MediaView();
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

        anchorPane.getChildren().add(mediaView);
        collisionTimer.start();
        cherryEatingTimer.start();

    }
    //    public void switchToScene2(ActionEvent e) throws IOException
//    {
//        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
//        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//        scene = new Scene (root);
//        stage.setScene(scene);
////        setupKeyHandlers();
//        stage.show();
//
//    }
    public void switchToScene1(ActionEvent e) throws IOException
    {
        aliveCnt = 0;
        Parent root = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }
//    public void SettingsScene(ActionEvent e) throws IOException
//    {
//        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
//        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//        scene = new Scene (root);
//        stage.setScene(scene);
//        stage.show();
//
//    }
    //    public void extendStick()
//    {
//
//    }
//    @FXML
//    public void initialize() {
//        // Set up key event handlers
//        scene.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.SPACE) {
//                spaceBarPressed = true;
//                extendStick();
//            }
//        });
//
//        scene.setOnKeyReleased(event -> {
//            if (event.getCode() == KeyCode.SPACE) {
//                spaceBarPressed = false;
//                // Optionally, you can perform some action when the space bar is released
//            }
//        });
//    }
    public void createStickFromButton(ActionEvent e) throws IOException {
        createStick();
    }


}