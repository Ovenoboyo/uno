package com.unoapp.uno.models;

import java.util.ArrayList;

public class Table {
    private ArrayList<Player> players = new ArrayList<>();

    private Deck deck = new Deck();

    /**
     * Get all players in game
     * 
     * @return list of all players
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Get generated deck of cards
     * 
     * @return Deck of 108 cards
     */
    public Deck getDeck() {
        return this.deck;
    }

    public Table() {
        // Add 2 players
        for (int i = 0; i < 2; i++)
            this.players.add(new Player());
        populatePlayerCards();
    }

    /**
     * Set initial hand of all players by picking 7 cards from deck
     */
    private void populatePlayerCards() {
        for (Player p : players) {
            p.setHand(deck.getInitialCards());
        }
    }
}
