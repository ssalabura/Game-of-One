package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;

import static org.bitbucket.GameofOneTeam.gameofone.Model.CardType.*;

public class ClassicGameModel implements GameModel {
    private LinkedList<Player> players = new LinkedList<Player>();
    private Deck deck = new Deck(1);
    private boolean clockwise = true;
    private int cardsToAdd = 0;

    public ClassicGameModel(boolean demo){

        for(int i=1;i<=5;i++){
            if(demo || i!=1) players.addLast(new EasyBot());
            else players.addLast(new HumanPlayer());

            for(int j=1;j<=7;j++){ players.getLast().draw(deck.draw());}
        }
    }

    public void run(){
        int currentPlayer = 0;
        System.out.println("Start!");
        Card firstCard = deck.draw();
        deck.playCard(firstCard);
        notifyPlayers(firstCard);

        while (true){
            System.out.println(getCardNumber());

            Card playedCard = players.get(currentPlayer).move();
            notifyPlayers(playedCard);
            System.out.println("< " + currentPlayer + " " + playedCard + " >");

            if(playedCard == null) {
                if(cardsToAdd == 0) players.get(currentPlayer).draw(deck.draw());
                else {
                    for(int i=1;i<=cardsToAdd;i++) players.get(currentPlayer).draw(deck.draw());
                    cardsToAdd = 0;
                }
            }

            else {
                deck.playCard(playedCard);
                if(playedCard.type == REVERSE) { clockwise = !clockwise; }
                if(playedCard.type == PLUS_FOUR) cardsToAdd += 4;
                if(playedCard.type == PLUS_TWO) cardsToAdd += 2;
            }

            if(players.get(currentPlayer).getCardNumber()==0){
                if(currentPlayer==0 && players.get(0) instanceof HumanPlayer) System.out.println("You won!");
                else System.out.println("EasyBot " + currentPlayer + " wins!");
                return;
            }

            if(clockwise) currentPlayer = (currentPlayer + 1) % players.size();
            else currentPlayer = (currentPlayer - 1 + players.size()) % players.size();
        }
    }

    LinkedList<Integer> getCardNumber(){
        LinkedList<Integer> L = new LinkedList<Integer>();
        for(Player z : players){ L.addLast(z.hand.size());}
        return L;
    }

    private void notifyPlayers(Card card){
        for (Player z : players) { z.update(card);}
    }
}
