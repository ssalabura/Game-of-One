package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class HardBot extends Player {
    Random r = new Random();
    public Card move(Card inputCard){

        LinkedList<Card> available = getAvailable();
        if(available.isEmpty()) return null;

        try {
            sleep(r.nextInt(200*available.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LinkedList<Card> cards = getHand();
        Card pickedCard = null;
        int pickedCount = 0;
        int b=0,g=0,r=0,y=0;
        for(Card c : cards) {
            if(c.color==CardColor.BLUE) b++;
            else if(c.color==CardColor.GREEN) g++;
            else if(c.color==CardColor.RED) r++;
            else if(c.color==CardColor.YELLOW) y++;
        }
        for(Card c : available) {
            if(c.color==CardColor.BLUE && b>pickedCount) { pickedCard = c; pickedCount = b; }
            else if(c.color==CardColor.GREEN && g>pickedCount) { pickedCard = c; pickedCount = g; }
            else if(c.color==CardColor.RED && r>pickedCount) { pickedCard = c; pickedCount = r; }
            else if(c.color==CardColor.YELLOW && y>pickedCount) { pickedCard = c; pickedCount = y; }
            else if(pickedCard==null) pickedCard = c;
        }

        hand.remove(pickedCard);
        return pickedCard;
    }
    public int changeColor(Integer inputColor) {
        return new Random().nextInt(4);
    }
}
