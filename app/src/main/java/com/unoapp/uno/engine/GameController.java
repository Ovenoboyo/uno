package com.unoapp.uno.engine;

import java.util.ArrayList;

import com.unoapp.uno.models.Card;
import com.unoapp.uno.models.Deck;
import com.unoapp.uno.models.Played;
import com.unoapp.uno.models.Player;

/**
 * Controller to handle logic of game
 */
public class GameController {
    private ArrayList<Player> players = new ArrayList<>();

    private Deck deck = new Deck();
    private Played playedCard = new Played();

    public GameController() {
        // Add 2 players
        for (int i = 0; i < 2; i++)
            this.players.add(new Player());
        populatePlayerCards();
    }

    /**
     * Get all players in game
     * 
     * @return list of all players
     */
    public Player getPlayer(Integer index) {
        return this.players.get(index);
    }

    /**
     * Get total players count
     * 
     * @return Integer
     */
    public Integer getTotalPlayers() {
        return this.players.size();
    }

    /**
     * Get generated deck of cards
     * 
     * @return Deck of 108 cards
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Set initial hand of all players by picking 7 cards from deck
     */
    private void populatePlayerCards() {
        for (Player p : players) {
            p.setHand(deck.getInitialCards());
        }
    }

    /**
     * Holds the index of player whose turn it is
     */
    private Integer turnIndex = 0;

    /**
     * Increments turnIndex if its lesser than total players else set it to point
     * first player
     */
    public void nextTurn() {
        turnIndex++;
        if (turnIndex == players.size())
            turnIndex = 0;
    }

    /**
     * Get player whose turn it is
     * 
     * @return current player
     */
    public Player getCurrentPlayer() {
        return players.get(turnIndex);
    }

    /**
     * Play card identified by provided UID. Removes card from players hand and adds
     * to played cards
     * 
     * @param UID UID of card
     * @return true if card is playable, false if not playable
     */
    public boolean playCard(String UID) {
        Player player = getCurrentPlayer();
        Card card = player.getByUID(UID);
        if (playedCard.validateCard(card)) {
            player.removeCard(UID);
            playedCard.playCard(card);

            // Increment turn if card is played
            nextTurn();
            return true;
        }
        return false;
    }
}
