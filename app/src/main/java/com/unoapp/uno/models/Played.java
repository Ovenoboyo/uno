package com.unoapp.uno.models;

import java.util.ArrayList;

import com.unoapp.uno.utils.Constants.Color;

/**
 * Model to hold cards played by players
 */
public class Played {
    private ArrayList<Card> playedCard = new ArrayList<>();

    /**
     * Adds the given card to played stack. Expected to be removed from wherever its
     * played
     * 
     * @param card to be played
     */
    public void playCard(Card card) {
        playedCard.add(0, card);
        this.cleanStack();
    }

    /**
     * Remove excessive cards
     * Only keep last 3 played cards
     */
    private void cleanStack() {
        if (playedCard.size() > 3) {
            for (int i = 3; i < playedCard.size(); i++) {
                playedCard.remove(i);
            }
        }
    }

    /**
     * Get prevously played cards (Usually 3)
     * @return
     */
    public ArrayList<Card> getLastPlayedCards() {
        return playedCard;
    }

    /**
     * Returns the last played card
     * 
     * @return latest played card
     */
    public Card getTop() {
        return playedCard.get(0);
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
        return (card.isSpecial() || getTop().getColor().equals(Color.BLACK)
                || getTop().getColor().equals(card.getColor()) || getTop().getNum() == card.getNum());
    }
}
