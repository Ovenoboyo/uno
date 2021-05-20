package com.unoapp.uno.utils;

/**
 * Utils class to hold all constants related stuff
 */
public class Constants {
    /**
     * Enum to hold all possible colors for cards
     */
    public enum Color {
        RED, BLUE, YELLOW, GREEN, BLACK;
    }

    public static final int SKIP = 10;
    public static final int REVERSE = 11;
    public static final int DRAW2 = 12;
    public static final int WILD = 13;
    public static final int DRAWFOUR = 14;

    /**
     * Global database connection (Mostly to avoid db connection overhead).
     */
    public static final Database dbConnection = new Database();

    /**
     * Enum to hold all possible screen values
     */
    public enum Screens {
        TITLE_SCREEN, PLAYER_SELECT, GAME, ACHIEVEMENT, RESULTS, EXIT
    }

    /**
     * Enum to hold all possible Achievement types
     */
    public enum AchievementTypes {
        PLAYED, DRAW4, WILD, DRAW2, SKIP, REVERSE, EXP
    }

}
