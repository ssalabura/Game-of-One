package org.bitbucket.GameofOneTeam.gameofone.Controller;

import org.bitbucket.GameofOneTeam.gameofone.Model.Card;
import org.bitbucket.GameofOneTeam.gameofone.Model.GameModel;
import org.bitbucket.GameofOneTeam.gameofone.Model.HumanPlayer;
import org.bitbucket.GameofOneTeam.gameofone.Model.Player;

import java.util.LinkedList;
import java.util.Scanner;

public class GameController {
    private final GameModel gameModel;

    GameController(GameModel gameModel){
        this.gameModel = gameModel;
    }

    void startGame(){
        System.out.println("Start!");   // No working view currently so just writing out

        while(gameModel.getWinner() == null){
            Integer currentPlayer = gameModel.getCurrentPlayer();
            Player player = gameModel.getPlayers().get(currentPlayer);

            if(currentPlayer == 0 && player instanceof HumanPlayer){
                Card inputCard = null;
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
                gameModel.playNextTurn(inputCard);
            }
            else gameModel.playNextTurn(null);

            System.out.println(gameModel.getCardNumber());  // Updating number of cards for each player
            System.out.println(": " + currentPlayer + " " + gameModel.getPlayedCard()+ " :"); // Updating card on the top
            if(gameModel.getDirection()) System.out.println("-->"); // Updating direction
            else System.out.println("<--");                        //
        }

        System.out.println("Player " + gameModel.getWinner() + " wins!");
    }
}
