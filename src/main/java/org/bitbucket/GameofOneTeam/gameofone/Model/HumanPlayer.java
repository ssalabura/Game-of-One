package org.bitbucket.GameofOneTeam.gameofone.Model;

public class HumanPlayer extends Player{
    public Card move(Card inputCard) {
        if(inputCard == null) return null;
        cardInd = hand.indexOf(inputCard);
        hand.remove(inputCard);
        return inputCard;
    }
    public int changeColor(Integer inputColor) {
        return inputColor;
    }
}
