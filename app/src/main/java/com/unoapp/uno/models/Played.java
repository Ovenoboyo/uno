package com.unoapp.uno.models;

import com.unoapp.uno.utils.Constants.Color;

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
     * Cases: 1) Played card is special (Wild / Draw 4)
     *        2) Last played card is of color BLACK
     *        3) Last played card matches color of current card
     *        4) Last played card matches number of current number
     * 
     * @param card card to be validated
     * @return true if last played card is same color or same number
     */
    public boolean validateCard(Card card) {
        return (card.isSpecial() || playedCard.getColor().equals(Color.BLACK)
                || playedCard.getColor().equals(card.getColor()) || playedCard.getNum() == card.getNum());
    }
}
