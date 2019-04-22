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

class MainMenu extends Scene {
    private static Font btnStyle = Font.font("Ubuntu Mono",50);
    private final static StackPane root = new StackPane();
    private final static Image background = new Image("https://i.stack.imgur.com/q02th.jpg");
    private final static VBox mainmenu = new VBox(50);
    private final static VBox vb = new VBox(20);
    private final static Button newgame = new Button();
    private final static Button settings = new Button();
    private final static Button exitgame = new Button();
    private final static Text title = new Text("Game of One");
    static {
        // System.out.println(Font.getFamilies()); -- all fonts

        title.setFont(Font.font("Open Sans",150));
        title.setStyle("-fx-fill: white;" +
                       "-fx-stroke: black;" +
                       "-fx-stroke-width: 5;");

        newgame.setText("New Game (WIP)");
        newgame.setMinSize(100,100);
        newgame.setFont(btnStyle);
        newgame.setDisable(true);

        settings.setText("Settings (WIP)");
        settings.setMinSize(100,100);
        settings.setFont(btnStyle);
        settings.setDisable(true);

        exitgame.setText("Exit Game");
        exitgame.setMinSize(100,100);
        exitgame.setFont(btnStyle);
        exitgame.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        vb.getChildren().addAll(newgame,settings,exitgame);
        vb.setAlignment(Pos.CENTER);
        mainmenu.getChildren().addAll(title,vb);
        mainmenu.setAlignment(Pos.CENTER);
        root.getChildren().add(mainmenu);
        root.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
    MainMenu(int w, int h) {
        super(root, w, h);
    }
}
