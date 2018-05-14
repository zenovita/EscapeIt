package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EndGame {
    // Control class of end_game.fxml
    @FXML private Button backButton;
    @FXML private Button playAgain;
    @FXML private Label timeSurvived;
    @FXML private Label point;

    @FXML protected void backButtonListener(ActionEvent event) throws Exception {

        Stage backToMenu = (Stage) backButton.getScene().getWindow();

        Parent mainRoot;


        mainRoot = FXMLLoader.load(getClass().getResource("main_screen.fxml"));

        Scene menuScene = new Scene(mainRoot, 1024, 720);
        backToMenu.setScene(menuScene);
    }

    @FXML protected void playAgainListener(ActionEvent event) throws Exception{
        Stage playStage = (Stage) playAgain.getScene().getWindow();

        GameLoop gameLoopStage = new GameLoop();
        gameLoopStage.start(playStage);
    }

    // These methods returns the time and point to the labels.
    Label getTimeSurvived() {
        return timeSurvived;
    }

    Label getPoint() {
        return point;
    }
}
