package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.bitbucket.GameofOneTeam.gameofone.Model.ClassicGameModel;

public class Settings extends Scene {
    private final static Font btnStyle = Font.font("Ubuntu Mono",50);
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
    private final static Button exit = new Button();
    static {
        difficultyText.setFont(Font.font("Open Sans",50));
        difficultyText.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2;");
        difficultyEasy.setText("Easy");
        difficultyEasy.setMinSize(100,100);
        difficultyEasy.setFont(btnStyle);
        if(ClassicGameModel.difficulty==0) difficultyEasy.setStyle("-fx-background-color: #2fe29b");
        difficultyEasy.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ClassicGameModel.difficulty=0;
                difficultyEasy.setStyle("-fx-background-color: #2fe29b");
                difficultyHard.setStyle("");
            }
        });
        difficultyHard.setText("Hard");
        difficultyHard.setMinSize(100,100);
        difficultyHard.setFont(btnStyle);
        if(ClassicGameModel.difficulty==1) difficultyHard.setStyle("-fx-background-color: #2fe29b");
        difficultyHard.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ClassicGameModel.difficulty=1;
                difficultyEasy.setStyle("");
                difficultyHard.setStyle("-fx-background-color: #2fe29b");
            }
        });

        difficultyBox.getChildren().addAll(difficultyText,difficultyEasy,difficultyHard);
        difficultyBox.setAlignment(Pos.CENTER);

        cardsText.setFont(Font.font("Open Sans",50));
        cardsText.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2;");
        cardsLess.setText("▼");
        cardsLess.setMinSize(50,50);
        cardsLess.setFont(Font.font("Ubuntu Mono",25));
        cardsLess.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ClassicGameModel.cards--;
                cardsCount.setText(String.valueOf(ClassicGameModel.cards));
                if(ClassicGameModel.cards==1) cardsLess.setDisable(true);
                cardsMore.setDisable(false);
            }
        });

        cardsCount.setText(String.valueOf(ClassicGameModel.cards));
        cardsCount.setFont(Font.font("Open Sans",50));
        cardsCount.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2;");

        cardsMore.setText("▲");
        cardsMore.setMinSize(50,50);
        cardsMore.setFont(Font.font("Ubuntu Mono",25));
        cardsMore.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ClassicGameModel.cards++;
                cardsCount.setText(String.valueOf(ClassicGameModel.cards));
                if(ClassicGameModel.cards==15) cardsMore.setDisable(true);
                cardsLess.setDisable(false);
            }
        });

        cardsBox.getChildren().addAll(cardsText,cardsLess,cardsCount,cardsMore);
        cardsBox.setAlignment(Pos.CENTER);

        exit.setText("Return to Main Menu");
        exit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                View.stage.setScene(View.mainMenu);
            }
        });
        exit.setFont(Font.font("Ubuntu Mono",20));
        exit.setMaxSize(250,30);

        vb.getChildren().addAll(difficultyBox,cardsBox,exit);
        vb.setAlignment(Pos.CENTER);
        root.getChildren().add(vb);
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    Settings(int w, int h) {
        super(root, w, h);
    }
}
