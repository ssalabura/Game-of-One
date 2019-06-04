package org.bitbucket.GameofOneTeam.gameofone.Model;

import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import org.bitbucket.GameofOneTeam.gameofone.View.ClassicGame;

import java.util.LinkedList;

import static org.bitbucket.GameofOneTeam.gameofone.Model.CardType.*;
import static org.bitbucket.GameofOneTeam.gameofone.View.Settings.cards;
import static org.bitbucket.GameofOneTeam.gameofone.View.Settings.difficulty;

public class BattleRoyaleModel implements GameModel{
    private LinkedList<Player> players = new LinkedList<Player>();
    private Deck deck = new Deck(1);
    public boolean clockwise = true;
    private int cardsToAdd = 0;
    private boolean block = false;
    private int currentPlayer = 0;
    private Integer winner = null;
    private Card playedCard;
    private int eliminatedCount = 0;

    private void notifyPlayers(Card card){
        for (Player z : players) { z.update(card);}
    }

    public BattleRoyaleModel(boolean demo){
        for(int i=0;i<4;i++){
            if(demo || i!=0) {
                if(difficulty==0) players.addLast(new EasyBot());
                else if(difficulty==1) players.addLast(new MediumBot());
                else if(demo) players.addLast(new HardBot(null));
                else players.addLast(new HardBot((HumanPlayer) players.get(0)));
            }
            else players.addLast(new HumanPlayer());

            for(int j=0;j<cards;j++){ players.getLast().draw(deck.draw());}
        }

        do {
            playedCard = deck.draw();
        } while (playedCard.type != NUMBER);
        deck.playCard(playedCard);
        notifyPlayers(playedCard);
    }

    public void playNextTurn(Card inputCard, Integer color) {
        if (block) playedCard = null;
        else playedCard = players.get(currentPlayer).move(inputCard);
        notifyPlayers(playedCard);

        players.get(currentPlayer).draw(deck.draw());

        if(block) block = false;
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
        }
        //Eliminate player if he has more than 10 cards
        if (players.get(currentPlayer).getCardNumber() >= 10) {
            if(currentPlayer == 0) {
                winner = 2;
                return;
            }
            players.get(currentPlayer).eliminated = true;
            eliminatedCount++;
            players.get(currentPlayer).die();
        }

        //Check if there's only 1 player left. If yes end game
        if(eliminatedCount == players.size()-1)
        {
            winner = 0;
            return;
        }
        //Change current player to the next one
        do
        {
            if (clockwise) currentPlayer = (currentPlayer + 1) % players.size();
            else currentPlayer = (currentPlayer - 1 + players.size()) % players.size();
        }while(players.get(currentPlayer).eliminated == true);
    }

    public LinkedList<Player> getPlayers() { return players; }
    public Integer getCurrentPlayer() { return currentPlayer; }
    public Integer getWinner() { return winner; }
    public boolean getBlock() { return block; }
    public boolean isClockwise() { return clockwise; }
    public Card getPlayedCard() { return playedCard; }
    public Card deckTop() { return deck.top(); }
}
