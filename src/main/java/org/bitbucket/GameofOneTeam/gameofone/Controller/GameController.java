package org.bitbucket.GameofOneTeam.gameofone.Controller;

import org.bitbucket.GameofOneTeam.gameofone.Model.GameModel;

public class GameController {
    private final GameModel gameModel;

    GameController(GameModel gameModel){
        this.gameModel = gameModel;
    }

    void startGame(){
        System.out.println("Start!");   // No working view currently so just writing out

        while(gameModel.getWinner() == null){
            gameModel.playNextTurn();

            System.out.println(gameModel.getCardNumber());  // Updating number of cards for each player
            System.out.println(": " + gameModel.getCurrentPlayer() + " " + gameModel.getPlayedCard()+ " :"); // Updating card on the top
            if(gameModel.getDirection()) System.out.println("-->"); // Updating direction
            else System.out.println("<--");                        //
        }

        System.out.println("Player " + gameModel.getWinner() + " wins!");
    }
}
