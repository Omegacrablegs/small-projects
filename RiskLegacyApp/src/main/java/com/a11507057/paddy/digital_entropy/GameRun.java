package com.a11507057.paddy.digital_entropy;

import android.content.SharedPreferences;

import java.util.Random;
import java.util.Scanner;

public class GameRun {
    // Declare the cards for the game, Starting with 5 Guards Cards
    private Card Guard1 = new Card("Guard",10,10,1,1);
    private Card Guard2 = new Card("Guard",10,10,1,2);
    private Card Guard3 = new Card("Guard",10,10,1,3);
    private Card Guard4 = new Card("Guard",10,10,1,4);
    private Card Guard5 = new Card("Guard",10,10,1,5);
    // 2 Priest Cards
    private Card Priest1 = new Card("Priest",10,10,2,6);
    private Card Priest2 = new Card("Priest",10,10,2,7);
    // 2 Baron Cards
    private Card Baron1 = new Card("Baron",10,10,3,8);
    private Card Baron2 = new Card("Baron",10,10,3,9);
    // 2 Handmaiden Cards
    private Card Handmaiden1 = new Card("Handmaiden",10,10,4,10);
    private Card Handmaiden2 = new Card("Handmaiden",10,10,4,11);
    // 2 Prince Cards
    private Card Prince1 = new Card("Prince",10,10,5,12);
    private Card Prince2 = new Card("Prince",10,10,5,13);
    // 1 King Cards
    private Card King = new Card("King",10,10,6,14);
    // 1 Countess
    private Card Countess = new Card("Countess",10,10,7,15);
    // 1 Princess
    private Card Princess = new Card("Princess",10,10,8,16);

    private Card NULL = new Card("NULL",10,10,-1,-1);

    // Declare 4 players for the game, although not all will be actually used

    private  EasyBot Player0 = new EasyBot("HumanPerson",0);

    private Player Player1 = new Player();
    private Player Player2 = new Player();
    private Player Player3 = new Player();

    private int numberOfPlayers;
    private int roundNumber;
    private int discardTracker=0;
    private Card [] Deck = new Card [16];  // Deck size is the same each game
    private int [] GlobalDiscard = new int [20];
    private Player [] Players = new Player [] {Player0,Player1,Player2,Player3};
    private boolean isRunning = true;
    private int currentPlayer;
    private int targetPlayer;
    private int lastRoundWinner = 0;

    /**
     * digital_entropy.Main Method
     *
     */
    public void startGame() {
        makeDeck();
        makePlayers();
        int roundWinner;
        while (isRunning){
            roundWinner = runGame();
            switch (roundWinner) {
                case 0:
                    Players[0].tickWinNumber();
                    break;
                case 1:
                    Players[1].tickWinNumber();
                    break;
                case 2:
                    Players[2].tickWinNumber();
                    break;
                case 3:
                    Players[3].tickWinNumber();
                    break;
            }
            if(Players[0].isWinner()||Players[1].isWinner()||Players[2].isWinner()||Players[3].isWinner()){
                isRunning = false;
            }
        }

    }


    /**
     * Prepares the Deck for the game, deck construction is the same each game but shuffled after being created
     *
     */
    public void makeDeck(){
        Deck[0] = Guard1;
        Deck[1] = Guard2;
        Deck[2] = Guard3;
        Deck[3] = Guard4;
        Deck[4] = Guard5;
        Deck[5] = Priest1;
        Deck[6] = Priest2;
        Deck[7] = Baron1;
        Deck[8] = Baron2;
        Deck[9] = Handmaiden1;
        Deck[10] = Handmaiden2;
        Deck[11] = Prince1;
        Deck[12] = Prince2;
        Deck[13] = King;
        Deck[14] = Countess;
        Deck[15] = Princess;
        shuffleDeck();

    }

    /**
     * Selects the number of players in the game, the type of bots and the name of the player
     */
    public  void makePlayers(){
        String playerName = "Player1";
        Player0.setName(playerName);
        numberOfPlayers = 3;
        int difficulty = 2;
        if(difficulty==1){
            EasyBot Player1 = new EasyBot("EasyBot1",1);
            EasyBot Player2 = new EasyBot("EasyBot2",2);
            EasyBot Player3 = new EasyBot("EasyBot3",3);
            Players[1] =  Player1;
            Players[2] =  Player2;
            Players[3] =  Player3;
        }else if(difficulty==2){
            MediumBot Player1 = new MediumBot("MediumBot1",1);
            MediumBot Player2 = new MediumBot("MediumBot2",2);
            MediumBot Player3 = new MediumBot("MediumBot3",3);
            Players[1] =  Player1;
            Players[2] =  Player2;
            Players[3] =  Player3;
        }else if(difficulty==3){
            HardBot Player1 = new HardBot("HardBot1",1);
            HardBot Player2 = new HardBot("HardBot2",2);
            HardBot Player3 = new HardBot("HardBot3",3);
            Players[1] =  Player1;
            Players[2] =  Player2;
            Players[3] =  Player3;
        }
        for(int i = 0; i<numberOfPlayers;i++){
            Players[i].setNumberOfPlayers(numberOfPlayers);
        }
        resetPlayers();
    }

