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
}
