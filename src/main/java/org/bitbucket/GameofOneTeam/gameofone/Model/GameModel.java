package org.bitbucket.GameofOneTeam.gameofone.Model;


import java.util.LinkedList;

public interface GameModel {

    LinkedList<Player> getPlayers();
    Integer getCurrentPlayer();
    void playNextTurn(Card inputCard, Integer color);
    Integer getWinner();  // null if game has not ended
    boolean getBlock();
    boolean isClockwise();
    Card getPlayedCard();
    Card deckTop();
}
