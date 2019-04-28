package org.bitbucket.GameofOneTeam.gameofone.Controller;

import org.bitbucket.GameofOneTeam.gameofone.Model.*;
import org.bitbucket.GameofOneTeam.gameofone.View.ClassicGame;
import org.bitbucket.GameofOneTeam.gameofone.View.View;

import java.util.LinkedList;
import java.util.Scanner;

public class GameController {
    private final GameModel gameModel;
    private final ClassicGame gameView;

    public GameController(GameModel gameModel, ClassicGame gameView){
        this.gameModel = gameModel;
        this.gameView = gameView;
    }

    public void startGame() throws InterruptedException {
        System.out.println("Start!");

        while(gameModel.getWinner() == null){
            Integer currentPlayer = gameModel.getCurrentPlayer();
            Player player = gameModel.getPlayers().get(currentPlayer);

            if(currentPlayer == 0 && player instanceof HumanPlayer){
                Card inputCard = null;
                Integer color = null;

                LinkedList<Card> available = player.getAvailable();
                if(!available.isEmpty()) {

                    long currentTime = System.currentTimeMillis();
                    do {

                        synchronized (Thread.currentThread()){
                                Thread.currentThread().wait(100);
                        }

                        inputCard = gameView.getCardAfter(currentTime);
                        boolean cont = false;
                        for(Card z : available) cont = cont || z.equals(inputCard);
                        if(!cont) inputCard = null;
                    } while (inputCard == null);
                }

                if(inputCard != null && inputCard.type == CardType.CHANGE_COLOR){
                    System.out.println("Choose color:");
                    for(int i=0;i<4;i++) {
                        System.out.println(i + " " + CardColor.values()[i]);
                    }
                    System.out.println("Your choice:");
                    Scanner in = new Scanner(System.in);
                    color = in.nextInt();
                }

                System.out.println("turn");
                gameModel.playNextTurn(inputCard,color);
            }
            else gameModel.playNextTurn(null,null);

            System.out.println(": " + gameModel.getPlayedCard() + " :");

            if(gameModel.getDirection()) System.out.println("-->"); // Updating direction
            else System.out.println("<--");                        //

            System.out.println(gameModel.getPlayers().get(0).getHand());
        }

        System.out.println("Player " + gameModel.getWinner() + " wins!");
    }
}
