package com.a11507057.paddy.digital_entropy;

import java.util.Scanner;

public class HumanPlayer extends Player implements PlayerTurn {

    public HumanPlayer() {
        super();
        playerType=0;
        setID(0);
    }

    public int getTurn(Player [] Players){
        Scanner scan = new Scanner(System.in);
        int cardPlayed;
        System.out.println(getName() + " has drawn the " + getDrawnCard().getName() + " Card");
        System.out.println("Enter 1 to play the " +getInHand().getName() + " card or 2 to play the " + getDrawnCard().getName() + " card.");
        cardPlayed = scan.nextInt();

        return cardPlayed;
    }

    public int getTarget(int cardPlayed, Player [] Players){
        Scanner scan = new Scanner(System.in);
        int target;
        System.out.println("Who do you wish to Target? [0-3]");
        return target = scan.nextInt();
    }

    public int getGuess(int discardProbability){
        Scanner scan = new Scanner(System.in);
        int guess;
        System.out.println("What card do they have? [1-8]");
        return guess=scan.nextInt();
    }
}
