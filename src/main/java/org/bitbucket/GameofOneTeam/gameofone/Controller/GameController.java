package org.bitbucket.GameofOneTeam.gameofone.Controller;

import org.bitbucket.GameofOneTeam.gameofone.Model.*;

import java.util.LinkedList;
import java.util.Scanner;

public class GameController {
    private final GameModel gameModel;

    GameController(GameModel gameModel){
        this.gameModel = gameModel;
    }

    void startGame(){
        System.out.println("Start!");

        while(gameModel.getWinner() == null){
            Integer currentPlayer = gameModel.getCurrentPlayer();
            Player player = gameModel.getPlayers().get(currentPlayer);

            if(currentPlayer == 0 && player instanceof HumanPlayer){
                Card inputCard = null;
                Integer color = null;

                System.out.println("Your turn: " + gameModel.getPlayedCard() + " " + player.hand);
                LinkedList<Card> available = player.getAvailable();
                if(!available.isEmpty()) {
                    for (int i = 0; i < available.size(); i++) {
                        System.out.println(i + " " + available.get(i));
                    }

                    System.out.println("Your choice (number) :");
                    Scanner in = new Scanner(System.in);
                    int num = in.nextInt();
                    inputCard = available.remove(num);
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

                gameModel.playNextTurn(inputCard,color);
            }
            else gameModel.playNextTurn(null,null);

            System.out.println(gameModel.getCardNumber());  // Updating number of cards for each player
            System.out.println(": " + currentPlayer + " " + gameModel.getPlayedCard()+ " :"); // Updating card on the top
            if(gameModel.getDirection()) System.out.println("-->"); // Updating direction
            else System.out.println("<--");                        //
        }

        System.out.println("Player " + gameModel.getWinner() + " wins!");
    }
}
