package com.unoapp.uno.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.unoapp.uno.models.Card.Color;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        this.populateDeck();
        this.shuffleDeck();
    }

    private void populateDeck() {

        // Populate number and action cards
        for (int i = 0; i < 13; i++) {
            for (Color c : Color.values())
                deck.add(new Card(i, c));
        }

        // TODO: Populate Wild Cards
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public Card popDeck() {
        Card c = deck.get(0);
        deck.remove(0);
        return c;
    }

    public List<Card> getInitialCards() {
        // Make copy of objects before clearing them from deck
        // https://stackoverflow.com/a/6536128 Shallow copy
        // https://stackoverflow.com/a/26698335 Remove range
        List<Card> l1 = deck.subList(0, 7), l2 = new ArrayList<>(l1);
        l1.clear();
        return l2;
    }
}
