package com.a11507057.paddy.digital_entropy;
import java.util.Random;
public class RandomBot extends Player implements PlayerTurn {
    Random rand = new Random();

    public RandomBot (String name){
        super();
        this.name = name;
        playerType=1;
        setID(1);
    }

    public int getTurn(Player [] Players){
        return (rand.nextInt(1)+1);

    }

    public int getTarget(int playedCard, Player [] Players){
        return (rand.nextInt(3));
    }

    public int getGuess(int discardProbability){
        return (rand.nextInt(6)+2);
    }
}
