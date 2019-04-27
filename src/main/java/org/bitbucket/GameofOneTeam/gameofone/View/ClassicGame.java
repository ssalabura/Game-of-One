package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.bitbucket.GameofOneTeam.gameofone.Model.Card;
import org.bitbucket.GameofOneTeam.gameofone.Model.ClassicGameModel;

public class ClassicGame extends Scene {
    static ClassicGameModel model = new ClassicGameModel(false);
    private final static StackPane root = new StackPane();
    private final static HBox player_cards = new HBox();
    private final static Button exit = new Button();
    private final static VBox classicGame = new VBox();
    static
    {
        for(Card c : model.getPlayers().get(0).getHand())
        {
            ImageView i = new ImageView(c.getImage());
            player_cards.getChildren().add(i);
        }
        exit.setText("Return to Main Menu");
        exit.setMinSize(100,100);
        exit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                View.stage.setScene(View.mainMenu);
            }
        });
        classicGame.getChildren().addAll(player_cards,exit);
        root.getChildren().add(classicGame);
    }

    ClassicGame(int w, int h) {
        super(root, w, h);
    }
}
