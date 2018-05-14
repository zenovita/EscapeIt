package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    // This is the Main class. App starts from here.
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main_screen.fxml"));
        primaryStage.setTitle("Escape It");
        primaryStage.setScene(new Scene(root,1024,720));
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }

}
