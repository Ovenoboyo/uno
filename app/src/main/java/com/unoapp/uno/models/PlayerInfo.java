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

    /**
     * @return draw 2 played
     */
    public Integer getDraw2() {
        return this.draw2;
    }

    /**
     * @return draw 4 played
     */
    public Integer getDraw4() {
        return this.draw4;
    }

    /**
     * @return skips played
     */
    public Integer getSkip() {
        return this.skip;
    }

    /**
     * @return reverse played
     */
    public Integer getReverse() {
        return this.reverse;
    }

    /**
     * @return wilds played
     */
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGamesWon(Integer gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void setGamesLost(Integer gamesLost) {
        this.gamesLost = gamesLost;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setDraw2(Integer draw2) {
        this.draw2 = draw2;
    }

    public void setDraw4(Integer draw4) {
        this.draw4 = draw4;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public void setReverse(Integer reverse) {
        this.reverse = reverse;
    }

    public void setWild(Integer wild) {
        this.wild = wild;
    }
}
