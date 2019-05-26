package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class HardBot extends Player {
    private final HumanPlayer human;
    HardBot(HumanPlayer human){
        this.human=human;
    }

    public Card move(Card inputCard){
        LinkedList<Card> available = getAvailable();
        try {
            sleep(r.nextInt(200*available.size() + 500) + 300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int b=0,g=0,r=0,y=0;

        if(available.isEmpty()) return null;
        if(human!=null){
            boolean blue = false;
            boolean green = false;
            boolean yellow = false;
            boolean red = false;

            for(Card c : human.getHand()){
                blue = blue || (c.color==CardColor.BLUE);
                green = green || (c.color==CardColor.GREEN);
                yellow = yellow || (c.color==CardColor.YELLOW);
                red = red || (c.color==CardColor.RED);
            }

            if(human.getHand().size()==1) {
                Card cardToPlay = null;
                for (Card c : getAvailable()) {
                    if (!blue && c.color == CardColor.BLUE) cardToPlay = c;
                    if (!green && c.color == CardColor.GREEN) cardToPlay = c;
                    if (!yellow && c.color == CardColor.YELLOW) cardToPlay = c;
                    if (!red && c.color == CardColor.RED) cardToPlay = c;
                }

                if (cardToPlay != null) {
                    cardInd = hand.indexOf(cardToPlay);
                    hand.remove(cardToPlay);
                    return cardToPlay;
                }
            }

            if(hand.size()>3){
                if(!blue) b+=3;
                if(!green) g+=3;
                if(!yellow) y+=3;
                if(!red) r+=3;
            } else if(hand.size() == 3){
                if(!blue) b++;
                if(!green) g++;
                if(!yellow) y++;
                if(!red) r++;
            }
        }

        Card pickedCard = null;
        int pickedCount = 0;
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

        if(human!=null){
            boolean blue = false;
            boolean green = false;
            boolean yellow = false;
            boolean red = false;

            for(Card c : human.getHand()){
                blue = blue || (c.color==CardColor.BLUE);
                green = green || (c.color==CardColor.GREEN);
                yellow = yellow || (c.color==CardColor.YELLOW);
                red = red || (c.color==CardColor.RED);
            }

            if(human.getHand().size() == 1) {
                if (!blue) return 0;
                else if (!green) return 1;
                else if (!red) return 2;
                else if (!yellow) return 3;
            }

            if(hand.size()>3){
                if(!blue) b+=4;
                if(!green) g+=4;
                if(!yellow) y+=4;
                if(!red) r+=4;
            } else if(hand.size() == 3){
                if(!blue) b++;
                if(!green) g++;
                if(!yellow) y++;
                if(!red) r++;
            }
        }

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
