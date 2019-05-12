package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class EasyBot extends Player {
    public Card move(Card inputCard){

        LinkedList<Card> available = getAvailable();
        try {
            sleep(r.nextInt(200*available.size() + 500) + 300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(available.isEmpty()) return null;


        Card pickedCard = available.remove(r.nextInt(available.size()));

        cardInd = hand.indexOf(pickedCard);
        hand.remove(pickedCard);
        return pickedCard;
    }
    public int changeColor(Integer inputColor) {
        return r.nextInt(4);
    }
}
