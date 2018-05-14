package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HowToPlay {

    // Controller class for how_to_play.fxml
    @FXML private Button backButton;
    @FXML protected void setBackButton(ActionEvent event) throws Exception{
        Stage menuStage = (Stage) backButton.getScene().getWindow();
        Parent menuRoot;
        menuRoot = FXMLLoader.load(getClass().getResource("main_screen.fxml"));
        Scene menuScene = new Scene(menuRoot, 1024,720);
        menuStage.setScene(menuScene);
    }
}
