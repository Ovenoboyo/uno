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
    private Integer angle = 0;

    public void setChangedColor(Color changedColor) {
        if (isSpecial())
            this.color = changedColor;
    }

    public Integer getAngle() {
        return this.angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

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

    /**
     * Check if card is action card (Draw 2, skip, reverse)
     * @return true if action otherwise false
     */
    public boolean isAction() {
        return num == Constants.SKIP || num == Constants.DRAW2 || num == Constants.REVERSE;
    }

    /**
     * Check if card is special (Draw 4 or wild)
     * @return true if special otherwise false
     */
    public boolean isSpecial() {
        return num == Constants.DRAWFOUR || num == Constants.WILD;
    }

    public Card(Integer num, Color color) {
        this.UID = UUID.randomUUID().toString();
        this.num = num;
        this.color = color;
    }
}
