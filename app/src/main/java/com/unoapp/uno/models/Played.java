package com.unoapp.uno.models;

/**
 * Model to hold cards played by players
 */
public class Played {
    private Card playedCard;

    /**
     * Adds the given card to played stack. Expected to be removed from wherever its
     * played
     * 
     * @param card to be played
     */
    public void playCard(Card card) {
        playedCard = card;
    }

    /**
     * Returns the last played card
     * 
     * @return latest played card
     */
    public Card getTop() {
        return playedCard;
    }

    /**
     * Validates a card to check if its playable or not
     * 
     * @param card card to be validated
     * @return true if last played card is same color or same number
     */
    public boolean validateCard(Card card) {
        return (playedCard.getColor().equals(card.getColor()) || playedCard.getNum() == card.getNum());
    }
}
