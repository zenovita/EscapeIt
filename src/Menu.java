package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Menu {
    // Control class of main_screen.fxml
    @FXML private Button playGameButton;
    @FXML private Button creditsButton;
    @FXML private Button howToPlayButton;

    @FXML protected void setPlayGameButton() throws Exception{
        Stage gameStage = (Stage) playGameButton.getScene().getWindow();
        GameLoop gameLoopStage = new GameLoop();
        gameLoopStage.start(gameStage);
    }
    @FXML protected void setCreditsButton() throws Exception{
        Stage creditsStage = (Stage) creditsButton.getScene().getWindow();
        Parent creditsRoot = FXMLLoader.load(getClass().getResource("credits.fxml"));

        Scene creditsScene = new Scene(creditsRoot, 1024, 720);
        creditsStage.setScene(creditsScene);
    }

    @FXML protected void setHowToPlayButton() throws Exception{
        Stage howToPlayStage = (Stage) howToPlayButton.getScene().getWindow();
        Parent howToPlayRoot = FXMLLoader.load(getClass().getResource("how_to_play.fxml"));

        Scene howToPlayScene = new Scene(howToPlayRoot, 1024, 720);
        howToPlayStage.setScene(howToPlayScene);
    }
}
