package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.Scanner;

public class HumanPlayer extends Player{
    public Card move(Card inputCard) {

        if(inputCard == null) return null;
        hand.remove(inputCard);
        return inputCard;
    }
    public int changeColor() {
        System.out.println("Choose color:");
        for(int i=0;i<4;i++) {
            System.out.println(i + " " + CardColor.values()[i]);
        }
        System.out.println("Your choice:");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
}
