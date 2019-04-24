package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;

public class ClassicGameModel implements GameModel {
    private LinkedList<Player> players = new LinkedList<Player>();
    private Deck deck = new Deck(1);


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
            Card playedCard = players.get(currentPlayer).move();
            notifyPlayers(playedCard);

            if(playedCard == null) players.get(currentPlayer).draw(deck.draw());
            else deck.playCard(playedCard);

            if(players.get(currentPlayer).getCardNumber()==0){
                if(currentPlayer==0 && players.get(0) instanceof HumanPlayer) System.out.println("You won!");
                else System.out.println("EasyBot " + currentPlayer + " wins!");
                return;
            }

            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }

    private void notifyPlayers(Card card){
        for (Player z : players) { z.update(card);}
    }
}
