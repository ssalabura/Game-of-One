package org.bitbucket.GameofOneTeam.gameofone;

import java.util.LinkedList;
import java.util.Random;

public class EasyBot extends Player {
    public Card move(Card currentCard) {
        LinkedList<Card> available = getAvailable(currentCard);
        if(available.isEmpty()) return null;

        Random R = new Random(System.currentTimeMillis());
        return available.remove(R.nextInt(available.size()));
    }
}
