package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

public class Settings extends Scene {
    public static int difficulty = 0; //0 - easy, 1 - hard
    public static int cards = 7;
    public static int volume = 100;

    private final static StackPane root = new StackPane();
    private final static VBox vb = new VBox(50);

    private final static HBox volumeBox = new HBox(50);
    private final static Text volumeText = new Text("Music volume:");
    private final static Button volumeLower = new Button();
    private final static Text volumeCurrent = new Text();
    private final static Button volumeHigher = new Button();

    private final static HBox difficultyBox = new HBox(50);
    private final static Text difficultyText = new Text("Bot difficulty:");
    private final static Button difficultyEasy = new Button();
    private final static Button difficultyMedium = new Button();
    private final static Button difficultyHard = new Button();

    private final static HBox cardsBox = new HBox(50);
    private final static Text cardsText = new Text("Cards at start:");
    private final static Button cardsLess = new Button();
    private final static Text cardsCount = new Text();
    private final static Button cardsMore = new Button();

    private final static HBox texturesBox = new HBox(50);
    private final static Text texturesText = new Text("Texture pack:");
    private final static Button texturesClassic = new Button();
    private final static Button texturesMinecraft = new Button();

    private final static Button exit = new Button();
    void load(){
        volumeText.setFont(Font.font(View.btnFont,50));
        volumeText.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2;");

        volumeLower.setText("▼");
        volumeLower.setMinSize(50,50);
        volumeLower.setFont(Font.font(View.btnFont,25));
        if(volume==0) volumeLower.setDisable(true);
        volumeLower.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                volume-=10;
                View.menuPlayer.setVolume((float)volume/100);
                volumeCurrent.setText(volume+"%");
                if(volume==0) volumeLower.setDisable(true);
                volumeHigher.setDisable(false);
            }
        });

        volumeCurrent.setText(volume+"%");
        volumeCurrent.setFont(Font.font(View.btnFont,50));
        volumeCurrent.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2;");

        volumeHigher.setText("▲");
        volumeHigher.setMinSize(50,50);
        volumeHigher.setFont(Font.font(View.btnFont,25));
        if(volume==100) volumeHigher.setDisable(true);
        volumeHigher.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                volume+=10;
                View.menuPlayer.setVolume((float)volume/100);
                volumeCurrent.setText(volume+"%");
                if(volume==100) volumeHigher.setDisable(true);
                volumeLower.setDisable(false);
            }
        });

        volumeBox.getChildren().addAll(volumeText,volumeLower,volumeCurrent,volumeHigher);
        volumeBox.setAlignment(Pos.CENTER);

        difficultyText.setFont(Font.font(View.btnFont,50));
        difficultyText.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2;");
        difficultyEasy.setText("Easy");
        difficultyEasy.setMinSize(100,100);
        difficultyEasy.setFont(Font.font(View.btnFont,50));
        if(difficulty==0) difficultyEasy.setStyle("-fx-background-color: #2fe29b");
        difficultyEasy.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                difficulty=0;
                difficultyEasy.setStyle("-fx-background-color: #2fe29b");
                difficultyMedium.setStyle("");
                difficultyHard.setStyle("");
            }
        });

        difficultyMedium.setText("Medium");
        difficultyMedium.setMinSize(100,100);
        difficultyMedium.setFont(Font.font(View.btnFont,50));
        if(difficulty==1) difficultyMedium.setStyle("-fx-background-color: #2fe29b");
        difficultyMedium.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                difficulty=1;
                difficultyEasy.setStyle("");
                difficultyMedium.setStyle("-fx-background-color: #2fe29b");
                difficultyHard.setStyle("");
            }
        });

        difficultyHard.setText("Hard");
        difficultyHard.setMinSize(100,100);
        difficultyHard.setFont(Font.font(View.btnFont,50));
        if(difficulty==2) difficultyHard.setStyle("-fx-background-color: #2fe29b");
        difficultyHard.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                difficulty=2;
                difficultyEasy.setStyle("");
                difficultyMedium.setStyle("");
                difficultyHard.setStyle("-fx-background-color: #2fe29b");
            }
        });

        difficultyBox.getChildren().addAll(difficultyText,difficultyEasy,difficultyMedium,difficultyHard);
        difficultyBox.setAlignment(Pos.CENTER);

        cardsText.setFont(Font.font(View.btnFont,50));
        cardsText.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2;");
        cardsLess.setText("▼");
        cardsLess.setMinSize(50,50);
        cardsLess.setFont(Font.font(View.btnFont,25));
        if(cards==1) cardsLess.setDisable(true);
        cardsLess.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                cards--;
                cardsCount.setText(String.valueOf(cards));
                if(cards==1) cardsLess.setDisable(true);
                cardsMore.setDisable(false);
            }
        });

        cardsCount.setText(String.valueOf(cards));
        cardsCount.setFont(Font.font(View.btnFont,50));
        cardsCount.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2;");

        cardsMore.setText("▲");
        cardsMore.setMinSize(50,50);
        cardsMore.setFont(Font.font(View.btnFont,25));
        if(cards==15) cardsMore.setDisable(true);
        cardsMore.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                cards++;
                cardsCount.setText(String.valueOf(cards));
                if(cards==15) cardsMore.setDisable(true);
                cardsLess.setDisable(false);
            }
        });

        cardsBox.getChildren().addAll(cardsText,cardsLess,cardsCount,cardsMore);
        cardsBox.setAlignment(Pos.CENTER);

        texturesText.setFont(Font.font(View.btnFont,50));
        texturesText.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2;");

        texturesClassic.setText("Classic");
        texturesClassic.setFont(Font.font("Ubuntu Mono",50));
        texturesClassic.setBackground(new Background(new BackgroundImage(new Image("/classic/mainmenu.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        texturesClassic.setMinSize(100,100);
        if(View.texture_pack.equals("classic")) texturesClassic.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 10px;");
        else texturesClassic.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 5px;");
        texturesClassic.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                View.texture_pack="classic";
                texturesClassic.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 10px;");
                texturesMinecraft.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 5px;");
                View.menuPlayer.stop();
                View.menuPlayer = new MediaPlayer(new Media(View.class.getResource("/" + View.texture_pack + "/menu.wav").toExternalForm()));
                View.menuPlayer.setVolume((float)volume/100);
                View.reloadTextures();
                View.menuPlayer.play();
            }
        });

        texturesMinecraft.setText("Minecraft");
        texturesMinecraft.setFont(Font.font("Minecraft",50));
        texturesMinecraft.setBackground(new Background(new BackgroundImage(new Image("/minecraft/mainmenu.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        texturesMinecraft.setMinSize(100,100);
        if(View.texture_pack.equals("minecraft")) texturesMinecraft.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 10px;");
        else texturesMinecraft.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 5px;");
        texturesMinecraft.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                View.texture_pack="minecraft";
                texturesClassic.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 5px;");
                texturesMinecraft.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 10px;");
                View.menuPlayer.stop();
                View.menuPlayer = new MediaPlayer(new Media(View.class.getResource("/" + View.texture_pack + "/menu.wav").toExternalForm()));
                View.menuPlayer.setVolume((float)volume/100);
                View.reloadTextures();
                View.menuPlayer.play();
            }
        });

        texturesBox.getChildren().addAll(texturesText,texturesClassic,texturesMinecraft);
        texturesBox.setAlignment(Pos.CENTER);
        
        exit.setText("Return to Main Menu");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                Properties p = new Properties();
                p.setProperty("volume",String.valueOf(volume));
                p.setProperty("difficulty",String.valueOf(difficulty));
                p.setProperty("cards",String.valueOf(cards));
                p.setProperty("texture_pack",View.texture_pack);
                File f = new File("save.txt");
                try {
                    f.createNewFile();
                    FileOutputStream fos = new FileOutputStream(f);
                    p.store(fos,"");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                double w = View.stage.getWidth(), h = View.stage.getHeight();
                View.stage.setScene(View.mainMenu);
                View.stage.setWidth(w);
                View.stage.setHeight(h);
            }
        });
        exit.setFont(Font.font(View.btnFont,20));
        exit.setMaxSize(250,30);

        vb.getChildren().addAll(volumeBox,difficultyBox,cardsBox,texturesBox,exit);
        vb.setAlignment(Pos.CENTER);
        vb.scaleXProperty().bind(this.widthProperty().divide(1280));
        vb.scaleYProperty().bind(this.heightProperty().divide(720));
        root.getChildren().add(vb);
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    Settings() {
        super(root);
    }

    static void reloadTextures() {
        volumeText.setFont(Font.font(View.btnFont,50));
        volumeCurrent.setFont(Font.font(View.btnFont,50));
        difficultyText.setFont(Font.font(View.btnFont,50));
        difficultyEasy.setFont(Font.font(View.btnFont,50));
        difficultyMedium.setFont(Font.font(View.btnFont, 50));
        difficultyHard.setFont(Font.font(View.btnFont,50));
        cardsText.setFont(Font.font(View.btnFont,50));
        cardsCount.setFont(Font.font(View.btnFont,50));
        texturesText.setFont(Font.font(View.btnFont,50));
        exit.setFont(Font.font(View.btnFont,20));
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
