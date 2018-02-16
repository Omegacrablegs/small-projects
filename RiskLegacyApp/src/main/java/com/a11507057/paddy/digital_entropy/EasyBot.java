package com.a11507057.paddy.digital_entropy;

import java.util.Random;

public class EasyBot extends Player implements PlayerTurn {
    Random rand = new Random();
    public EasyBot(String name, int ID){
        super();
        this.name = name;
        playerType=1;
        setID(ID);
    }

    public int getTurn(Player [] Players){
        // Always play the other card if you have a Princess
        if(getDrawnCard().getValue()==8){
            return 1;
        } else if(getInHand().getValue()==8){
            return 2;
        } else if(getInHand().getValue()==7){ // Don't play the countess unless you have to
            if(getDrawnCard().getValue()>4){
                return 1;
            }
            return 2;
        } else if(getDrawnCard().getValue()==7){
            if(getInHand().getValue()>4){
                return 2;
            }
            return 1;
        } else if (getInHand().getValue()>getDrawnCard().getValue()){ // Play whatever card value is lower
            return 2;
        }else{
            return 1;
        }


    }

    public int getTarget(int playedCard, Player [] Players){
        for(int i = 0; i<20;i++){
            int guess = rand.nextInt(3);
            if (Players[guess].isTargetable() && guess != getID()){
                return guess;
            }
        }
        return getID();
    }

    public int getGuess(int discardProbability){
        if(getInHand().getValue()==discardProbability){
            if(discardProbability==2){
                return discardProbability+1;
            }else{
                return discardProbability-1;
            }

        }
        return discardProbability;
    }
}
