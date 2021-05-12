package com.unoapp.uno.ui.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;

import com.unoapp.uno.ui.components.GenericMenuScreen;
import com.unoapp.uno.ui.components.RoundedRectangle;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.SmoothText;
import com.unoapp.uno.ui.components.TransparentPanel;
import com.unoapp.uno.utils.Constants;

public class TitleScreen extends GenericMenuScreen {

    public TitleScreen() {
        init();
    }

    private void init() {
        ScaledBackground background = new ScaledBackground(Constants.getAsset("mainBg.png"), xSize, ySize);
        getContentPane().add(background);

        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(new Dimension(MAX_COMPONENT_X, MAX_COMPONENT_Y));

        TransparentPanel buttonPanel = new TransparentPanel();

        buttonPanel.add(createButton("Play", Constants.getAsset("playIcon.png"), 178, 124));
        buttonPanel.add(Box.createHorizontalStrut(80));
        buttonPanel.add(createButton("Settings", Constants.getAsset("gear.png"), 123, 123));
        buttonPanel.add(Box.createHorizontalStrut(80));
        buttonPanel.add(createButton("Quit", Constants.getAsset("power.png"), 144, 144));

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        background.add(mainPanel);

        pack();
    }

    private TransparentPanel createButton(String labelText, String iconSrc, int iconWidth, int iconHeight) {
        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        TransparentPanel textPanel = new TransparentPanel();
        SmoothText text = new SmoothText(labelText, Color.WHITE, Constants.ProximaNovaBold);
        textPanel.add(text);

        RoundedRectangle playButton = new RoundedRectangle(200, 200, 80);
        playButton.setLayout(new BoxLayout(playButton, BoxLayout.PAGE_AXIS));
        TransparentPanel label = new TransparentPanel();
        label.add(new ScaledBackground(iconSrc, iconWidth, iconHeight, new FlowLayout()));
        playButton.add(Box.createVerticalGlue());
        playButton.add(Box.createHorizontalStrut(iconHeight / 2));
        playButton.add(label);
        playButton.add(Box.createVerticalGlue());

        mainPanel.add(textPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(playButton);

        return mainPanel;
    }
}