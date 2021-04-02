package com.unoapp.uno.models;

import java.util.UUID;

public class Card {
    public enum Color {
        RED, BLUE, YELLOW, GREEN
    }

    // TODO: Wild Cards

    public final Integer SKIP = 10;
    public final Integer REVERSE = 11;
    public final Integer DRAW2 = 12;

    private String UID;
    private Integer num;
    private Color color;

    public String getUID() {
        return this.UID;
    }

    public int getNum() {
        return this.num;
    }

    public Color getColor() {
        return this.color;
    }

    public Card(Integer num, Color color) {
        this.UID = UUID.randomUUID().toString();
        this.num = num;
        this.color = color;
    }
}
