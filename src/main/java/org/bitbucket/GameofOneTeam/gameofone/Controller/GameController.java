package org.bitbucket.GameofOneTeam.gameofone.Controller;

import javafx.application.Platform;
import org.bitbucket.GameofOneTeam.gameofone.Model.*;
import org.bitbucket.GameofOneTeam.gameofone.View.ClassicGame;

import java.util.LinkedList;

public class GameController {
    private final GameModel gameModel;
    private final ClassicGame gameView;
    public GameController(GameModel gameModel, ClassicGame gameView){
        this.gameModel = gameModel;
        this.gameView = gameView;
    }

    public void startGame() throws InterruptedException {
        while(gameModel.getWinner() == null){
            Integer currentPlayer = gameModel.getCurrentPlayer();
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
                            gameView.chooseColor();
                        }
                    });

                    synchronized (Thread.currentThread()) {
                        Thread.currentThread().wait();
                    }

                    color = gameView.getChosenColor();
                }

                gameModel.playNextTurn(inputCard,color);

                Platform.runLater(new Runnable() {
                    public void run() {
                        gameView.updateHumanMove();
                        gameView.updateDeckTop();
                        gameView.trackUpdate();
                        gameView.endUpdate();
                    }
                });
                beginUpdate();
            }
            else if(currentPlayer == 0){
                gameModel.playNextTurn(null,null);
                Platform.runLater(new Runnable() {
                    public void run() {
                        gameView.updateHumanMove();
                        gameView.updateDeckTop();
                        gameView.trackUpdate();
                        gameView.endUpdate();
                    }
                });
                beginUpdate();
            }
            else {
                final int num = gameModel.getCurrentPlayer();
                gameModel.playNextTurn(null,null);
                Platform.runLater(new Runnable() {
                    public void run() {
                        gameView.updateBotMove(num);
                        gameView.updateDeckTop();
                        gameView.trackUpdate();
                        gameView.endUpdate();
                    }
                });
                beginUpdate();
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
    }
}
