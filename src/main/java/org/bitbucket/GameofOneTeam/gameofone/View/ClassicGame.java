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
import org.bitbucket.GameofOneTeam.gameofone.Model.*;

import static java.lang.Math.*;


public class ClassicGame extends Scene {
    private static ClassicGameModel model;
    private static Thread controllerThread;
    private static StackPane root = new StackPane();
    private static HBox player_cards;
    private static HBox[] bot_cards = new HBox[3];
    private static HBox centerBox;
    private static VBox centerCenter;
    private static Button exit;
    private static VBox vb;
    private static ImageView order;
    private static ImageView turnIndicator;
    private static HBox topBox;
    private static Button oneButton;
    private static Card lastClickedCard;
    private static long lastClickTime;
    private static boolean choosingColor;
    private static Integer chosenColor;
    ClassicGame(int w, int h) {
        super(root, w, h);
    }

    public void newGame(ClassicGameModel gameModel, Thread cT) {
        model = gameModel;
        controllerThread = cT;
        reload_cards();
    }

    public void reload_cards() {
        root.getChildren().clear();
        if(model.getWinner()!=null)
        {
            controllerThread.stop();
            View.victoryScreen.show(model.getWinner());
        }
        player_cards = new HBox(-30 - model.getPlayers().get(0).getHand().size() * 2);
        centerBox = new HBox(100);
        centerCenter = new VBox();
        topBox = new HBox(100);
        exit = new Button();
        vb = new VBox(40);
        order = new ImageView();
        turnIndicator = new ImageView();
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
            bot_cards[i] = new HBox(-130 + min(100,260/max(1,model.getPlayers().get(i+1).getCardNumber()-1)));
            bot_cards[i].setMinWidth(390);
            bot_cards[i].setAlignment(Pos.CENTER);
            for(int j=0;j<model.getPlayers().get(i+1).getCardNumber();j++) {
                bot_cards[i].getChildren().add(new ImageView(new Image("/card_back.png")));
            }
        }
        exit.setText("Return to Main Menu");
        exit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                View.stage.setScene(View.mainMenu);
                controllerThread.stop();
            }
        });
        exit.setFont(Font.font("Ubuntu Mono",20));
        exit.setMaxSize(250,30);
        player_cards.setAlignment(Pos.CENTER);
        if(model.clockwise) order.setImage(new Image("/counter_clockwise.png", 150, 150, false, false));
        else order.setImage(new Image("/clockwise.png", 150, 150, false, false));
        turnIndicator.setImage(new Image("/turn"+model.getCurrentPlayer()+".png", 150, 150, false, false));

        /*
        This functionality is currently not implemented

        oneButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
        oneButton.setGraphic(new ImageView(new Image("/one_button.png", 150, 150, false, false)));
        oneButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                 IF NOT YOUR TURN
                        Check players with 1 card and without "One" flag and give them 2 cards
                 IF YOUR TURN
                        Check "One" flag to true
            }
        }
        */

        if(choosingColor) {
            Button blue = new Button("BLUE");
            blue.setFont(Font.font("Ubuntu Mono",20));
            blue.setStyle("-fx-background-color: #00c3e5");
            blue.setMinSize(150,45);
            blue.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    chosenColor = 0;
                    choosingColor = false;
                    synchronized (controllerThread){
                        controllerThread.notifyAll();
                    }
                    reload_cards();
                }
            });
            Button red = new Button("RED");
            red.setFont(Font.font("Ubuntu Mono",20));
            red.setStyle("-fx-background-color: #f56462");
            red.setMinSize(150,45);
            red.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    chosenColor = 2;
                    choosingColor = false;
                    synchronized (controllerThread){
                        controllerThread.notifyAll();
                    }
                    reload_cards();
                }
            });
            Button green = new Button("GREEN");
            green.setFont(Font.font("Ubuntu Mono",20));
            green.setStyle("-fx-background-color: #2fe29b");
            green.setMinSize(150,45);
            green.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    chosenColor = 1;
                    choosingColor = false;
                    synchronized (controllerThread){
                        controllerThread.notifyAll();
                    }
                    reload_cards();
                }
            });
            Button yellow = new Button("YELLOW");
            yellow.setFont(Font.font("Ubuntu Mono",20));
            yellow.setStyle("-fx-background-color: #f7e359");
            yellow.setMinSize(150,45);
            yellow.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    chosenColor = 3;
                    choosingColor = false;
                    synchronized (controllerThread){
                        controllerThread.notifyAll();
                    }
                    reload_cards();
                }
            });
            centerCenter.getChildren().addAll(blue,red,green,yellow);
        }
        else {
            centerCenter.getChildren().add(new ImageView(model.deckTop().getImage()));
        }
        centerBox.getChildren().addAll(bot_cards[0],centerCenter,bot_cards[2]);
        centerBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(order, bot_cards[1], turnIndicator);
        topBox.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(topBox,centerBox,player_cards,exit);
        vb.setAlignment(Pos.CENTER);
        root.getChildren().add(vb);
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        choosingColor = false;
    }

    public Card getCardAfter(long time){
        if(time > lastClickTime) return null;
        return lastClickedCard;
    }

    public Integer getChosenColor(){
        return chosenColor;
    }

    public void chooseColor() {
        choosingColor = true;
        reload_cards();
    }
}
