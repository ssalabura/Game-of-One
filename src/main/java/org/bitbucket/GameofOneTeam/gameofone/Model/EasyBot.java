package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;
import java.util.Random;

public class EasyBot extends Player {
    public Card move(Card inputCard) {
        LinkedList<Card> available = getAvailable();
        if(available.isEmpty()) return null;

        Random R = new Random(System.currentTimeMillis());
        Card pickedCard = available.remove(R.nextInt(available.size()));
        hand.remove(pickedCard);
        return pickedCard;
    }
    public int changeColor() {
        return new Random().nextInt(4);
    }
}
