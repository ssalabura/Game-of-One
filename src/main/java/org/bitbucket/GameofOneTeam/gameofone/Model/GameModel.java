package org.bitbucket.GameofOneTeam.gameofone.Model;


import java.util.LinkedList;

public interface GameModel {

    LinkedList<Player> getPlayers();
    Integer getCurrentPlayer();
    void playNextTurn(Card inputCard);
    Integer getWinner();  // null if game has not ended
    LinkedList<Integer> getCardNumber();
    boolean getDirection(); // true if clockwise
    Card getPlayedCard();
}
