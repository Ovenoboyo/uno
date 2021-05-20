package com.unoapp.uno.utils;

import java.awt.Font;

/**
 * Utils for font related stuff.
 * 
 * Honestly i dont even know how fonts are supposed to be distributed
 */
public class Fonts {
    private static Font ProximaNovaBold = new Font("Proxima Nova Bold", Font.PLAIN, 36);
    private static Font ProximaNovaRegular = new Font("Proxima Nova Regular", Font.PLAIN, 36);
    private static Font GilmerHeavy = new Font("Gilmer Heavy", Font.PLAIN, 36);

    /**
     * Get instance of Proxima Nova with custom font size and bold
     * @param fontSize font size of font
     * @param bold true if font is bold otherwise false
     * @return new derived instance of Proxima Nova
     */
    public static Font getProximaInstance(float fontSize, Boolean bold) {
        if (bold)
            return ProximaNovaBold.deriveFont(fontSize);
        else
            return ProximaNovaRegular.deriveFont(fontSize);
    }

    /**
     * Get instance of Gilmer Heavy with custom font size
     * @param fontSize font size of font
     * @return new derived instance of Gilmer Heavy
     */
    public static Font getGilmerInstance(float fontSize) {
        return GilmerHeavy.deriveFont(fontSize);
    }

}
