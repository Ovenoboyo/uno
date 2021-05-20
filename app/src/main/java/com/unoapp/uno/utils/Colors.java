package com.unoapp.uno.utils;

import com.unoapp.uno.utils.Constants.Color;

/**
 * Utils class to hold colors related stuff
 */
public class Colors {

    /**
     * Get a color from predefined java.awt.Color
     * @param color enums for predefined colors
     * @param disabled true if the card is disabled otherwise false
     * @return java.awt.Color
     */
    public static java.awt.Color getColor(Color color, boolean disabled) {
        if (disabled) {
            return java.awt.Color.GRAY;
        }

        switch (color) {
            case RED:
                return new java.awt.Color(206, 60, 61);
            case BLUE:
                return new java.awt.Color(3, 107, 210);
            case GREEN:
                return new java.awt.Color(108, 181, 96);
            case YELLOW:
                return new java.awt.Color(239, 211, 65);
            case BLACK:
                return new java.awt.Color(36, 39, 41);
        }
        return null;
    }
}
