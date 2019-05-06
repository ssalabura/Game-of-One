package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class EasyBot extends Player {
    public Card move(Card inputCard){
        /*try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        LinkedList<Card> available = getAvailable();
        if(available.isEmpty()) return null;

        Random R = new Random(System.currentTimeMillis());
        Card pickedCard = available.remove(R.nextInt(available.size()));
        hand.remove(pickedCard);
        return pickedCard;
    }
    public int changeColor(Integer inputColor) {
        return new Random().nextInt(4);
    }
}
