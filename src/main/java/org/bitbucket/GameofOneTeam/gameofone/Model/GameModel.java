package org.bitbucket.GameofOneTeam.gameofone.Model;


import java.util.LinkedList;

public interface GameModel {

    Integer getCurrentPlayer();
    void playNextTurn();
    Integer getWinner();  // null if game has not ended
    LinkedList<Integer> getCardNumber();
    boolean getDirection(); // true if clockwise
    LinkedList<Card> getHand();  // not implemented properly
    Card getPlayedCard();
}
