package com.unoapp.uno.models;

public class PlayerInfo {
    private String id;
    private String name;
    private Integer gamesWon;
    private Integer gamesLost;
    private Integer experience;

    public PlayerInfo(String id, String name, Integer gamesWon, Integer gamesLost, Integer experience) {
        this.id = id;
        this.name = name;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.experience = experience;
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