    /**
     *  Resets all players to be playing depending on how many are selected at the start
     */
    public  void resetPlayers(){
        for(int i =0; i<=numberOfPlayers;i++){
            Players[i].setPlaying(true);
            Players[i].setTargetable(true);
            Players[i].setLastCardPlayed(NULL);
            Players[i].setInHand(NULL);
        }
    }

    /**
     * Resets the players card and then draws from the start of the deck
     */
    public void drawStartingHand(){
        // gives each player a card
        if(Players[3].isPlaying()){
            Players[0].setInHand(Deck[1]);;
            Players[1].setInHand(Deck[2]);
            Players[2].setInHand(Deck[3]);
            Players[3].setInHand(Deck[4]);
            roundNumber = 5;
        }else if (Players[2].isPlaying()){
            Players[0].setInHand(Deck[1]);
            Players[1].setInHand(Deck[2]);
            Players[2].setInHand(Deck[3]);
            roundNumber = 4;
        }else {
            Players[0].setInHand(Deck[1]);
            Players[1].setInHand(Deck[2]);
            roundNumber = 3;
        }
    }

    /**
     *  Plays the game out until a player wins a round
     *
     * @return return an int to show which player has won the round
     */
    public int runGame(){
        // first card is discarded
        currentPlayer = lastRoundWinner;
        int cardSelection;
        discardTracker = 0;
        drawStartingHand();
        // Game runs until one player remains or the deck runs out of cards
        while   ((((Players[0].isPlaying()&&(Players[1].isPlaying()||Players[2].isPlaying()||Players[3].isPlaying()))
                ||(Players[1].isPlaying()&&(Players[0].isPlaying()||Players[2].isPlaying()||Players[3].isPlaying()))
                ||(Players[2].isPlaying()&&(Players[0].isPlaying()||Players[1].isPlaying()||Players[3].isPlaying()))
                ||(Players[3].isPlaying()&&(Players[0].isPlaying()||Players[1].isPlaying()||Players[2].isPlaying())))&& roundNumber <15) ){


            if(Players[currentPlayer].isPlaying()) {
                Players[currentPlayer].setTargetable(true);
                Players[currentPlayer].setDrawnCard(Deck[roundNumber]);
                cardSelection = Players[currentPlayer].getTurn(Players);
                if(cardSelection==1){
                    Players[currentPlayer].setKnown(false);
                    cardSelection = Players[currentPlayer].getInHand().getValue();
                    GlobalDiscard[discardTracker] = Players[currentPlayer].getInHand().getValue();
                    discardTracker++;
                    Players[currentPlayer].setLastCardPlayed(Players[currentPlayer].getInHand());
                    Players[currentPlayer].setInHand(Players[currentPlayer].getDrawnCard());
                }else{
                    cardSelection = Players[currentPlayer].getDrawnCard().getValue();
                    GlobalDiscard[discardTracker] = Players[currentPlayer].getDrawnCard().getValue();
                    discardTracker++;
                    Players[currentPlayer].getInHand();
                }
                playedCard(cardSelection);

                if (currentPlayer == numberOfPlayers) {
                    currentPlayer = 0;
                    roundNumber++;
                } else {
                    currentPlayer++;
                    roundNumber++;
                }
            }
                else if(currentPlayer == numberOfPlayers){
                    currentPlayer=0;
                    roundNumber++;
                }else {
                    currentPlayer++;
                    roundNumber++;
                }
            }

        // Round over game is reset for the next round
        shuffleDeck();
        for(int i = 0; i<GlobalDiscard.length;i++){
            GlobalDiscard[i] = 0;
        }

        discardTracker=0;
        if(Players[0].isPlaying()){
            resetPlayers();
            lastRoundWinner=0;
            return 0;
        }else if(Players[1].isPlaying()){
            resetPlayers();
            lastRoundWinner=1;
            return 1;
        }else if(Players[2].isPlaying()){
            resetPlayers();
            lastRoundWinner=2;
            return 2;
        }else{
            resetPlayers();
            lastRoundWinner=3;
            return 3;
        }
    }

    /**
     * Fisher–Yates shuffle with O(n) complexity
     *
     */
    public void shuffleDeck() {
        Random card = new Random();
        for (int i = Deck.length - 1; i > 0; i--) {
            int index = card.nextInt(i);
            // swap positions;
            Card temp = Deck[index];
            Deck[index] = Deck[i];
            Deck[i] = temp;
        }
    }

