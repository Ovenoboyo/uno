package com.unoapp.uno.models;

/**
 * HAHA ANAL-YTIC
 */
public class StatAnalytics {
    private Integer draw2Played;
    private Integer draw4Played;
    private Integer skipPlayed;
    private Integer reversePlayed;
    private Integer wildPlayed;

    public StatAnalytics(Integer draw2Played, Integer draw4Played, Integer skipPlayed, Integer reversePlayed,
            Integer wildPlayed) {
        this.draw2Played = draw2Played;
        this.draw4Played = draw4Played;
        this.skipPlayed = skipPlayed;
        this.reversePlayed = reversePlayed;
        this.wildPlayed = wildPlayed;

    }

    public void incDraw2() {
        draw2Played += 1;
    }

    public void incDraw4() {
        draw4Played += 1;
    }

    public void incSkip() {
        skipPlayed += 1;
    }

    public void incReverse() {
        reversePlayed += 1;
    }

    public void incWild() {
        wildPlayed += 1;
    }

    public Integer getDraw2Played() {
        return this.draw2Played;
    }

    public Integer getDraw4Played() {
        return this.draw4Played;
    }

    public Integer getSkipPlayed() {
        return this.skipPlayed;
    }

    public Integer getReversePlayed() {
        return this.reversePlayed;
    }

    public Integer getWildPlayed() {
        return this.wildPlayed;
    }
}
