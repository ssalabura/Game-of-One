package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class EasyBot extends Player {
    Random r = new Random();
    public Card move(Card inputCard){

        LinkedList<Card> available = getAvailable();
        if(available.isEmpty()) return null;

        try {
            sleep(r.nextInt(200*available.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Random R = new Random(System.currentTimeMillis());
        Card pickedCard = available.remove(R.nextInt(available.size()));
        hand.remove(pickedCard);
        return pickedCard;
    }
    public int changeColor(Integer inputColor) {
        return new Random().nextInt(4);
    }
}
