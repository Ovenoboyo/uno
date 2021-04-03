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
    private IGameController mGameController;

    private Deck deck = new Deck();
    private Played playedCard = new Played();

    /**
     * Holds the index of player whose turn it is
     */
    private Integer turnIndex = -1;

    public GameController(IGameController mGameController) {
        this.mGameController = mGameController;
        // Add 2 players
        for (int i = 0; i < 2; i++)
            this.players.add(new Player());
        populatePlayerCards();

        // Play one card initially
        playedCard.playCard(deck.popDeck());
    }

    public void startGameLoop() {
        nextTurn();
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
     * Increments turnIndex if its lesser than total players else set it to point
     * first player
     */
    private void nextTurn() {
        turnIndex++;
        if (turnIndex == players.size())
            turnIndex = 0;

        // Notify of turn end event
        mGameController.turnEndCallback();
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
     * @param card card to be played
     * @return true if card is playable, false if not playable
     */
    public boolean playCard(Card card) {
        Player player = getCurrentPlayer();
        if (playedCard.validateCard(card)) {
            player.removeCard(card.getUID());
            playedCard.playCard(card);

            // Increment turn if card is played
            nextTurn();
            return true;
        }
        return false;
    }

    /**
     * Get the topmost card from playedcards stack
     * 
     * @return card played latest
     */
    public Card getLastPlayedCard() {
        return playedCard.getTop();
    }

    /**
     * Interface for all required event callbacks
     */
    public interface IGameController {
        /**
         * Fires on every turn end and at the start of t he game.
         */
        void turnEndCallback();
    }
}
