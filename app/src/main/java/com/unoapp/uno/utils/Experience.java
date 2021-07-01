package com.unoapp.uno.utils;

import com.unoapp.uno.models.StatAnalytics;

/**
 * Utils class to hold all experience related stuff
 */
public class Experience {
    private static Double XPSCALE = 2d;

    /**
     * Calculate current level from given xp
     * 
     * level = (x/500) ^ 1/2
     * 
     * @param xp of player to calculate level of
     * @return level as int
     */
    public static int getLevel(int xp) {
        Double level = Math.pow(((double) xp / 500), (1 / XPSCALE));
        return level.intValue();

        // return (int) (Math.floor(500 + Math.sqrt(2500 + 100 * xp)) / 100);
    }

    /**
     * Calcuate xp of player after round ends based on amount of cards played and winner
     * @param analytics analytics of player to calculate xp of
     * @param winner true if player is winner otherwise false
     * @return xp as Integer
     */
    public static Integer calculateXP(StatAnalytics analytics, Boolean winner) {
        int xp = 100 + (analytics.getDraw2() * 2 + 2 * analytics.getSkip() + 2 * analytics.getReverse()
                + analytics.getWild() * 3 + analytics.getDraw4() * 4) * 3;
        if (winner) {
            xp += 100;
        }
        return xp;
    }

    /**
     * Gets xp required to complete current level 
     * Alternatively starting xp of next level
     * 
     * @param level current level to get upper bound of
     * @return upper bound xp as int
     */
    public static int getUpperBoundXP(int level) {
        Double L = level + 1d;
        Double upperBound = Math.pow(L, XPSCALE) * 500;
        return upperBound.intValue();
    }
}
