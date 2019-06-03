package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
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
    public static MediaPlayer menuPlayer, gamePlayer;

    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(getClass().getResource("/minecraft/MinecraftFont.ttf").toExternalForm(),10);

        stage = primaryStage;
        mainMenu = new MainMenu(1280,720); mainMenu.load();
        classicGame = new ClassicGame(1280,720);
        victoryScreen = new VictoryScreen(1280,720);
        settings = new Settings();

        Properties p = new Properties();
        File f = new File("save.txt");
        try {
            FileInputStream fis = new FileInputStream(f);
            p.load(fis);
            try { Settings.volume=Integer.valueOf(p.getProperty("volume")); } catch (Exception e) { Settings.volume=100; }
            try { Settings.difficulty=Integer.valueOf(p.getProperty("difficulty")); } catch(Exception e) { Settings.difficulty=0; }
            try { Settings.cards=Integer.valueOf(p.getProperty("cards")); } catch(Exception e) { Settings.cards=7; }
            try { texture_pack=p.getProperty("texture_pack"); } catch(Exception e) { texture_pack="classic"; }
        } catch (Exception e) { }
        settings.load();
        reloadTextures();

        primaryStage.setTitle("Game of One");
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        primaryStage.setScene(mainMenu);
        primaryStage.show();

        menuPlayer = new MediaPlayer(new Media(View.class.getResource("/" + texture_pack + "/menu.wav").toExternalForm()));
        menuPlayer.setVolume((float)Settings.volume/100);
        menuPlayer.play();
        menuPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                menuPlayer.seek(Duration.ZERO);
                menuPlayer.play();
            }
        });
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