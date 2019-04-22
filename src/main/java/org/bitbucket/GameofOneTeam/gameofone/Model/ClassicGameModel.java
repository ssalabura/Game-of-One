package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;

public class ClassicGameModel implements GameModel {
    private LinkedList<Player> players = new LinkedList<Player>();
    private Deck deck = new Deck(1);


    public ClassicGameModel(){
        for(int i=1;i<=5;i++){
            players.addLast(new EasyBot());

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
            Card playedCard = players.get(currentPlayer).move();
            notifyPlayers(playedCard);

            if(playedCard == null) players.get(currentPlayer).draw(deck.draw());
            else deck.playCard(playedCard);

            if(players.get(currentPlayer).getCardNumber()==0){
                System.out.println("Player " + currentPlayer + " wins!");
                return;
            }

            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }

    private void notifyPlayers(Card card){
        for (Player z : players) { z.update(card);}
    }
}
