package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import org.bitbucket.GameofOneTeam.gameofone.Model.Card;
import org.bitbucket.GameofOneTeam.gameofone.Model.ClassicGameModel;
import org.bitbucket.GameofOneTeam.gameofone.Model.GameModel;

import static java.lang.Math.min;


public class ClassicGame extends Scene {
    private static ClassicGameModel model;
    private static Thread controllerThread;
    private static StackPane root = new StackPane();
    private static HBox player_cards;
    private static HBox[] bot_cards = new HBox[3];
    private static HBox centerBox;
    private static Button exit;
    private static VBox vb;
    private static ImageView order;
    private static HBox topBox;
    private static Button oneButton;
    private static Card lastClickedCard;
    private static long lastClickTime;

    ClassicGame(int w, int h) {
        super(root, w, h);
    }

    public void newGame(ClassicGameModel gameModel, Thread cT) {
        model = gameModel;
        controllerThread = cT;
        reload_cards();

        Timeline update = new Timeline(new KeyFrame(Duration.millis(333), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) { reload_cards(); }
        }));
        update.setCycleCount(Timeline.INDEFINITE);
        update.play();
    }

    public void reload_cards() {
        root.getChildren().clear();
        player_cards = new HBox(-40 - min(20,model.getPlayers().get(0).getHand().size() * 2));
        centerBox = new HBox(100);
        topBox = new HBox(100);
        exit = new Button();
        vb = new VBox(40);
        order = new ImageView();
        oneButton = new Button();
        for(final Card c : model.getPlayers().get(0).getHand())
        {
            ImageView i = new ImageView(c.getImage());
            i.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    lastClickTime = System.currentTimeMillis();
                    lastClickedCard = c;
                    synchronized (controllerThread){
                        controllerThread.notify();
                    }
                }
            });
            player_cards.getChildren().add(i);
        }
        for(int i=0;i<3;i++) {
            bot_cards[i] = new HBox(-60 - min(60,model.getPlayers().get(i+1).getCardNumber()*4));
            for(int j=0;j<model.getPlayers().get(i+1).getCardNumber();j++) {
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
        if(model.clockwise) order.setImage(new Image("/counter_clockwise.png", 150, 150, false, false));
        else order.setImage(new Image("/clockwise.png", 150, 150, false, false));
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

    public Card getCardAfter(long time){
        if(time > lastClickTime) return null;
        return lastClickedCard;
    }
}
