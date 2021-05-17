package com.unoapp.uno.models;

/**
 * Model to hold player info retrieved from database
 */
public class PlayerInfo {
    private String id;
    private String name;
    private Integer gamesWon;
    private Integer gamesLost;
    private Integer experience;
    private Integer draw2;
    private Integer draw4;
    private Integer skip;
    private Integer reverse;
    private Integer wild;

    public PlayerInfo(String id, String name, Integer gamesWon, Integer gamesLost, Integer experience, Integer draw2,
            Integer draw4, Integer skip, Integer reverse, Integer wild) {
        this.id = id;
        this.name = name;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.experience = experience;
        this.draw2 = draw2;
        this.draw4 = draw4;
        this.skip = skip;
        this.reverse = reverse;
        this.wild = wild;
    }

    public Integer getDraw2() {
        return this.draw2;
    }

    public Integer getDraw4() {
        return this.draw4;
    }

    public Integer getSkip() {
        return this.skip;
    }

    public Integer getReverse() {
        return this.reverse;
    }

    public Integer getWild() {
        return this.wild;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the gamesWon
     */
    public Integer getGamesWon() {
        return gamesWon;
    }

    /**
     * @return the gamesLost
     */
    public Integer getGamesLost() {
        return gamesLost;
    }

    /**
     * @return the experience
     */
    public Integer getExperience() {
        return experience;
    }
}
