package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class EasyBot extends Player {
    public Card move(Card inputCard){

        LinkedList<Card> available = getAvailable();
        if(available.isEmpty()) return null;

        try {
            sleep(r.nextInt(200*available.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Card pickedCard = available.remove(r.nextInt(available.size()));
        hand.remove(pickedCard);
        return pickedCard;
    }
    public int changeColor(Integer inputColor) {
        return r.nextInt(4);
    }
}
