package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;

import static org.bitbucket.GameofOneTeam.gameofone.Model.CardColor.*;
import static org.bitbucket.GameofOneTeam.gameofone.Model.CardType.*;

public abstract class Player {
    LinkedList<Card> hand = new LinkedList<Card>();
    Card currentCard;
    private boolean active = true;

    LinkedList<Card> getAvailable(){
        LinkedList<Card> availableCards = new LinkedList<Card>();

        for(Card z : hand){
            if(active){
                if(currentCard.type == PLUS_FOUR) {
                    if(z.type == PLUS_FOUR) availableCards.addLast(z);
                    else continue;
                }

                else if(currentCard.type == PLUS_TWO){
                    if(z.type == PLUS_TWO || z.type == PLUS_FOUR) availableCards.addLast(z);
                    else continue;
                }
            }

            if(z.type==currentCard.type){
                if(z.type==NUMBER){
                    if(z.number==currentCard.number) { availableCards.addLast(z); continue;}
                }
                else { availableCards.addLast(z); continue;}
            }

            if(z.color == WILD || currentCard.color == WILD) availableCards.addLast(z);
            else if(z.color==currentCard.color) availableCards.addLast(z);
        }

        return availableCards;
    }

    public void draw(Card card){
        if(card != null) hand.addLast(card);
    }

    public int getCardNumber() { return hand.size(); }
    public void update(Card currentCard) {
        if(currentCard != null){ this.currentCard = currentCard; active = true; }
        else active = false;
    }
    public abstract Card move();
    public abstract int changeColor();
}
