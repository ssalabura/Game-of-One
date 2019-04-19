package org.bitbucket.GameofOneTeam.gameofone;

import java.util.LinkedList;

import static org.bitbucket.GameofOneTeam.gameofone.CardColor.*;
import static org.bitbucket.GameofOneTeam.gameofone.CardType.*;

public abstract class Player {
    protected LinkedList<Card> hand = new LinkedList<Card>();

    protected LinkedList<Card> getAvailable(Card currentCard){
        LinkedList<Card> availableCards = new LinkedList<Card>();

        for(Card z : hand){
            if(z.type==currentCard.type){
                if(z.type==NUMBER){
                    if(z.number==currentCard.number) { availableCards.addLast(z); continue;}
                }
                else { availableCards.addLast(z); continue;}
            }

            if(z.color == WILD) availableCards.addLast(z);
            else if(z.color==currentCard.color) availableCards.addLast(z);
        }

        return availableCards;
    }

    public void draw(Card card){
        hand.addLast(card);
    }

    public abstract Card move(Card currentCard);
}
