package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.bitbucket.GameofOneTeam.gameofone.Controller.GameController;
import org.bitbucket.GameofOneTeam.gameofone.Model.ClassicGameModel;

class MainMenu extends Scene {
    private final static StackPane root = new StackPane();
    private final static VBox vb = new VBox(50);
    private static ImageView logo;
    private final static VBox buttons = new VBox(20);
    private final static Button newgameBtn = new Button();
    private final static Button settingsBtn = new Button();
    private final static Button exitgameBtn = new Button();
    static {
        newgameBtn.setText("Classic Game");
        newgameBtn.setMinSize(100,100);
        newgameBtn.setFont(Font.font(View.btnFont,50));
        newgameBtn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                final ClassicGameModel model = new ClassicGameModel(false);

                Thread T =  new Thread(new Task<Integer>() {
                    @Override protected Integer call() throws Exception {
                        new GameController(model, View.classicGame).startGame();
                        return null;
                    }
                });

                View.classicGame.newGame(model,T);
                T.start();
                View.stage.setScene(View.classicGame);
            }
        });

        settingsBtn.setText("Settings");
        settingsBtn.setMinSize(100,100);
        settingsBtn.setFont(Font.font(View.btnFont,50));
        settingsBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                View.stage.setScene(View.settings);
            }
        });

        exitgameBtn.setText("Exit Game");
        exitgameBtn.setMinSize(100,100);
        exitgameBtn.setFont(Font.font(View.btnFont,50));
        exitgameBtn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        logo = new ImageView(View.logo);

        buttons.getChildren().addAll(newgameBtn,settingsBtn,exitgameBtn);
        buttons.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(logo,buttons);
        vb.setAlignment(Pos.CENTER);
        root.getChildren().add(vb);
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
    MainMenu(int w, int h) {
        super(root, w, h);
    }

    static void reloadTextures() {
        logo = new ImageView(View.logo);
        newgameBtn.setFont(Font.font(View.btnFont,50));
        settingsBtn.setFont(Font.font(View.btnFont,50));
        exitgameBtn.setFont(Font.font(View.btnFont,50));
        vb.getChildren().clear();
        vb.getChildren().addAll(logo,buttons);
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
