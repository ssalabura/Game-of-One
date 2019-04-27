package org.bitbucket.GameofOneTeam.gameofone.Model;

import javafx.scene.image.Image;

import static org.bitbucket.GameofOneTeam.gameofone.Model.CardType.*;
import static org.bitbucket.GameofOneTeam.gameofone.Model.CardColor.*;

public class Card {
    CardColor color;
    final CardType type;
    final int number;
    Image image;

    Card(CardColor color,CardType type,int number){
        if(color == null || type==null) throw new NullPointerException("Empty argument");
        if(color == WILD && type!= CHANGE_COLOR && type!= PLUS_FOUR) throw new IllegalArgumentException();
        if(color != WILD && (type == CHANGE_COLOR || type == PLUS_FOUR)) throw new IllegalArgumentException();
        if(type != NUMBER && number!=0) throw new IllegalArgumentException();
        if(type == NUMBER && (number<0 || number>9)) throw new IllegalArgumentException();

        this.color = color;
        this.type = type;
        this.number = number;
        image = new Image("/" + color.toString().toLowerCase() + "_" + type.toString().toLowerCase() + "_" + number + ".png");
    }

    public Image getImage() { return image; }

    @Override
    public String toString() {
        return color.toString() + " " + type.toString() + " " + number;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Card)) return false;
        return (((Card) obj).color == this.color) && (((Card) obj).type == this.type) && (((Card) obj).number == this.number);
    }

    @Override
    public int hashCode() {
        return color.hashCode() * type.hashCode() * (number+17);
    }
}