    /**
     * Method to help set up the played card effects by passing the player and card chosen
     * @param card the card played, either 1 or 2
     */
    public void playedCard(int card){
        switch(card){
            case 1 :
                do {
                    targetPlayer = Players[currentPlayer].getTarget(1,Players);
                    if((Players[targetPlayer].isTargetable()==false)&&(Players[currentPlayer].getPlayerType()==0)){
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                int guess;
                do {
                    guess = Players[currentPlayer].getGuess(getDiscardProbability());
                    if(guess==1){
                    }
                }while(guess == 1);
                playGuard(guess);
                break;
            case 2:
                do {
                    targetPlayer = Players[currentPlayer].getTarget(2,Players);
                    if((Players[targetPlayer].isTargetable()==false)&&(Players[currentPlayer].getPlayerType()==0)){
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                playPriest();
                break;
            case 3:
                do {
                    targetPlayer = Players[currentPlayer].getTarget(3,Players);
                }while(Players[targetPlayer].isTargetable()==false);
                playBaron();
                break;
            case 4:
                playHandmaiden();
                break;
            case 5:
                do {
                    targetPlayer = Players[currentPlayer].getTarget(5,Players);
                    if((Players[targetPlayer].isTargetable()==false)&&(Players[currentPlayer].getPlayerType()==0)){
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                playPrince();
                break;
            case 6:
                do {
                    targetPlayer = Players[currentPlayer].getTarget(6,Players);
                    if((Players[targetPlayer].isTargetable()==false)&&(Players[currentPlayer].getPlayerType()==0)){
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                playKing();
                break;
            case 7:
                playCountess();
                break;
            case 8:
                playPrincess();
                break;
            default:
                break;
        }
    }

    // Methods for playing each card,

    /**
     * Guard picks a player and checks is guess == targets card value
     * @param guess The card type the player believes target has
     */

    public void playGuard(int guess) {
        if (Players[targetPlayer].getInHand().getValue() == guess) {
            Players[targetPlayer].setPlaying(false);
            Players[targetPlayer].setTargetable(false);
        } else {
        }
    }

    /**
     * Priest allows a player to see another players card
     */
    public void playPriest(){
        if(Players[currentPlayer].getPlayerType()==0){
        }
        Players[currentPlayer].setCurrentTarget(targetPlayer);
        Players[currentPlayer].setCurrentTargetCard(Players[targetPlayer].getInHand().getID());
        Players[currentPlayer].setKnown(true);
    }

    /**
     * Baron compares the value of the players card and the targets and the loser is eliminated
     */
    public void playBaron(){
        if(Players[currentPlayer].getInHand().getValue()>Players[targetPlayer].getInHand().getValue()){
            Players[targetPlayer].setPlaying(false);
            Players[targetPlayer].setTargetable(false);
        }else if(Players[currentPlayer].getInHand().getValue()==Players[targetPlayer].getInHand().getValue()){
        }else{
            Players[currentPlayer].setPlaying(false);
            Players[currentPlayer].setTargetable(false);
        }
    }

    /**
     * Playing the handmaiden makes the player untargetable
     */
    public void playHandmaiden(){
        Players[currentPlayer].setTargetable(false);
    }

    /**
     * The Price digital_entropy.Card causes the target to discard his current card and redrawn a new card from the deck
     * If the princess is discarded the player is defeated
     */
    public void playPrince() {
        if (Players[targetPlayer].getInHand().getValue() == 8) {
            Players[targetPlayer].setPlaying(false);
            Players[targetPlayer].setTargetable(false);
            Players[targetPlayer].setInHand(NULL);
        } else {
            roundNumber++;
            Players[targetPlayer].setInHand(Deck[roundNumber]);

        }
    }

    /**
     * The King digital_entropy.Card causes the target to trade their hand with the player who played the king
     */
    public void playKing(){
        Card tempCard;
        tempCard = Players[currentPlayer].getInHand();
        Players[currentPlayer].setInHand(Players[targetPlayer].getInHand());
        Players[targetPlayer].setInHand(tempCard);
        if(Players[currentPlayer].getPlayerType()==0){
        }
    }

    /**
     *  The Countess has no effect when played but must be played when a player has either a Prince or the King digital_entropy.Card
     */
    public void playCountess(){
    }

    /**
     * The Princess if played or discarded causes the player who played her to be defeated
     */
    public void playPrincess() {
        Players[currentPlayer].setPlaying(false);
        Players[currentPlayer].setTargetable(false);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public GameRun(){

    }

    public int getDiscardProbability(){
        int priest=2;
        int baron=2;
        int handmaiden=2;
        int prince=2;
        int king=1;
        int countess=1;
        int princess=1;

        for(int i =0; i < discardTracker; i++){
            int x = GlobalDiscard[i];
            if(x == 2){
                priest--;
            }else if(x==3){
                baron--;
            }else if(x==4){
                handmaiden--;
            }else if(x==5){
                prince--;
            }else if(x==6){
                king--;
            }else if(x==7){
                countess--;
            }else{
                princess--;
            }
        }
        if(prince==2){
            return 5;
        }else if (handmaiden==2){
            return 4;
        }else if (baron==2){
            return 3;
        }else if (priest==2){
            return 2;
        }else if(princess==1){
            return 8;
        }else if(countess==1){
            return 7;
        }else if(king==1){
            return 6;
        }else if (prince==1){
            return 5;
        }else if(handmaiden==1){
            return 4;
        }else if(baron==1){
            return 3;
        }else{
            return 2;
        }
    }

}
