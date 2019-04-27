package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.bitbucket.GameofOneTeam.gameofone.Model.Card;
import org.bitbucket.GameofOneTeam.gameofone.Model.ClassicGameModel;

public class ClassicGame extends Scene {
    private static ClassicGameModel model = new ClassicGameModel(false);
    private static StackPane root = new StackPane();
    private static HBox player_cards;
    private static HBox[] bot_cards = new HBox[3];
    private static HBox centerBox;
    private static Button exit;
    private static VBox vb;

    ClassicGame(int w, int h) {
        super(root, w, h);
    }

    public void newGame() {
        root.getChildren().clear();
        player_cards = new HBox(-40);
        centerBox = new HBox(150);
        exit = new Button();
        vb = new VBox(40);
        model = new ClassicGameModel(false);
        for(Card c : model.getPlayers().get(0).getHand())
        {
            ImageView i = new ImageView(c.getImage());
            player_cards.getChildren().add(i);
        }
        for(int i=0;i<3;i++) {
            bot_cards[i] = new HBox(-80);
            for(int j=0;j<model.getPlayers().get(i).getCardNumber();j++) {
                bot_cards[i].getChildren().add(new ImageView(new Image("/card_back.png")));
            }
        }
        exit.setText("Return to Main Menu");
        exit.setMinSize(100,100);
        exit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                View.stage.setScene(View.mainMenu);
            }
        });
        player_cards.setAlignment(Pos.CENTER);
        bot_cards[1].setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(bot_cards[0],bot_cards[2]);
        centerBox.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(bot_cards[1],centerBox,player_cards,exit);
        vb.setAlignment(Pos.CENTER);
        root.getChildren().add(vb);
    }
}
