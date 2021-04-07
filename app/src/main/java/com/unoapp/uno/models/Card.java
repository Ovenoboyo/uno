package com.unoapp.uno.models;

import java.util.UUID;

import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.Color;

/**
 * Model to hold data of a single Card
 */
public class Card {
    private String UID;
    private Integer num;
    private Color color;

    /**
     * Get UID of card
     * 
     * @return String UID
     */
    public String getUID() {
        return this.UID;
    }

    /**
     * Get number associated with card
     * 
     * @return Number
     */
    public int getNum() {
        return this.num;
    }

    /**
     * Get color associated with card
     * 
     * @return Color
     */
    public Color getColor() {
        return this.color;
    }

    public boolean isAction() {
        return num == Constants.SKIP || num == Constants.DRAW2 || num == Constants.REVERSE;
    }

    public Card(Integer num, Color color) {
        this.UID = UUID.randomUUID().toString();
        this.num = num;
        this.color = color;
    }

    private String getActionCardNaming() {
        switch (num) {
        case Constants.SKIP:
            return "Skip";
        case Constants.REVERSE:
            return "Reverse";
        case Constants.DRAW2:
            return "Draw Two";
        }
        return null;
    }

    @Override
    public String toString() {
        return this.color.toString() + " " + ((this.isAction()) ? getActionCardNaming() : this.num.toString());
    }
}
