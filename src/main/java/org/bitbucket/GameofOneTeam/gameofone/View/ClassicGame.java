package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.bitbucket.GameofOneTeam.gameofone.Model.*;

import java.awt.*;

import static java.lang.Math.*;


public class ClassicGame extends Scene {
    private static ClassicGameModel model;
    private static Thread controllerThread;
    private static StackPane root = new StackPane();
    private static HBox player_cards;
    private static HBox[] bot_cards = new HBox[3];
    private static HBox centerBox;
    private static VBox centerCenter;
    private static Button exit = new Button();
    private static VBox vb;
    private static ImageView order;
    private static HBox alignTop;
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

    public synchronized void reload_cards() {
        root.getChildren().clear();
        player_cards = new HBox(-130 + min(100,1040/max(1,model.getPlayers().get(0).getHand().size()-1)));
        centerBox = new HBox(100);
        centerCenter = new VBox();
        topBox = new HBox(100);
        vb = new VBox(40);
        order = new ImageView();
        alignTop = new HBox();
        oneButton = new Button();

        player_cards.setMinHeight(182);
        for(final Card c : model.getPlayers().get(0).getHand())
        {
            ImageView i = new ImageView(c.getImage());
            Effect e = new Glow(0.5);
            ((Glow) e).setInput(new DropShadow());
            i.setEffect(e);
            if(!choosingColor) {
                i.setOnMouseClicked(getCardClickEvent(c));
            }
            player_cards.getChildren().add(i);
        }
        for(int i=0;i<3;i++) {
            bot_cards[i] = new HBox(-130 + min(100,260/max(1,model.getPlayers().get(i+1).getCardNumber()-1)));
            bot_cards[i].setMinWidth(390);
            bot_cards[i].setMinHeight(182);
            bot_cards[i].setAlignment(Pos.CENTER);
            for(int j=0;j<model.getPlayers().get(i+1).getCardNumber();j++) {
                ImageView iv = new ImageView(new Image("/card_back.png"));
                iv.setEffect(new DropShadow());
                bot_cards[i].getChildren().add(iv);
            }
        }
        exit.setText("Return to Main Menu");
        exit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                if(View.texture_pack.equals("classic")) {
                    View.gamePlayer.stop();
                    try { Thread.sleep(1000); } catch (InterruptedException e) { }
                    View.menuPlayer.play();
                }
                else try { Thread.sleep(1000); } catch (InterruptedException e) { }
                View.stage.setScene(View.mainMenu);
                controllerThread.stop();
            }
        });
        exit.setFont(Font.font(View.btnFont,20));
        exit.setMaxSize(250,30);
        player_cards.setAlignment(Pos.CENTER);
        if(model.clockwise) order.setImage(new Image("/clockwise.png", 150, 150, false, false));
        else order.setImage(new Image("/counter_clockwise.png", 150, 150, false, false));
        alignTop.setMinWidth(150);

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

        centerCenter.getChildren().add(new ImageView(model.deckTop().getImage()));
        centerBox.getChildren().addAll(bot_cards[0],centerCenter,bot_cards[2]);
        centerBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(order, bot_cards[1], alignTop);
        topBox.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(topBox,centerBox,player_cards,exit);
        vb.setAlignment(Pos.CENTER);
        root.getChildren().add(vb);
        root.setBackground(new Background(new BackgroundImage(View.game_background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        choosingColor = false;
    }

    public Card getCardAfter(long time){
        if(time > lastClickTime) return null;
        return lastClickedCard;
    }

    public Integer getChosenColor(){
        return chosenColor;
    }

    public void updateDeckTop(){
        centerCenter.getChildren().clear();
        centerCenter.getChildren().add(new ImageView(model.deckTop().getImage()));
    }

    public void trackUpdate(){
        if(model.clockwise) order.setImage(new Image("/clockwise.png", 150, 150, false, false));
        else order.setImage(new Image("/counter_clockwise.png", 150, 150, false, false));
        if(model.getCurrentPlayer()==0) {
            for(Node c : player_cards.getChildren()) {
                Effect e = new Glow(0.5);
                ((Glow) e).setInput(new DropShadow());
                c.setEffect(e);
            }
            for(int i=0;i<3;i++) {
                for(Node c : bot_cards[i].getChildren()) c.setEffect(new DropShadow());
            }
        }
        else {
            for(Node c : player_cards.getChildren()) c.setEffect(new DropShadow());
            for(int i=0;i<3;i++) {
                if(model.getCurrentPlayer()==i+1) {
                    for(Node c : bot_cards[i].getChildren()) {
                        Effect e = new Glow(0.5);
                        ((Glow) e).setInput(new DropShadow());
                        c.setEffect(e);
                    }
                }
                else {
                    for(Node c : bot_cards[i].getChildren()) c.setEffect(new DropShadow());
                }
            }
        }
    }

    EventHandler<MouseEvent> getCardClickEvent(final Card c){
        return new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                lastClickTime = System.currentTimeMillis();
                lastClickedCard = c;

                synchronized (controllerThread) {
                    controllerThread.notify();
                }
            }
        };
    }

    public void addCard(int id ,Card card){
        if(id==0) {
            ImageView newCardView = new ImageView(card.getImage());
            newCardView.setOnMouseClicked(getCardClickEvent(card));
            Effect e = new Glow(0.5);
            ((Glow) e).setInput(new DropShadow());
            newCardView.setEffect(e);
            player_cards.getChildren().add(newCardView);
            player_cards.setSpacing(-130 + min(100,1040/max(1,player_cards.getChildren().size()-1)));
        } else {
            ImageView iv = new ImageView("/card_back.png");
            Effect e = new Glow(0.5);
            ((Glow) e).setInput(new DropShadow());
            iv.setEffect(e);
            bot_cards[id-1].getChildren().add(iv);
            bot_cards[id-1].setSpacing(-130 + min(100,260/max(1,bot_cards[id-1].getChildren().size()-1)));
        }
    }

    public void animate(final Node z){
        new AudioClip(getClass().getResource("/" + View.texture_pack + "/card.wav").toExternalForm()).play();
        z.setEffect(new DropShadow());
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(z);
        tt.setDuration(Duration.millis(500));
        tt.setToX(centerCenter.getLayoutX() - root.getWidth()/2 + z.getBoundsInLocal().getWidth()/2 - ((DropShadow)z.getEffect()).getRadius());
        tt.setToY(centerBox.getLayoutY() - root.getHeight()/2 + z.getBoundsInLocal().getHeight()/2 - ((DropShadow)z.getEffect()).getRadius());
        tt.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                updateDeckTop();
                root.getChildren().remove(z);
                endUpdate();
            }
        });
        tt.play();
    }

    public void playCard(int playerId, int ind){
        Node z;
        if(playerId==0) z = player_cards.getChildren().get(ind);
        else z = bot_cards[playerId-1].getChildren().get(ind);
        Node zp = z.getParent();
        double x = z.getLayoutX() + zp.getLayoutX() + zp.getParent().getLayoutX();
        double y = z.getLayoutY() + zp.getLayoutY() + zp.getParent().getLayoutY();

        root.getChildren().add(z);
        z.setTranslateX(x - root.getWidth()/2 + z.getBoundsInLocal().getWidth()/2 - 10); // 10 = shadow radius
        z.setTranslateY(y - root.getHeight()/2 + z.getBoundsInLocal().getHeight()/2 - 10);
        animate(z);

        if(playerId!=0) bot_cards[playerId-1].setSpacing(-130 + min(100,260/max(1,bot_cards[playerId-1].getChildren().size()-1)));
        else player_cards.setSpacing(-130 + min(100,1040/max(1,model.getPlayers().get(0).getHand().size()-1)));
    }

    public void beginUpdate(){
        for(Node z : player_cards.getChildren()) z.setDisable(true);
    }

    public void endUpdate(){
        for(Node z : player_cards.getChildren()) z.setDisable(false);
        synchronized (controllerThread){
            controllerThread.notify();
        }
    }

    public void endGame(){
        View.victoryScreen.show(model.getWinner());
    }
    public int getHandSize(int i) {
        if(i>0) return bot_cards[i-1].getChildren().size();
        return player_cards.getChildren().size();
    }

    public void updateChooseColor(){

        centerCenter.getChildren().clear();
        Button blue = new Button("BLUE");
        blue.setFont(Font.font("Ubuntu Mono",20));
        blue.setStyle("-fx-background-color: #00c3e5");
        blue.setMinSize(130,45);
        blue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                chosenColor = 0;
                choosingColor = false;
                synchronized (controllerThread){
                    controllerThread.notifyAll();
                }
            }
        });
        Button red = new Button("RED");
        red.setFont(Font.font("Ubuntu Mono",20));
        red.setStyle("-fx-background-color: #f56462");
        red.setMinSize(130,45);
        red.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                chosenColor = 2;
                choosingColor = false;
                synchronized (controllerThread){
                    controllerThread.notifyAll();
                }
            }
        });
        Button green = new Button("GREEN");
        green.setFont(Font.font("Ubuntu Mono",20));
        green.setStyle("-fx-background-color: #2fe29b");
        green.setMinSize(130,45);
        green.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                chosenColor = 1;
                choosingColor = false;
                synchronized (controllerThread){
                    controllerThread.notifyAll();
                }
            }
        });
        Button yellow = new Button("YELLOW");
        yellow.setFont(Font.font("Ubuntu Mono",20));
        yellow.setStyle("-fx-background-color: #f7e359");
        yellow.setMinSize(130,45);
        yellow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                chosenColor = 3;
                choosingColor = false;
                synchronized (controllerThread){
                    controllerThread.notifyAll();
                }
            }
        });
        centerCenter.getChildren().addAll(blue,red,green,yellow);
    }

    static void reloadTextures() {
        exit.setFont(Font.font(View.btnFont,20));
        root.setBackground(new Background(new BackgroundImage(View.game_background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
