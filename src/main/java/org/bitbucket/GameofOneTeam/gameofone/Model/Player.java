package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.LinkedList;

import static org.bitbucket.GameofOneTeam.gameofone.Model.CardColor.*;
import static org.bitbucket.GameofOneTeam.gameofone.Model.CardType.*;

public abstract class Player {
    LinkedList<Card> hand = new LinkedList<Card>();
    private Card currentCard;
    private boolean active = true;

    public final LinkedList<Card> getAvailable(){
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

    void draw(Card card){
        if(card != null) hand.addLast(card);
    }

    public LinkedList<Card> getHand() { return hand; }
    public int getCardNumber() { return hand.size(); }
    final void update(Card currentCard) {
        if(currentCard != null){ this.currentCard = currentCard; active = true; }
        else active = false;
    }

    public abstract Card move(Card inputCard);
    public abstract int changeColor(Integer inputColor);
}
