package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.bitbucket.GameofOneTeam.gameofone.Model.Card;
import org.bitbucket.GameofOneTeam.gameofone.Model.ClassicGameModel;

public class ClassicGame extends Scene {
    private static ClassicGameModel model = new ClassicGameModel(false);
    private static StackPane root = new StackPane();
    private static HBox player_cards;
    private static HBox[] bot_cards = new HBox[3];
    private static HBox centerBox;
    private static HBox topBox;
    private static Button exit;
    private static VBox vb;
    private static VBox order;
    private static Button oneButton;

    ClassicGame(int w, int h) {
        super(root, w, h);
    }

    public void newGame() {
        root.getChildren().clear();
        player_cards = new HBox(-40);
        centerBox = new HBox(100);
        topBox = new HBox(100);
        exit = new Button();
        vb = new VBox(40);
        model = new ClassicGameModel(false);
        order = new VBox(182);
        oneButton = new Button();
        for(final Card c : model.getPlayers().get(0).getHand())
        {
            ImageView i = new ImageView(c.getImage());
            i.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    reload_cards();
                    //coś
                }
            });
            player_cards.getChildren().add(i);
        }
        for(int i=0;i<3;i++) {
            bot_cards[i] = new HBox(-80);
            for(int j=0;j<model.getPlayers().get(i).getCardNumber();j++) {
                bot_cards[i].getChildren().add(new ImageView(new Image("/card_back.png")));
            }
        }
        exit.setText("Return to Main Menu");
        exit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                View.stage.setScene(View.mainMenu);
            }
        });
        exit.setFont(Font.font("Ubuntu Mono",20));
        exit.setMaxSize(250,30);
        player_cards.setAlignment(Pos.CENTER);
        order.getChildren().add(new ImageView((new Image("/counter_clockwise.png", 150, 150, false, false))));
        oneButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
        oneButton.setGraphic(new ImageView(new Image("/one_button.png", 150, 150, false, false)));
        oneButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                /* IF NOT YOUR TURN
                        Check players with 1 card and without "One" flag and give them 2 cards
                   IF YOUR TURN
                        Check "One" flag to true
                 */
            }
        });
        centerBox.getChildren().addAll(bot_cards[0],new ImageView(model.deckTop().getImage()),bot_cards[2]);
        centerBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(order, bot_cards[1], oneButton);
        topBox.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(topBox,centerBox,player_cards,exit);
        vb.setAlignment(Pos.CENTER);
        root.getChildren().add(vb);
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
    void reload_cards() {
        player_cards.getChildren().clear();
        for(final Card c : model.getPlayers().get(0).getHand())
        {
            ImageView i = new ImageView(c.getImage());
            i.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    reload_cards();
                    //coś
                }
            });
            player_cards.getChildren().add(i);
        }
    }
}
