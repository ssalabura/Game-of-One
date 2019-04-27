package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.Scanner;

public class HumanPlayer extends Player{
    public Card move(Card inputCard) {

        if(inputCard == null) return null;
        hand.remove(inputCard);
        return inputCard;
    }
    public int changeColor(Integer inputColor) {
        return inputColor;
    }
}
