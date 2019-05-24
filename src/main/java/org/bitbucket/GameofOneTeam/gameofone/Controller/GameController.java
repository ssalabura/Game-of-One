package org.bitbucket.GameofOneTeam.gameofone.Controller;

import javafx.application.Platform;
import org.bitbucket.GameofOneTeam.gameofone.Model.*;
import org.bitbucket.GameofOneTeam.gameofone.View.ClassicGame;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

public class GameController {
    private final GameModel gameModel;
    private final ClassicGame gameView;
    private CountDownLatch latch;
    public GameController(GameModel gameModel, ClassicGame gameView){
        this.gameModel = gameModel;
        this.gameView = gameView;
    }

    public void startGame() throws InterruptedException {
        while(gameModel.getWinner() == null){
            final Integer currentPlayer = gameModel.getCurrentPlayer();
            Player player = gameModel.getPlayers().get(currentPlayer);

            if(currentPlayer == 0 && player instanceof HumanPlayer && !gameModel.getBlock()){
                Card inputCard = null;
                Integer color = null;

                LinkedList<Card> available = player.getAvailable();
                if(!available.isEmpty()) {

                    long currentTime = System.currentTimeMillis();
                    do {
                        synchronized (Thread.currentThread()){
                                Thread.currentThread().wait();
                        }

                        inputCard = gameView.getCardAfter(currentTime);
                        if(!available.contains(inputCard)) inputCard = null;
                    } while (inputCard == null);
                }

                if(inputCard != null && (inputCard.type == CardType.CHANGE_COLOR || inputCard.type == CardType.PLUS_FOUR )){
                    latch = new CountDownLatch(1);
                    Platform.runLater(new Runnable() {
                        public void run() {
                            gameView.beginUpdate(latch);
                            gameView.updateChooseColor();
                        }
                    });
                    latch.await();

                    color = gameView.getChosenColor();
                }

                gameModel.playNextTurn(inputCard,color);

                if(inputCard == null){
                    while (gameView.getHandSize(currentPlayer) < gameModel.getPlayers().get(0).getHand().size()){
                        latch = new CountDownLatch(1);
                        Platform.runLater(new Runnable() {
                            public void run() {
                                gameView.beginUpdate(latch);
                                gameView.addCard(currentPlayer ,gameModel.getPlayers().get(0).getHand().get(gameView.getHandSize(currentPlayer)));
                                gameView.endUpdate();
                            }
                        });
                        latch.await();
                        sleep(600);
                    }

                    Platform.runLater(new Runnable() {
                        public void run() {
                            gameView.trackUpdate();
                        }
                    });
                }

                else {
                    latch = new CountDownLatch(1);
                    Platform.runLater(new Runnable() {
                        public void run() {
                            gameView.beginUpdate(latch);
                            gameView.playCard(0,gameModel.getPlayers().get(0).getCardInd());
                        }
                    });
                    latch.await();
                    sleep(600);

                    Platform.runLater(new Runnable() {
                        public void run() {
                            gameView.trackUpdate();
                        }
                    });
                }
                sleep(200);
            }
            else if(currentPlayer == 0){
                gameModel.playNextTurn(null,null);
                sleep(750);
                Platform.runLater(new Runnable() {
                    public void run() {
                        gameView.trackUpdate();
                    }
                });
                sleep(200);
            }
            else {
                gameModel.playNextTurn(null,null);
                sleep(750);

                if(gameModel.getPlayedCard() != null){

                    latch = new CountDownLatch(1);
                    Platform.runLater(new Runnable() {
                        public void run() {
                            gameView.beginUpdate(latch);
                            gameView.playCard(currentPlayer,gameModel.getPlayers().get(currentPlayer).getCardInd());
                        }
                    });
                    latch.await();
                    sleep(600);

                    Platform.runLater(new Runnable() {
                        public void run() {
                            gameView.trackUpdate();
                        }
                    });

                } else {
                    while (gameView.getHandSize(currentPlayer) < gameModel.getPlayers().get(currentPlayer).getHand().size()){
                        final CountDownLatch latch = new CountDownLatch(1);

                        Platform.runLater(new Runnable() {
                            public void run() {
                                gameView.beginUpdate(latch);
                                gameView.addCard(currentPlayer ,gameModel.getPlayers().get(currentPlayer).getHand().get(gameView.getHandSize(currentPlayer)));
                                gameView.endUpdate();

                            }
                        });
                        latch.await();
                        sleep(600);
                    }

                    Platform.runLater(new Runnable() {
                        public void run() {
                            gameView.trackUpdate();
                        }
                    });
                }
                sleep(200);
            }
        }

        Platform.runLater(new Runnable() {
            public void run() {
                gameView.endGame();
            }
        });
    }
}
