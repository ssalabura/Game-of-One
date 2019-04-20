package org.bitbucket.GameofOneTeam.gameofone;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import static org.bitbucket.GameofOneTeam.gameofone.CardColor.*;
import static org.bitbucket.GameofOneTeam.gameofone.CardType.*;

public class Deck {
    private LinkedList<Card> draw_pile = new LinkedList<Card>();
    private LinkedList<Card> played_pile = new LinkedList<Card>();

    Deck(int n){
        if(n<1 || n>4) throw new IllegalArgumentException("Wrong number of decks");

        for(int i=0;i<n;i++) {
            for(CardColor C : CardColor.values()) {
                if(C == WILD) continue;
                draw_pile.add(new Card(C,NUMBER,0));
                for(int k=0;k<2;k++) {
                    for (int j = 1; j <= 9; j++) {
                        draw_pile.add(new Card(C, NUMBER, j));
                    }
                    draw_pile.add(new Card(C, BLOCK, 0));
                    draw_pile.add(new Card(C, REVERSE, 0));
                    draw_pile.add(new Card(C, PLUS_TWO, 0));
                }
            }
            for(int k=0;k<4;k++) {
                draw_pile.add(new Card(WILD, PLUS_FOUR, 0));
                draw_pile.add(new Card(WILD, CHANGE_COLOR, 0));
            }
        }

        shuffle();
    }

    private void shuffle(){ Collections.shuffle(draw_pile,new Random(System.currentTimeMillis())); }

    public Card draw(){
        if(draw_pile.isEmpty()){
            if(played_pile.size() == 1) return null;

            Card tmp = played_pile.removeLast();
            draw_pile.addAll(played_pile);
            played_pile.clear();
            played_pile.add(tmp);

            shuffle();
        }

        return draw_pile.removeLast();
    }

    public void playCard(Card card){
        played_pile.addLast(card);
    }

    public int size(){
       return played_pile.size() + draw_pile.size();
    }
}
