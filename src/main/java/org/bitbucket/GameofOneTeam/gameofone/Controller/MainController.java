package org.bitbucket.GameofOneTeam.gameofone.Controller;

import javafx.application.Application;
import javafx.stage.Stage;
import org.bitbucket.GameofOneTeam.gameofone.Model.ClassicGameModel;

public class MainController extends Application {

    public static void main(String... args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        new GameController(new ClassicGameModel(false)).startGame();
    }
}
