package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;


public class Player extends Spawnable {
    // This is the Player class.
    // This class will create player so that user can play.

    private Parent root;
    private MouseManager mouse;
    private Circle player;
    private boolean destroyed = false;

    public Player(Location location, Size size, Movement movement, Image img, Parent root, MouseManager mouse){
        super(location, movement, img, size);
        this.mouse = mouse;
        this.root = root;
    }
    public Player(){}

    // This method creates a new object with given variables.
    @Override
    public void render(){
        player = new Circle();
        player.setFill(new ImagePattern(this.img));

        player.setCenterX(loc.getX());
        player.setCenterY(loc.getY());
        player.setRadius(size.getRadius());

        ((Pane) root).getChildren().add(player);
    }

    // This method sets the coordinates according to mouse.
    @Override
    public void move(){
        loc.setX(mouse.getxCoor());
        loc.setY(mouse.getyCoor());
        player.setCenterX(loc.getX());
        player.setCenterY(loc.getY());

    }

    // After player dies this method loads the end game screen so that user can see the points and time.
    @Override
    public void destroy(){
        destroyed = true;
        Stage gameStage = (Stage) player.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("end_game.fxml"));
        Parent gameRoot = null;
        try {
            gameRoot = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EndGame fxmlDocumentController = fxmlLoader.getController();

        // loading stats of the game
        fxmlDocumentController.getTimeSurvived().setText(GameLoop.getTime());
        fxmlDocumentController.getPoint().setText(GameLoop.getPoint());

        Scene gameScene = new Scene(gameRoot, 1024, 720);
        gameStage.setScene(gameScene);
    }

    public boolean isDestroyed() { return destroyed; }
}

