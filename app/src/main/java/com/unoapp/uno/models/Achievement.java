package com.unoapp.uno.models;

import com.unoapp.uno.utils.Constants.AchievementTypes;

public class Achievement {
    private AchievementTypes type;
    private String title;
    private Float progress;
    private Float total;

    public Achievement(AchievementTypes type, String title, Float progress, Float total) {
        this.type = type;
        this.title = title;
        this.progress = progress;
        this.total = total;
    }

    public AchievementTypes getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public Float getProgress() {
        return this.progress;
    }

    public Float getTotal() {
        return this.total;
    }

    public void setProgress(Float progress) {
        this.progress = progress;
    }
}
