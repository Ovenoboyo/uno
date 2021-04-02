package com.unoapp.uno.models;

import java.util.UUID;

public class Card {
    public enum Color {
        RED("Red"), BLUE("Blue"), YELLOW("Yellow"), GREEN("Green");

        private final String valueString;

        Color(final String text) {
            this.valueString = text;
        }

        @Override
        public String toString() {
            return valueString;
        }
    }

    // TODO: Wild Cards

    public final Integer SKIP = 10;
    public final Integer REVERSE = 11;
    public final Integer DRAW2 = 12;

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

    public Card(Integer num, Color color) {
        this.UID = UUID.randomUUID().toString();
        this.num = num;
        this.color = color;
    }

    @Override
    public String toString() {
        return this.color.toString() + " " + this.num.toString();
    }
}
