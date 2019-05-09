package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Settings extends Scene {
    public static int difficulty = 0; //0 - easy, 1 - hard
    public static int cards = 7;

    private final static StackPane root = new StackPane();
    private final static VBox vb = new VBox(50);

    private final static HBox difficultyBox = new HBox(50);
    private final static Text difficultyText = new Text("Bot difficulty:");
    private final static Button difficultyEasy = new Button();
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
    static {
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
                difficulty=0;
                difficultyEasy.setStyle("-fx-background-color: #2fe29b");
                difficultyHard.setStyle("");
            }
        });
        difficultyHard.setText("Hard");
        difficultyHard.setMinSize(100,100);
        difficultyHard.setFont(Font.font(View.btnFont,50));
        if(difficulty==1) difficultyHard.setStyle("-fx-background-color: #2fe29b");
        difficultyHard.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                difficulty=1;
                difficultyEasy.setStyle("");
                difficultyHard.setStyle("-fx-background-color: #2fe29b");
            }
        });

        difficultyBox.getChildren().addAll(difficultyText,difficultyEasy,difficultyHard);
        difficultyBox.setAlignment(Pos.CENTER);

        cardsText.setFont(Font.font(View.btnFont,50));
        cardsText.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2;");
        cardsLess.setText("▼");
        cardsLess.setMinSize(50,50);
        cardsLess.setFont(Font.font(View.btnFont,25));
        cardsLess.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
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
        cardsMore.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
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
        if(View.texture_pack=="classic") texturesClassic.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 10px;");
        else texturesClassic.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 5px;");
        texturesClassic.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                View.texture_pack="classic";
                texturesClassic.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 10px;");
                texturesMinecraft.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 5px;");
                View.reloadTextures();
            }
        });

        texturesMinecraft.setText("Minecraft");
        texturesMinecraft.setFont(Font.font("LM Mono Caps 10",50));
        texturesMinecraft.setBackground(new Background(new BackgroundImage(new Image("/minecraft/mainmenu.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        texturesMinecraft.setMinSize(100,100);
        if(View.texture_pack=="minecraft") texturesMinecraft.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 10px;");
        else texturesMinecraft.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 5px;");
        texturesMinecraft.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                View.texture_pack="minecraft";
                texturesClassic.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 5px;");
                texturesMinecraft.setStyle("-fx-text-fill: white; -fx-border-color: black; -fx-border-width: 10px;");
                View.reloadTextures();
            }
        });

        texturesBox.getChildren().addAll(texturesText,texturesClassic,texturesMinecraft);
        texturesBox.setAlignment(Pos.CENTER);

        exit.setText("Return to Main Menu");
        exit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                View.stage.setScene(View.mainMenu);
            }
        });
        exit.setFont(Font.font(View.btnFont,20));
        exit.setMaxSize(250,30);

        vb.getChildren().addAll(difficultyBox,cardsBox,texturesBox,exit);
        vb.setAlignment(Pos.CENTER);
        root.getChildren().add(vb);
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    Settings(int w, int h) {
        super(root, w, h);
    }

    static void reloadTextures() {
        difficultyText.setFont(Font.font(View.btnFont,50));
        difficultyEasy.setFont(Font.font(View.btnFont,50));
        difficultyHard.setFont(Font.font(View.btnFont,50));
        cardsText.setFont(Font.font(View.btnFont,50));
        cardsCount.setFont(Font.font(View.btnFont,50));
        texturesText.setFont(Font.font(View.btnFont,50));
        exit.setFont(Font.font(View.btnFont,20));
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
