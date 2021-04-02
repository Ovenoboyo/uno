package com.unoapp.uno.models;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand = new ArrayList<>();

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void removeCard(String UID) {
        hand.remove(getByUID(UID));
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    private Card getByUID(String UID) {
        return hand.stream().filter(card -> UID.equals(card.getUID())).findFirst().orElse(null);
    }
}
