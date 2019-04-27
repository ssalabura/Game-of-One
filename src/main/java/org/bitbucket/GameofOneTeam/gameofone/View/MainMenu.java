package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class MainMenu extends Scene {
    private final static Font btnStyle = Font.font("Ubuntu Mono",50);
    private final static StackPane root = new StackPane();
    private final static VBox vb = new VBox(50);
    private final static VBox buttons = new VBox(20);
    private final static Button newgameBtn = new Button();
    private final static Button settingsBtn = new Button();
    private final static Button exitgameBtn = new Button();
    private final static Text title = new Text("Game of One");
    static {
        // System.out.println(Font.getFamilies()); -- all fonts

        title.setFont(Font.font("Open Sans",150));
        title.setStyle("-fx-fill: white;" +
                       "-fx-stroke: black;" +
                       "-fx-stroke-width: 5;");

        newgameBtn.setText("Classic Game");
        newgameBtn.setMinSize(100,100);
        newgameBtn.setFont(btnStyle);
        newgameBtn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                View.classicGame.newGame();
                View.stage.setScene(View.classicGame);
            }
        });

        settingsBtn.setText("Settings (WIP)");
        settingsBtn.setMinSize(100,100);
        settingsBtn.setFont(btnStyle);
        settingsBtn.setDisable(true);

        exitgameBtn.setText("Exit Game");
        exitgameBtn.setMinSize(100,100);
        exitgameBtn.setFont(btnStyle);
        exitgameBtn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        buttons.getChildren().addAll(newgameBtn,settingsBtn,exitgameBtn);
        buttons.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(title,buttons);
        vb.setAlignment(Pos.CENTER);
        root.getChildren().add(vb);
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
    MainMenu(int w, int h) {
        super(root, w, h);
    }
}
