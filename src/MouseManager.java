package sample;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MouseManager {
    // This class manages the Mouse actions.
    private Stage primaryStage;
    private double xCoor;
    private double yCoor;

    MouseManager(Stage primaryStage){this.primaryStage = primaryStage;}

    // This method creates a new mouse event so that user can play the game with its mouse.
    void mouseInputs(){
        primaryStage.addEventHandler(MouseEvent.MOUSE_MOVED, t -> {
            xCoor = t.getSceneX();
            yCoor = t.getSceneY();
        });

        /*
        EventHandler<MouseEvent> mouseEventEventHandler =
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent h) {
                        player.loc.setX(h.getSceneX());
                        player.loc.setY(h.getSceneY());
                        player.move();
                    }
                };
        primaryStage.addEventHandler(MouseEvent.MOUSE_MOVED, mouseEventEventHandler);
        */
    }

    // Returns the coordinates.
    double getxCoor(){return xCoor;}
    double getyCoor(){return yCoor;}
}
