package com.a11507057.paddy.digital_entropy;

import java.util.Random;

public class HardBot extends Player implements PlayerTurn {
    Random rand = new Random();

    public HardBot(String name, int ID){
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

        // play guard if target is known
        }else if (((getDrawnCard().getValue()==1) || (getInHand().getValue()==1)) && (getCurrentTarget() != -1) && (Players[getCurrentTarget()]).isKnown()) {
            if (getInHand().getValue() == 1) {
                return 1;
            } else {
                return 2;
            }
            // Play a handmaiden if you have a card value higher than 5
        }else if ((getDrawnCard().getValue()==4 || getInHand().getValue()==4)&&(getDrawnCard().getValue()>5 || getInHand().getValue()>5)) {
                if(getInHand().getValue()==4){
                    return 1;
                }
                return 2;

            } else if((((getDrawnCard().getValue()==3) || (getInHand().getValue()==3))) && ((getDrawnCard().getValue()>5) || (getInHand().getValue()>5))) {
            if(getInHand().getValue()==3){
                return 1;
            }else{
                return 2;
            }
        } else if((((getDrawnCard().getValue()==2) || (getInHand().getValue()==2))) && ((getDrawnCard().getValue()==1) || (getInHand().getValue()==1))) {
            if (getInHand().getValue() == 2) {
                return 1;
            } else {
                return 2;
            }
        } else if (getInHand().getValue()>getDrawnCard().getValue()){ // Play whatever card value is lower
            return 2;
        }else{
            return 1;
        }

    }

    public int getTarget(int playedCard, Player [] Players){
        if(playedCard==1){
            if(getCurrentTarget()!=-1){
                return getCurrentTarget();
            }else{
                return rand.nextInt(3);
            }
        } else if(playedCard==2){
            int highest=0;
            for(int i =0;i<getNumberOfPlayers();i++){
                if(i==getID()){

                }else if((Players[i].getLastCardPlayed().getValue()>highest) && (Players[i].isTargetable())){
                    highest=i;
                }
            }
            if(highest==0){
                return getID();
            }else{
                return highest;
            }
        } else if(playedCard==3){
            int lowest=10;
            int unplayed = 0;
            if((getCurrentTarget()!=-1)&&(getCurrentTargetCard()<getInHand().getValue())){
                return getCurrentTarget();
            }
            for(int i =0;i<4;i++){
                if(i==getID()){

                }else if((Players[i].getLastCardPlayed().getValue()<lowest) && (Players[i].isTargetable())){
                    if(Players[i].getLastCardPlayed().getValue()==-1){
                        unplayed=i;
                    }else{
                        lowest=i;
                    }
                }
            }
            if(lowest==10){
                return getID();
            }else{
                return lowest;
            }
        } else if(playedCard==5){
            int highest=0;
            if((getCurrentTarget()!=-1)&&(getCurrentTargetCard()==8)){
                return getCurrentTarget();
            }
            for(int i =0;i<4;i++){
                if(i==getID()){

                }else if((Players[i].getLastCardPlayed().getValue()>highest) && (Players[i].isTargetable())){
                    highest=i;
                }
            }
            if(highest==0){
                return getID();
            }else{
                return highest;
            }
        } else if(playedCard==6) {
            int highest=0;
            if((getCurrentTarget()!=-1)&&(getCurrentTargetCard()>getInHand().getValue())){
                setCurrentTargetCard(getInHand().getValue());
                return getCurrentTarget();
            }
            for(int i =0;i<4;i++){
                if(i==getID()){

                }else if((Players[i].getLastCardPlayed().getValue()>highest) && (Players[i].isTargetable())){
                    highest=i;
                }
            }
            if(highest==0){
                return getID();
            }else{
                return highest;
            }
        }else {
            return -1;
        }

    }

    public int getGuess(int discardProbability){
        if(getCurrentTarget()!=-1){
            return getCurrentTargetCard();
        }
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
