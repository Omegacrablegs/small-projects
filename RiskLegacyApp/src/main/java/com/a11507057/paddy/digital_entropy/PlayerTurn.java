package com.a11507057.paddy.digital_entropy;

public interface PlayerTurn {

    int getTurn(Player [] Players);

    int getTarget(int playedCard, Player [] Players);

    int getGuess(int discardProbability);
}
