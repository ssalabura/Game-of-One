package org.bitbucket.GameofOneTeam.gameofone;

import static org.bitbucket.GameofOneTeam.gameofone.CardType.*;
import static org.bitbucket.GameofOneTeam.gameofone.CardColor.*;

public class Card {
    final CardColor color;
    final CardType type;
    final int number;
    //we also need to store path to image

    Card(CardColor color,CardType type,int number){
        if(color == null || type==null) throw new NullPointerException("Empty argument");
        if(color == WILD && type!= CHANGE_COLOR && type!= PLUS_FOUR) throw new IllegalArgumentException();
        if(color != WILD && (type == CHANGE_COLOR || type == PLUS_FOUR)) throw new IllegalArgumentException();
        if(type != NUMBER && number!=0) throw new IllegalArgumentException();
        if(type == NUMBER && (number<0 || number>9)) throw new IllegalArgumentException();

        this.color = color;
        this.type = type;
        this.number = number;
    }
}
