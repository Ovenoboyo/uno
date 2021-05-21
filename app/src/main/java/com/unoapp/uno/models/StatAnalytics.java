package com.unoapp.uno.models;

public class StatAnalytics {
    private Integer draw2Played = 0;
    private Integer draw4Played = 0;
    private Integer skipPlayed = 0;
    private Integer reversePlayed = 0;
    private Integer wildPlayed = 0;

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

    public Integer getDraw2() {
        return this.draw2Played;
    }

    public Integer getDraw4() {
        return this.draw4Played;
    }

    public Integer getSkip() {
        return this.skipPlayed;
    }

    public Integer getReverse() {
        return this.reversePlayed;
    }

    public Integer getWild() {
        return this.wildPlayed;
    }
}
