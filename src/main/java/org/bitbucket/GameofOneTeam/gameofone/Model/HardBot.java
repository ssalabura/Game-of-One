package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class HardBot extends Player {
    public Card move(Card inputCard){
        LinkedList<Card> available = getAvailable();
        try {
            sleep(r.nextInt(200*available.size() + 500) + 300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(available.isEmpty()) return null;

        Card pickedCard = null;
        int pickedCount = 0;
        int b=0,g=0,r=0,y=0;
        for(Card c : hand) {
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

        cardInd = hand.indexOf(pickedCard);
        hand.remove(pickedCard);
        return pickedCard;
    }
    public int changeColor(Integer inputColor) {
        int b=0,g=0,r=0,y=0;
        int output = 0, outputCount = 0;
        for(Card c : hand) {
            if(c.color==CardColor.BLUE) { b++; if(b>outputCount) { output = 0; outputCount = b; } }
            else if(c.color==CardColor.GREEN) { g++; if(g>outputCount) { output = 1; outputCount = g; } }
            else if(c.color==CardColor.RED) { r++; if(r>outputCount) { output = 2; outputCount = r; } }
            else if(c.color==CardColor.YELLOW) { y++; if(y>outputCount) { output = 3; outputCount = y; } }
        }
        return output;
    }
}
