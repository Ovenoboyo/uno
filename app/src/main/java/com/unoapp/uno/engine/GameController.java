package com.unoapp.uno.engine;

import java.util.ArrayList;

import com.unoapp.uno.models.Card;
import com.unoapp.uno.models.Deck;
import com.unoapp.uno.models.Played;
import com.unoapp.uno.models.Player;
import com.unoapp.uno.utils.Constants;

/**
 * Controller to handle logic of game
 */
public class GameController {
    private ArrayList<Player> players;
    private IGameController mGameController;

    private Deck deck = new Deck();
    private Played playedCard = new Played();

    private int isStackingD2 = 0;
    private int isStackingD4 = 0;

    private boolean isReversed = false;

    /**
     * Holds the index of player whose turn it is
     */
    private Integer turnIndex = -1;

    /**
     * Constructor for GameController
     * @param mGameController Interface for various events emmited by controller 
     */
    public GameController(ArrayList<Player> players, IGameController mGameController) {
        this.mGameController = mGameController;
        this.players = players;

        populatePlayerCards();

        // Play one card initially
        playedCard.playCard(deck.popDeck());
    }

    /**
     * Start the game loop
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
    public ArrayList<Player> getPlayers() {
        return this.players;
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
     * Returns if player order is reversed or not
     * @return true if player order is reversed otherwise false
     */
    public boolean isReversed() {
        return this.isReversed;
    }

    /**
     * Handle special functions exhibited by action cards NOTE: increment turn is
     * always called even after handling action cards.
     * 
     * @param card card which was played last @
     */
    private void handleActionCards(Player player, Card card) {
        switch (card.getNum()) {
            case Constants.SKIP:
                player.getAnalytics().incSkip();
                incrementTurn();
                break;

            case Constants.DRAW2:
                player.getAnalytics().incDraw2();
                isStackingD2++;
                mGameController.drawTwoCallback();
                break;

            case Constants.REVERSE:
                player.getAnalytics().incReverse();
                this.isReversed = !this.isReversed;
                break;
        }
    }

    /**
     * Emits respective events if the card is special
     * The color is already selected into the card before this method is called
     * 
     * @param card card to be handled
     */
    private void handleSpecialCards(Player player, Card card) {
        switch (card.getNum()) {
            case Constants.DRAWFOUR:
                player.getAnalytics().incDraw4();
                isStackingD4++;
                mGameController.drawFourCallback();
                break;
            case Constants.WILD:
                player.getAnalytics().incWild();
                break;
        }
    }

    /**
     * Increments turn in circular order
     */
    private void incrementTurn() {
        // Set first turn
        if (turnIndex == -1) {
            turnIndex = 0;
        }

        turnIndex = (isReversed) ? turnIndex - 1 : turnIndex + 1;
        if (turnIndex == players.size())
            turnIndex = 0;
        else if (turnIndex == -1) {
            turnIndex = players.size() - 1;
        }
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
        if (player == null) {
            player = players.get(0);
        }
        if (checkInvalidUno(player)) {
            Card lastPlayed = playedCard.getTop();
            if (!isPass) {
                if (lastPlayed.isAction()) {
                    handleActionCards(player, lastPlayed);
                } else if (lastPlayed.isSpecial()) {
                    handleSpecialCards(player, lastPlayed);
                }
            }
            incrementTurn();

            // Notify of turn end event
            mGameController.turnEndCallback();
            return;
        }
        // Notify of the winner
        mGameController.gotWinnerCallback(player, this.players);
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

    public void simulateWinner() {
        mGameController.gotWinnerCallback(this.players.get(0), this.players);
    }

    /**
     * Draw 2 cards from deck and add them to player
     * @param player player that is affected by draw 2
     */
    public void drawTwo(Player player) {
        Card cards[] = new Card[2 * isStackingD2];
        for (int i = 0; i < 2 * isStackingD2; i++) {
            Card card = deck.popDeck();
            cards[i] = card;
            player.addCard(card);
        }
        isStackingD2 = 0;
        mGameController.drawingCallback(cards, () -> nextTurn(player, true));
    }

    public void drawUnoFault(Player player) {
        Card cards[] = new Card[2];
        for (int i = 0; i < 2; i++) {
            Card card = deck.popDeck();
            cards[i] = card;
            player.addCard(card);
        }
        mGameController.drawingCallback(cards, null);
    }

    /**
     * Draw 4 cards from deck and add them to player
     * @param player player that is affected by draw 4
     */
    public void drawFour(Player player) {
        Card cards[] = new Card[4 * isStackingD4];

        for (int i = 0; i < 4 * isStackingD4; i++) {
            Card card = deck.popDeck();
            cards[i] = card;
            player.addCard(card);
        }
        isStackingD4 = 0;
        mGameController.drawingCallback(cards, () -> nextTurn(player, true));
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

    /**
     * Pass turn without playing any card
     * @param player player who is passing the turn
     */
    public void pass(Player player) {
        nextTurn(player, true);
    }

    /**
     * Get the topmost card from playedcards stack
     * 
     * @return card played latest
     */
    public ArrayList<Card> getLastPlayedCard() {
        return playedCard.getLastPlayedCards();
    }

    private Boolean checkInvalidUno(Player player) {
        if (player.getHand().size() == 0) {
            if (player.isUno()) {
                player.setUno(true);
                return false;
            } else {
                drawUnoFault(player);
            }
        }
        return true;
    }

    public Boolean playerCanPlay() {
        for (Card c : getCurrentPlayer().getHand()) {
            if (playedCard.validateCard(c)) {
                return true;
            }
        }
        return false;
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

        /**
         * Fired when a draw 2 card is played
         */
        void drawTwoCallback();

        /**
        * Fired when a draw 4 card is played
        */
        void drawFourCallback();

        /**
         * Interface to pass a method that continues the turn
         */
        public interface continueDraw {
            void continueTurn();
        }

        /**
         * Called when current player is drawing 2 cards
         * @param cards cards that are being drawn from deck
         * @param cDraw callback to continue the turn (usually skips it)
         */
        void drawingCallback(Card[] cards, continueDraw cDraw);

        /**
         * Fires when a winner is found
         * 
         * @param player object of the winner
         */
        void gotWinnerCallback(Player player, ArrayList<Player> players);
    }
}
