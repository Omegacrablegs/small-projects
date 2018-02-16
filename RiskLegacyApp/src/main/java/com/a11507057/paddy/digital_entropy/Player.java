package com.a11507057.paddy.digital_entropy;

public class Player implements PlayerTurn {
    private int  winNumber, ID, currentTarget = -1, currentTargetCard = -1, numberOfPlayers;
    private Card inHand;
    private Card drawnCard;
    private Card lastCardPlayed;
    private boolean isPlaying=false;
    private boolean isTargetable=false;
    private boolean isWinner=false;
    private boolean isKnown=false;
    protected int playerType;
    protected String name;



    public Player(){
        this.name = "NULL" ;
        this.winNumber = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public void setTargetable(boolean targetable) {
        isTargetable = targetable;
    }


    public void tickWinNumber(){
        this.winNumber++;
        System.out.println("Round Over "+ this.name+" wins a point!");
        if (this.winNumber ==3){
            this.isWinner = true;
            System.out.println(this.name+" has won three points and wins the game!!!");
        }
    }

    public void setDrawnCard(Card drawnCard) {
        this.drawnCard = drawnCard;
    }

    public void setInHand(Card inHand){
        this.inHand = inHand;
    }

    public Card getDrawnCard() {
        return drawnCard;
    }

    public Card getInHand() {
        return inHand;
    }

    public boolean isTargetable(){
        return isTargetable;
    }

    public int getPlayerType() {
        return playerType;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isWinner(){
        return isWinner;
    }

    public String getName() {
        return name;
    }

    public  int getTurn(Player [] Players){
        return -1;
    }

    public int getTarget(int playedCard, Player [] Players){
        return -1;
    }

    public int getGuess(int discardProbability){
        return -1;
    }

    public boolean isKnown() {
        return isKnown;
    }

    public void setKnown(boolean known) {
        isKnown = known;
    }

    public void setCurrentTarget(int currentTarget) {
        this.currentTarget = currentTarget;
    }

    public int getCurrentTarget() {
        return currentTarget;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Card getLastCardPlayed() {
        return lastCardPlayed;
    }

    public void setLastCardPlayed(Card lastCardPlayed) {
        this.lastCardPlayed = lastCardPlayed;
    }

    public void setCurrentTargetCard(int currentTargetCard) {
        this.currentTargetCard = currentTargetCard;
    }

    public int getCurrentTargetCard() {
        return currentTargetCard;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getWinNumber() {
        return winNumber;
    }
}
