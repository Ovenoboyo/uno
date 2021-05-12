package com.unoapp.uno.ui.screens;

import java.awt.BorderLayout;
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

/**
 * Title screen 
 * Opens on start of program
 */
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

        buttonPanel.add(createPanel("Play", Constants.getAsset("playIcon.png"), 178, 124));
        buttonPanel.add(Box.createHorizontalStrut(80));
        buttonPanel.add(createPanel("Settings", Constants.getAsset("gear.png"), 123, 123));
        buttonPanel.add(Box.createHorizontalStrut(80));
        buttonPanel.add(createPanel("Quit", Constants.getAsset("power.png"), 144, 144));

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        background.add(mainPanel);

        pack();
    }

    /**
     * Creates panel with button and text for respective operation
     * @param labelText label of button
     * @param iconSrc src of icon
     * @param iconWidth width of icon
     * @param iconHeight height of icon
     * @return panel with label and icon
     */
    private TransparentPanel createPanel(String labelText, String iconSrc, int iconWidth, int iconHeight) {
        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        TransparentPanel textPanel = new TransparentPanel();
        SmoothText text = new SmoothText(labelText, Constants.getColor(Constants.Color.RED, false),
                Constants.ProximaNovaBold.deriveFont(26f));
        textPanel.add(text);
        textPanel.add(Box.createVerticalStrut(100));

        RoundedRectangle playButton = new RoundedRectangle(280, 280, 80, new BorderLayout());
        // playButton.setLayout(new BoxLayout(playButton, BoxLayout.PAGE_AXIS));
        TransparentPanel label = new TransparentPanel();
        label.add(new ScaledBackground(iconSrc, iconWidth, iconHeight, new FlowLayout()));
        // playButton.add(Box.createVerticalGlue());
        playButton.add(Box.createVerticalStrut(40), BorderLayout.NORTH);
        playButton.add(label, BorderLayout.CENTER);
        playButton.add(textPanel, BorderLayout.SOUTH);

        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(playButton);

        return mainPanel;
    }
}