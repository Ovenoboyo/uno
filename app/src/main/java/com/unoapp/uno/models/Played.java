package com.unoapp.uno.models;

import java.util.ArrayList;

/**
 * Model to hold cards played by players
 */
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
    public Card getTop() {
        return playedCards.get(playedCards.size() - 1);
    }

    /**
     * Validates a card to check if its playable or not
     * 
     * @param card card to be validated
     * @return true if last played card is same color or same number
     */
    public boolean validateCard(Card card) {
        Card topCard = getTop();
        return (topCard.getColor().equals(card.getColor()) || topCard.getNum() == card.getNum());
    }
}
