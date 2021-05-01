package com.unoapp.uno.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.Color;

/**
 * Model to hold a deck of 108 cards as per UNO rules
 */
public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        this.populateDeck();
        this.shuffleDeck();
    }

    /**
     * Generate deck with 108 cards
     */
    private void populateDeck() {

        // Populate number and action cards
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 13; i++) {
                for (Constants.Color c : Constants.Color.values())
                    if (c != Color.BLACK)
                        deck.add(new Card(i, c));

            }

            // Generate special cards (4 of each)
            for (int i = Constants.WILD; i <= Constants.DRAWFOUR; i++) {
                for (int k = 0; k < 4; k++) {
                    deck.add(new Card(i, Color.BLACK));
                }
            }
        }
    }

    /**
     * Shuffle the generated deck
     */
    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    /**
     * Returns size of deck
     * @return size of deck as int
     */
    public int getSize() {
        return deck.size();
    }

    /**
     * Remove a card from top of deck
     * 
     * @return removed card
     */
    public Card popDeck() {
        if (deck.size() == 0) {
            this.populateDeck();
            this.shuffleDeck();
        }

        Card c = deck.get(0);
        deck.remove(0);
        return c;
    }

    /**
     * Add all cards from list to deck and shuffle
     * @param list list of cards that is to be added to the deck
     */
    public void addAll(ArrayList<Card> list) {
        deck.addAll(list);
        this.shuffleDeck();
    }

    /**
     * Used to allocate 7 cards to players at start of game. Gets 7 cards from top
     * of deck and removes them from the deck before returning
     * 
     * @return 7 cards from top of deck
     */
    public List<Card> getInitialCards() {
        // Make copy of objects before clearing them from deck
        // https://stackoverflow.com/a/6536128 Shallow copy
        // https://stackoverflow.com/a/26698335 Remove range
        List<Card> l1 = deck.subList(0, 7), l2 = new ArrayList<>(l1);
        l1.clear();
        return l2;
    }
}
