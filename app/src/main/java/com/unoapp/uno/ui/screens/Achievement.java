package com.unoapp.uno.ui.screens;

import com.unoapp.uno.ui.components.GenericMenuScreen;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.utils.Constants;

import java.awt.BorderLayout;

public class Achievement extends GenericMenuScreen {
    public Achievement() {
        super();
        init();
    }

    private void init() {
        ScaledBackground background = new ScaledBackground(Constants.getAsset("mainBg.png"), xSize, ySize,
                new BorderLayout());
        getContentPane().add(background);
    }
}
