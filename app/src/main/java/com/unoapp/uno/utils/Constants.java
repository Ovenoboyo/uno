package com.unoapp.uno.utils;

import java.awt.Font;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static final Database dbConnection = new Database();

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

    public static int ASSET_NOT_FOUND_ERR = -2;

    public static Path getAssetsDirectory() {
        Path assets = Paths.get("assets");
        if (!Files.exists(assets)) {
            assets = Paths.get("runtime", "bin", "assets");
        }
        return assets;
    }

    public static String getAsset(String assetName) {
        Path file = Paths.get(getAssetsDirectory().toString(), assetName);
        if (!Files.exists(file)) {
            System.out.println("Asset " + assetName + " not found");
            System.exit(ASSET_NOT_FOUND_ERR);
        }
        return file.toString();
    }

    private static Font ProximaNovaBold = new Font("Proxima Nova Bold", Font.PLAIN, 36);
    private static Font GilmerHeavy = new Font("Gilmer Heavy", Font.PLAIN, 36);

    public static Font getProximaInstance(float fontSize) {
        return ProximaNovaBold.deriveFont(fontSize);
    }

    public static Font getGilmerInstance(float fontSize) {
        return GilmerHeavy.deriveFont(fontSize);
    }

    public enum Screens {
        TITLE_SCREEN, PLAYER_SELECT, GAME, ACHIEVEMENT, EXIT
    }

}
