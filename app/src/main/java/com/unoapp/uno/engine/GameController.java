package com.unoapp.uno.engine;

import java.util.ArrayList;

import com.unoapp.uno.models.Card;
import com.unoapp.uno.models.Table;

public class GameController {
    private Table table = new Table();

    /**
     * Holds the index of player whose turn it is
     */
    private Integer turnIndex = 0;

    /**
     * 
     * @return list of cards of player whose turn it is now
     */
    public ArrayList<Card> getCurrentPlayerCards() {
        return table.getPlayers().get(turnIndex).getHand();
    }

    /**
     * Increments turnIndex if its lesser than total players else set it to point
     * first player
     */
    public void nextTurn() {
        turnIndex++;
        if (turnIndex == table.getPlayers().size())
            turnIndex = 0;
    }
}
