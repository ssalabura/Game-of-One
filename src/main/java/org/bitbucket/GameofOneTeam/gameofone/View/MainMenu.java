package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.bitbucket.GameofOneTeam.gameofone.Controller.GameController;
import org.bitbucket.GameofOneTeam.gameofone.Model.BattleRoyaleModel;
import org.bitbucket.GameofOneTeam.gameofone.Model.ClassicGameModel;

class MainMenu extends Scene {
    private final static StackPane root = new StackPane();
    private final static VBox vb = new VBox(50);
    private static ImageView logo;
    private final static VBox buttons = new VBox(20);
    private final static Button newgameBtn = new Button();
    private final static Button battleRoyaleBtn = new Button();
    private final static Button settingsBtn = new Button();
    private final static Button exitgameBtn = new Button();
    public synchronized void load() {
        newgameBtn.setText("Classic Game");
        newgameBtn.setMinSize(100,100);
        newgameBtn.setFont(Font.font(View.btnFont,50));
        newgameBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                if(View.texture_pack.equals("classic")) {
                    View.menuPlayer.stop();
                    try { Thread.sleep(1000); } catch (InterruptedException e) { }
                    View.gamePlayer = new MediaPlayer(new Media(View.class.getResource("/" + View.texture_pack + "/game.wav").toExternalForm()));
                    View.gamePlayer.setVolume((float)Settings.volume/100);
                    View.gamePlayer.play();
                    View.gamePlayer.setOnEndOfMedia(new Runnable() {
                        public void run() {
                            View.gamePlayer.seek(Duration.ZERO);
                            View.gamePlayer.play();
                        }
                    });
                }
                else try { Thread.sleep(1000); } catch (InterruptedException e) { }

                final ClassicGameModel model = new ClassicGameModel(false);

                Thread T =  new Thread(new Task<Integer>() {
                    @Override protected Integer call() throws Exception {
                        new GameController(model, View.classicGame).startGame();
                        return null;
                    }
                });
                View.classicGame.newGame(model,T);
                T.start();
                double w = View.stage.getWidth(), h = View.stage.getHeight();
                View.stage.setScene(View.classicGame);
                View.stage.setWidth(w);
                View.stage.setHeight(h);
            }
        });

        battleRoyaleBtn.setText("Battle Royale");
        battleRoyaleBtn.setMinSize(100,100);
        battleRoyaleBtn.setFont(Font.font(View.btnFont,50));
        battleRoyaleBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                if(View.texture_pack.equals("classic")) {
                    View.menuPlayer.stop();
                    try { Thread.sleep(1000); } catch (InterruptedException e) { }
                    View.gamePlayer = new MediaPlayer(new Media(View.class.getResource("/" + View.texture_pack + "/game.wav").toExternalForm()));
                    View.gamePlayer.setVolume((float)Settings.volume/100);
                    View.gamePlayer.play();
                    View.gamePlayer.setOnEndOfMedia(new Runnable() {
                        public void run() {
                            View.gamePlayer.seek(Duration.ZERO);
                            View.gamePlayer.play();
                        }
                    });
                }
                else try { Thread.sleep(1000); } catch (InterruptedException e) { }

                final BattleRoyaleModel model = new BattleRoyaleModel(false);

                Thread T =  new Thread(new Task<Integer>() {
                    @Override protected Integer call() throws Exception {
                        new GameController(model, View.classicGame).startGame();
                        return null;
                    }
                });
                View.classicGame.newGame(model,T);
                T.start();
                double w = View.stage.getWidth(), h = View.stage.getHeight();
                View.stage.setScene(View.classicGame);
                View.stage.setWidth(w);
                View.stage.setHeight(h);
            }
        });
        settingsBtn.setText("Settings");
        settingsBtn.setMinSize(100,100);
        settingsBtn.setFont(Font.font(View.btnFont,50));
        settingsBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                double w = View.stage.getWidth(), h = View.stage.getHeight();
                View.stage.setScene(View.settings);
                View.stage.setWidth(w);
                View.stage.setHeight(h);
            }
        });

        exitgameBtn.setText("Exit Game");
        exitgameBtn.setMinSize(100,100);
        exitgameBtn.setFont(Font.font(View.btnFont,50));
        exitgameBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                new AudioClip(getClass().getResource("/" + View.texture_pack + "/click.wav").toExternalForm()).play();
                try { Thread.sleep(100); } catch(Exception e) { }
                System.exit(0);
            }
        });

        logo = new ImageView(View.logo);

        buttons.getChildren().addAll(newgameBtn,battleRoyaleBtn, settingsBtn,exitgameBtn);
        buttons.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(logo,buttons);
        vb.setAlignment(Pos.CENTER);
        vb.scaleXProperty().bind(this.widthProperty().divide(1280));
        vb.scaleYProperty().bind(this.heightProperty().divide(720));
        root.getChildren().add(vb);
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
    MainMenu(int w, int h) {
        super(root, w, h);
    }

    static void reloadTextures() {
        logo = new ImageView(View.logo);
        newgameBtn.setFont(Font.font(View.btnFont,50));
        battleRoyaleBtn.setFont(Font.font(View.btnFont,50));
        settingsBtn.setFont(Font.font(View.btnFont,50));
        exitgameBtn.setFont(Font.font(View.btnFont,50));
        vb.getChildren().clear();
        vb.getChildren().addAll(logo,buttons);
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
