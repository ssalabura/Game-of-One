package org.bitbucket.GameofOneTeam.gameofone.Model;

import java.util.Scanner;

import java.util.LinkedList;

public class HumanPlayer extends Player{
    public Card move() {
        System.out.println("Your turn: " + currentCard + " " + hand);
        LinkedList<Card> available = getAvailable();
        if(available.isEmpty()) return null;
        for(int i=0;i<available.size();i++){
            System.out.println(i + " " + available.get(i));
        }

        System.out.println("Your choice (number) :");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        Card pickedCard = available.remove(num);
        hand.remove(pickedCard);
        return pickedCard;
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
