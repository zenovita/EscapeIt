package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;


public class GameLoop extends Application {

    private Parent root;
    private MouseManager mouse;

    private Player player;
    private Point point;

    // Created enemies contained in this arraylist.
    private ArrayList<Enemy> enemies = new ArrayList<>();

    // Holds the point and time for fxml file.
    private static String time;
    private static String pointFX;

    // Holds the time
    private long elapsedTime;
    private long spawnTime;

    private int enemyCounter = 0;
    private int totalPoint = 0;
    private int totalEnemyNumber = 0;

    @FXML private Label pointNum;
    @FXML private Label timer;



    public GameLoop(){}

    // Update method for the game loop. Updates the game in every loop.
    private void update(){
        // Creates enemies.
        initialize();

        // Updated the players move.
        player.move();

        // Updates the enemies' move.
        if(!enemies.isEmpty()) {
            int i = 0;
            while(i < enemyCounter){
                enemies.get(i).move();
                i++;
            }
        }

        // Checks collision if happened.
        checkCollision();
    }

    // This methods creates enemy, sets the speeds and such.
    private void createEnemy(int speed, int frequency) {

        try {
            if(spawnTime%frequency == 0) {
                Enemy enemy1 = new Enemy(
                        new Location(randomWithRange(30, 994), 30),
                        new Size(30),
                        new Movement(1, 0, randomWithRange(speed*2,speed*3)),
                        new Image(new FileInputStream("C:\\Users\\MONSTER\\Desktop\\EscapeIt\\IntellJ\\src\\sample\\character1.png")),
                        root
                );
                enemies.add(enemy1);
                enemyCounter++;
                totalEnemyNumber++;
                enemy1.render();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // This methods creates point object, sets the wanted parameters.
    private void createPoint(){
        try{
            point = new Point(
                    new Location(randomWithRange(20, 1004), randomWithRange(100, 700)),
                    new Size(20),
                    new Movement(0,0,0),
                    new Image(new FileInputStream("C:\\Users\\MONSTER\\Desktop\\EscapeIt\\IntellJ\\src\\sample\\Fire_Emoji_grande.png")),
                    root
            );
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    // This method creates an instance of Player object.
    private void createPlayer(){
        try {
            player = new Player(
                    new Location(512, 500),
                    new Size(20),
                    new Movement(0, 0, 6),
                    new Image(new FileInputStream("C:\\Users\\MONSTER\\Desktop\\EscapeIt\\IntellJ\\src\\sample\\alper.png")),
                    root,
                    mouse);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // This method is like extended Math.random()
    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    // This methods checks the collision between Spawnables.
    private void checkCollision(){

        // Enemies contained in the array list so that this iterator is used.
        final Iterator<Enemy> iterator = enemies.iterator();
        while(iterator.hasNext()){
            final Enemy enemy = iterator.next();
            // If collision happens with the player
            if(enemy != null){
                if(enemy.collision(player.loc, player.size)){

                    enemy.destroy();
                    iterator.remove();

                    time = timer.getText();
                    pointFX = pointNum.getText();
                    player.destroy();
                }
            }
            // Enemy is destroyed when it gets the end of the screen
            if(enemy != null && enemy.getYEnemy() >= 720){
                enemy.destroy();
                iterator.remove();
                enemyCounter--;
            }
        }
        // Point collision
        // Player earns point if collision happens
        if(point != null){
            if(point.collision(player.loc, player.size)) {
                point.destroy();
                totalPoint = totalPoint + 10;
                // Destroyed point created again for the continuity
                createPoint();
                point.render();
                // Player can see the point
                pointNum.setText(String.valueOf(totalPoint));
            }
        }
    }

    private void initialize(){

        // Enemies are created according to this ifs

        if(totalEnemyNumber < 120 || spawnTime < 30000){
            createEnemy(1, 35);
        }
        if(totalEnemyNumber > 120 || spawnTime < 30000){
            createEnemy(2, 30);
        }
        if(totalEnemyNumber > 120 && spawnTime < 60000){
            createEnemy(3, 20);
            createEnemy(3, 25);
        }
        if(totalEnemyNumber > 120 || spawnTime >= 60000){
            createEnemy(3, 20);
            createEnemy(3, 22);
        }
        if(totalPoint < 50){
            createEnemy(1, 35);
        }
        if(totalPoint > 50 && totalPoint < 100){
            createEnemy(3, 20);
            createEnemy(3, 25);
        }
        if(totalPoint > 100 && totalPoint < 150){
            createEnemy(3, 21);
            createEnemy(3, 22);
            createEnemy(3, 27);
        }
        if(totalPoint > 150){
            createEnemy(2, 16);
            createEnemy(3, 24);
            createEnemy(5, 32);
        }
    }

    // Returns the wanted variables.
    public static String getTime(){return time;}

    public static String getPoint(){return pointFX;}

    // Start method for the game.
    // Game starts at here.
    @Override
    public void start(Stage primaryStage) throws Exception{

        // Loads the game screen
        FXMLLoader loader = new FXMLLoader((getClass().getResource("game.fxml")));
        loader.setController(this);

        root = loader.load();
        primaryStage.setScene(new Scene(root, 1024, 720));
        primaryStage.show();

        // Timer
        long startTime = System.currentTimeMillis();
        String format = String.format("%%0%dd", 2);

        // Mouse instance for controls
        mouse = new MouseManager(primaryStage);
        mouse.mouseInputs();

        // Player created
        createPlayer();
        player.render();

        // Point created
        createPoint();
        point.render();

        new AnimationTimer(){
            @Override
            public void handle(long l){
                if(!player.isDestroyed()){
                    update();

                    // This part is counting the time and formatting.
                    long elapsedMillis = System.currentTimeMillis() - startTime;
                    spawnTime = elapsedMillis;
                    //System.out.println(spawnTime);
                    elapsedTime = elapsedMillis / 1000;
                    String seconds = String.format(format, elapsedTime % 60);
                    String minutes = String.format(format, (elapsedTime % 3600) / 60);
                    time = minutes + ":" + seconds;
                    timer.setText(time);

                }
            }
        }.start(); // Recursion for the game.
    }

}
