package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application{

    @Override
    public void start(Stage primaryStage) {
        Scene MainMenu = new MainMenu(1280,720);
        primaryStage.setTitle("Game of One");
        primaryStage.setScene(MainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
