package com.unoapp.uno.utils;

public class Constants {
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

    public static final int SKIP = 10;
    public static final int REVERSE = 11;
    public static final int DRAW2 = 12;
}
