package com.unoapp.uno.models;

import java.util.ArrayList;

public class Played {
    private ArrayList<Card> playedCards = new ArrayList<>();

    /**
     * Adds the given card to played stack. Expected to be removed from wherever its
     * played
     * 
     * @param card to be played
     */
    public void playCard(Card card) {
        playedCards.add(card);
    }

    /**
     * Returns the last played card
     * 
     * @return latest played card
     */
    private Card getTop() {
        return playedCards.get(0);
    }

    /**
     * Validates a card to check if its playable or not
     * 
     * @param card card to be validated
     * @return true if last played card is same color or same number
     */
    public boolean validateCard(Card card) {
        return (getTop().getColor().equals(card.getColor()) || getTop().getNum() == card.getNum());
    }
}
