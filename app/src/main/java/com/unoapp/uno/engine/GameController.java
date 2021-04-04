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
        nextTurn(null);
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
     * Set initial hand of all players by picking 7 cards from deck
     */
    private void populatePlayerCards() {
        for (Player p : players) {
            p.setHand(deck.getInitialCards());
        }
    }

    /**
     * Increments turnIndex if its lesser than total players else set it to point
     * first player. Also checks for winner on every turn. If winner is found then
     * gotWinnerCallback will be fired otherwise turnEndCallback will be fired
     * 
     */
    private void nextTurn(Player player) {
        // If the player is null, then it indicates first turn where turnIndex is -1
        if (player == null || !checkWinner(player)) {
            turnIndex++;
            if (turnIndex == players.size())
                turnIndex = 0;

            // Notify of turn end event
            mGameController.turnEndCallback();
            return;
        }
        // Notify of the winner
        mGameController.gotWinnerCallback(player);
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
            nextTurn(player);
            return true;
        }
        return false;
    }

    /**
     * Pops a card from the deck and adds to to players hand
     * 
     * @param player player to which card is to be given
     */
    public void drawCard(Player player) {
        player.addCard(deck.popDeck());
        mGameController.drawCardCallback();
        nextTurn(player);
    }

    /**
     * Get the topmost card from playedcards stack
     * 
     * @return card played latest
     */
    public Card getLastPlayedCard() {
        return playedCard.getTop();
    }

    public boolean checkWinner(Player player) {
        return player.getHand().size() == 0;
    }

    /**
     * Interface for all required event callbacks
     */
    public interface IGameController {
        /**
         * Fires on every turn end and at the start of t he game.
         */
        void turnEndCallback();

        /**
         * Fires everytime a card is drawn from the deck
         */
        void drawCardCallback();

        void gotWinnerCallback(Player player);
    }
}
