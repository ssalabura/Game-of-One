package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class View extends Application{
    public static Stage stage;
    public static MainMenu mainMenu;
    public static ClassicGame classicGame;
    public static VictoryScreen victoryScreen;
    public static Settings settings;
    public static String texture_pack = "classic";
    public static String btnFont = "Ubuntu Mono";
    public static Image logo = new Image("/" + texture_pack + "/logo.png");
    public static Image background = new Image("/" + texture_pack + "/mainmenu.png");
    public static Image game_background = new Image("/" + texture_pack + "/game.png");

    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(getClass().getResource("/minecraft/MinecraftFont.ttf").toExternalForm(),10);

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

    static void reloadTextures() {
        logo = new Image("/" + texture_pack + "/logo.png");
        background = new Image("/" + texture_pack + "/mainmenu.png");
        game_background = new Image("/" + texture_pack + "/game.png");
        if(texture_pack=="classic") btnFont = "Ubuntu Mono";
        else if(texture_pack=="minecraft") btnFont = "Minecraft";
        MainMenu.reloadTextures();
        ClassicGame.reloadTextures();
        Settings.reloadTextures();
        VictoryScreen.reloadTextures();
    }
}
