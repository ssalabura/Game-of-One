package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class View extends Application{
    public static Stage stage;
    public static MainMenu mainMenu;
    public static ClassicGame classicGame;
    public static VictoryScreen victoryScreen;
    public static Settings settings;
    public static String texture_pack = "minecraft";
    public final static Image logo = new Image("/" + texture_pack + "/logo.png");
    public final static Image background = new Image("/" + texture_pack + "/mainmenu.png");
    public final static Image game_background = new Image("/" + texture_pack + "/game.png");

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        mainMenu = new MainMenu(1280,720);
        classicGame = new ClassicGame(1280,720);
        victoryScreen = new VictoryScreen(1280,720);
        settings = new Settings(1280,720);
        primaryStage.setTitle("Game of One");
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
