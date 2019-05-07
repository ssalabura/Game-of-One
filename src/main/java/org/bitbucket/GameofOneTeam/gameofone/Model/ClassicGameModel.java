package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;

import static org.bitbucket.GameofOneTeam.gameofone.Model.CardType.*;

public class ClassicGameModel implements GameModel {
    private LinkedList<Player> players = new LinkedList<Player>();
    private Deck deck = new Deck(1);
    public boolean clockwise = true;
    private int cardsToAdd = 0;
    private boolean block = false;
    private int currentPlayer = 0;
    private Integer winner = null;
    private Card playedCard;
    private int difficulty = 0; //0 - easy, 1 - hard

    private void notifyPlayers(Card card){
        for (Player z : players) { z.update(card);}
     }

    public ClassicGameModel(boolean demo){

        for(int i=0;i<4;i++){
            if(demo || i!=0) {
                if(difficulty==0) players.addLast(new EasyBot());
                else players.addLast(new HardBot());
            }
            else players.addLast(new HumanPlayer());

            for(int j=1;j<=7;j++){ players.getLast().draw(deck.draw());}
        }

        playedCard = deck.draw();
        deck.playCard(playedCard);
        notifyPlayers(playedCard);
    }

    public void playNextTurn(Card inputCard, Integer color) {
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
                if (playedCard.type == CHANGE_COLOR || playedCard.type == PLUS_FOUR) {
                    int num = players.get(currentPlayer).changeColor(color);
                    playedCard.color = CardColor.values()[num];
                    playedCard.updateImage();
                }
            }

            if (players.get(currentPlayer).getCardNumber() == 0) {
                winner = currentPlayer;
                return;
            }
        }

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
    public boolean getBlock(){ return block;}
    public Card getPlayedCard() { return playedCard; }
    public Card deckTop() { return deck.top(); }
}
