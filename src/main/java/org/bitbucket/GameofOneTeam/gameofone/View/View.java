package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

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

        Properties p = new Properties();
        File f = new File("save.txt");
        try {
            FileInputStream fis = new FileInputStream(f);
            p.load(fis);
            try { Settings.difficulty=Integer.valueOf(p.getProperty("difficulty")); } catch(Exception e) { Settings.difficulty=0; }
            try { Settings.cards=Integer.valueOf(p.getProperty("cards")); } catch(Exception e) { Settings.cards=7; }
            try { texture_pack=p.getProperty("texture_pack"); } catch(Exception e) { texture_pack="classic"; }
        } catch (Exception e) { }
        Settings.load();
        reloadTextures();

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
        if(texture_pack.equals("classic")) btnFont = "Ubuntu Mono";
        else if(texture_pack.equals("minecraft")) btnFont = "Minecraft";
        MainMenu.reloadTextures();
        ClassicGame.reloadTextures();
        Settings.reloadTextures();
        VictoryScreen.reloadTextures();
    }
}
