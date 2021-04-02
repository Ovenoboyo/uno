package com.unoapp.uno.models;

import java.util.ArrayList;

public class Table {
    private ArrayList<Player> players = new ArrayList<>();

    private Deck deck = new Deck();

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public Table() {
        // Add 2 players
        for (int i = 0; i < 2; i++)
            this.players.add(new Player());
    }
}
