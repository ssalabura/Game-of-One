package org.bitbucket.GameofOneTeam.gameofone;

import java.util.LinkedList;
import java.util.Random;

public class EasyBot extends Player {
    public Card move() {
        LinkedList<Card> available = getAvailable();
        if(available.isEmpty()) return null;

        Random R = new Random(System.currentTimeMillis());
        Card pickedCard = available.remove(R.nextInt(available.size()));
        hand.remove(pickedCard);
        return pickedCard;
    }
}
