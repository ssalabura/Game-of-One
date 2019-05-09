package org.bitbucket.GameofOneTeam.gameofone.Controller;

import javafx.application.Platform;
import org.bitbucket.GameofOneTeam.gameofone.Model.*;
import org.bitbucket.GameofOneTeam.gameofone.View.ClassicGame;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class GameController {
    private final GameModel gameModel;
    private final ClassicGame gameView;
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
                    Platform.runLater(new Runnable() {
                        public void run() {
                            gameView.beginUpdate();
                            gameView.updateChooseColor();
                        }
                    });

                    synchronized (Thread.currentThread()) {
                        Thread.currentThread().wait();
                    }

                    color = gameView.getChosenColor();
                }

                gameModel.playNextTurn(inputCard,color);

                if(inputCard == null){
                    while (gameView.getHandSize(currentPlayer) < gameModel.getPlayers().get(0).getHand().size()){
                        Platform.runLater(new Runnable() {
                            public void run() {
                                gameView.beginUpdate();
                                gameView.addCard(currentPlayer ,gameModel.getPlayers().get(0).getHand().get(gameView.getHandSize(currentPlayer)));
                                gameView.endUpdate();
                            }
                        });
                        beginUpdate();
                    }

                    Platform.runLater(new Runnable() {
                        public void run() {
                            gameView.beginUpdate();
                            gameView.trackUpdate();
                            gameView.endUpdate();
                        }
                    });
                    beginUpdate();
                }

                else {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            gameView.beginUpdate();
                            gameView.playCard(0,gameModel.getPlayers().get(0).getCardInd());
                        }
                    });
                    beginUpdate();

                }
            }
            else if(currentPlayer == 0){
                gameModel.playNextTurn(null,null);
                Platform.runLater(new Runnable() {
                    public void run() {
                        gameView.trackUpdate();
                    }
                });
                sleep(1000);
            }
            else {
                gameModel.playNextTurn(null,null);

                if(gameModel.getPlayedCard() != null){
                    Platform.runLater(new Runnable() {
                        public void run() {
                            gameView.beginUpdate();
                            gameView.playCard(currentPlayer,gameModel.getPlayers().get(currentPlayer).getCardInd());
                        }
                    });
                    beginUpdate();

                } else {
                    while (gameView.getHandSize(currentPlayer) < gameModel.getPlayers().get(currentPlayer).getHand().size()){
                        Platform.runLater(new Runnable() {
                            public void run() {
                                gameView.beginUpdate();
                                gameView.addCard(currentPlayer ,gameModel.getPlayers().get(currentPlayer).getHand().get(gameView.getHandSize(currentPlayer)));
                                gameView.endUpdate();
                            }
                        });
                        beginUpdate();
                    }
                }
            }
        }

        Platform.runLater(new Runnable() {
            public void run() {
                gameView.endGame();
            }
        });
    }

    void beginUpdate(){
        synchronized (Thread.currentThread()) {
            try {
                Thread.currentThread().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
