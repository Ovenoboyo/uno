package com.unoapp.uno.engine;

import java.util.ArrayList;
import java.util.Collections;

import com.unoapp.uno.models.Card;
import com.unoapp.uno.models.Deck;
import com.unoapp.uno.models.Played;
import com.unoapp.uno.models.Player;
import com.unoapp.uno.utils.Constants;

/**
 * Controller to handle logic of game
 */
public class GameController {
    private ArrayList<Player> players = new ArrayList<>();
    private IGameController mGameController;

    private Deck deck = new Deck();
    private Played playedCard = new Played();

    private int isStackingD2 = 0;
    private int isStackingD4 = 0;

    /**
     * Holds the index of player whose turn it is
     */
    private Integer turnIndex = -1;

    public GameController(IGameController mGameController) {
        this.mGameController = mGameController;
        // Add 2 players
        for (int i = 0; i < 3; i++)
            this.players.add(new Player("Player " + i));
        populatePlayerCards();

        // Play one card initially
        playedCard.playCard(deck.popDeck());
    }

    /**
     * Start the game loop
     * 
     *
     */
    public void startGameLoop() {
        nextTurn(null, false);
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
     * Handle special functions exhibited by action cards NOTE: increment turn is
     * always called even after handling action cards.
     * 
     * @param card card which was played last @
     */
    private void handleActionCards(Card card) {
        switch (card.getNum()) {
        case Constants.SKIP:
            incrementTurn();
            break;

        case Constants.DRAW2:
            isStackingD2++;
            mGameController.drawTwoCallback();
            break;

        case Constants.REVERSE:
            Collections.reverse(players);
            turnIndex = players.size() - turnIndex - 1;
            break;
        }
    }

    private void handleSpecialCards(Card card) {
        switch (card.getNum()) {
        case Constants.DRAWFOUR:
            isStackingD4++;
            mGameController.drawFourCallback();
            break;
        case Constants.WILD:
            break;
        }
    }

    /**
     * Increments turn in circular order
     */
    private void incrementTurn() {
        turnIndex++;
        if (turnIndex == players.size())
            turnIndex = 0;
    }

    /**
     * Increments turnIndex if its lesser than total players else set it to point
     * first player. Also checks for winner on every turn. If winner is found then
     * gotWinnerCallback will be fired otherwise turnEndCallback will be fired
     * 
     * lastPlayed here refers to latest card and not one previous of latest
     * 
     * @param player player who just finished playing the card
     * @param isPass true if the current player has played a card. false if no card
     *               was played by current player @
     */
    private void nextTurn(Player player, Boolean isPass) {
        // If the player is null, then it indicates first turn where turnIndex is -1
        if (player == null || !checkWinner(player)) {
            Card lastPlayed = playedCard.getTop();
            if (!isPass) {
                if (lastPlayed.isAction()) {
                    handleActionCards(lastPlayed);
                } else if (lastPlayed.isSpecial()) {
                    handleSpecialCards(lastPlayed);
                }
            }
            incrementTurn();

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
        if (turnIndex < players.size() && turnIndex > 0) {
            return players.get(turnIndex);
        }
        return players.get(0);
    }

    /**
     * Play card identified by provided UID. Removes card from players hand and adds
     * to played cards
     * 
     * @param card card to be played
     * @return true if card is playable, false if not playable @
     */
    public boolean playCard(Card card) {
        Player player = getCurrentPlayer();
        if (playedCard.validateCard(card)) {

            player.removeCard(card.getUID());
            playedCard.playCard(card);

            // Increment turn if card is played
            nextTurn(player, false);
            return true;
        }
        return false;
    }

    public void drawTwo(Player player) {
        Card cards[] = new Card[2 * isStackingD2];
        for (int i = 0; i < 2 * isStackingD2; i++) {
            Card card = deck.popDeck();
            cards[i] = card;
            player.addCard(card);
        }
        isStackingD2 = 0;
        mGameController.drawingTwoCallback(cards, () -> nextTurn(player, true));
    }

    public void drawFour(Player player) {
        Card cards[] = new Card[4 * isStackingD4];

        for (int i = 0; i < 4 * isStackingD4; i++) {
            Card card = deck.popDeck();
            cards[i] = card;
            player.addCard(card);
        }
        isStackingD4 = 0;
        mGameController.drawingFourCallback(cards, () -> nextTurn(player, true));
    }

    /**
     * Pops a card from the deck and adds to to players hand
     * 
     * @param player player to which card is to be given
     */
    public void drawCard(Player player) {
        Card card = deck.popDeck();
        player.addCard(card);
        mGameController.drawCardCallback(card, playedCard.validateCard(card));
    }

    public void pass(Player player) {
        nextTurn(player, true);
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
     * Checks if the player has 0 cards left or not
     * 
     * @param player player to be checked for winner
     * @return true if winner else false
     */
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
         * 
         * @param card       card drawn from deck
         * @param isPlayable true if card is playable else false
         */
        void drawCardCallback(Card card, boolean isPlayable);

        void drawTwoCallback();

        void drawFourCallback();

        public interface continueDraw {
            void continueTurn();
        }

        void drawingTwoCallback(Card[] cards, continueDraw cDraw);

        void drawingFourCallback(Card[] cards, continueDraw cDraw);

        /**
         * Fires when a winner is found
         * 
         * @param player object of the winner
         */
        void gotWinnerCallback(Player player);
    }
}
