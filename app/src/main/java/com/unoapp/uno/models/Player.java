package com.unoapp.uno.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Model to hold player data
 */
public class Player {
    private ArrayList<Card> hand = new ArrayList<>();
    private String name;
    private String id;
    private Boolean isUno = false;
    private StatAnalytics analytics;

    public static Player parseFromPlayerInfo(PlayerInfo info) {
        return new Player(info.getName(), info.getId(), new StatAnalytics());
    }

    public StatAnalytics getAnalytics() {
        return this.analytics;
    }

    public Boolean isUno() {
        return this.isUno;
    }

    public void setUno(Boolean isUno) {
        this.isUno = isUno;
    }

    public String getId() {
        return this.id;
    }

    /**
     * Get name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Constructor for player
     * @param name name of player
     */
    public Player(String name, String id, StatAnalytics analytics) {
        this.name = name;
        this.id = id;
        this.analytics = analytics;
    }

    /**
     * Get cards in players hand
     * 
     * @return list of cards
     */
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    /**
     * Initial setter for player cards
     * 
     * @param hand list of cards to set onto the player
     */
    public void setHand(List<Card> hand) {
        this.hand.clear();
        this.hand.addAll(hand);
    }

    /**
     * Removes card from player
     * 
     * @param UID to identify the card
     */
    public void removeCard(String UID) {
        hand.remove(getByUID(UID));
    }

    /**
     * Usually called after removing a card from deck
     * 
     * @param card to be added in players hand
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Search card by UID
     * 
     * @param UID of card to be found
     * @return found card
     */
    public Card getByUID(String UID) {
        return hand.stream().filter(card -> UID.equals(card.getUID())).findFirst().orElse(null);
    }
}
