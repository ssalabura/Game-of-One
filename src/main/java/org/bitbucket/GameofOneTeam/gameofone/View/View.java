package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application{
    public static Stage stage;
    public static MainMenu mainMenu;
    public static ClassicGame classicGame;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        mainMenu = new MainMenu(1280,720);
        classicGame = new ClassicGame(1280,720);
        primaryStage.setTitle("Game of One");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
