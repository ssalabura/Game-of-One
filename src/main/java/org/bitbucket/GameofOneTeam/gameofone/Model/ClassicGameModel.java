package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;

import static org.bitbucket.GameofOneTeam.gameofone.Model.CardType.*;

public class ClassicGameModel implements GameModel {
    private LinkedList<Player> players = new LinkedList<Player>();
    private Deck deck = new Deck(1);
    private boolean clockwise = true;
    private int cardsToAdd = 0;
    private boolean block = false;
    private int currentPlayer = 0;
    private int prevPlayer = 0;
    private Integer winner = null;
    private Card playedCard;

     private void notifyPlayers(Card card){
        for (Player z : players) { z.update(card);}
     }

    public ClassicGameModel(boolean demo){

        for(int i=0;i<4;i++){
            if(demo || i!=0) players.addLast(new EasyBot());
            else players.addLast(new HumanPlayer());

            for(int j=1;j<=7;j++){ players.getLast().draw(deck.draw());}
        }

        Card firstCard = deck.draw();
        deck.playCard(firstCard);
        notifyPlayers(firstCard);
    }

    public void playNextTurn(Card inputCard) {
        if (block) playedCard = null;
        else playedCard = players.get(currentPlayer).move(inputCard);
        notifyPlayers(playedCard);

        if (block) block = false;
        else {
            if (playedCard == null) {
                if (cardsToAdd == 0) players.get(currentPlayer).draw(deck.draw());
                else {
                    for (int i = 0; i < cardsToAdd; i++) players.get(currentPlayer).draw(deck.draw());
                    cardsToAdd = 0;
                }
            } else {
                deck.playCard(playedCard);
                if (playedCard.type == REVERSE) {
                    clockwise = !clockwise;
                }
                if (playedCard.type == PLUS_FOUR) cardsToAdd += 4;
                if (playedCard.type == PLUS_TWO) cardsToAdd += 2;
                if (playedCard.type == BLOCK) block = true;
                if (playedCard.type == CHANGE_COLOR) {
                    int num = players.get(currentPlayer).changeColor();
                    playedCard.color = CardColor.values()[num];
                }
            }

            if (players.get(currentPlayer).getCardNumber() == 0) {
                //if (currentPlayer == 0 && players.get(0) instanceof HumanPlayer) System.out.println("You won!");
                winner = currentPlayer;
                return;
            }
        }

        prevPlayer = currentPlayer;
        if (clockwise) currentPlayer = (currentPlayer + 1) % players.size();
        else currentPlayer = (currentPlayer - 1 + players.size()) % players.size();
    }

    public LinkedList<Integer> getCardNumber(){
        LinkedList<Integer> L = new LinkedList<Integer>();
        for(Player z : players){ L.addLast(z.hand.size());}
        return L;
    }

    public LinkedList<Player> getPlayers() { return players; }
    public Integer getCurrentPlayer(){ return currentPlayer; }
    public Integer getWinner(){ return winner; }
    public boolean getDirection(){ return clockwise; }
    public Card getPlayedCard(){ return playedCard; }

    public LinkedList<Card> getHand() {
        return null;
    }
}
