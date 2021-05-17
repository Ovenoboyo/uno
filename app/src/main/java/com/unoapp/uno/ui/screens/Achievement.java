package com.unoapp.uno.ui.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JPanel;

import com.unoapp.uno.Application;
import com.unoapp.uno.Application.ScreenObject;
import com.unoapp.uno.abstracts.MouseClickListener;
import com.unoapp.uno.ui.components.BackButton;
import com.unoapp.uno.ui.components.GenericMenuScreen;
import com.unoapp.uno.ui.components.RoundedRectangle;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.SmoothText;
import com.unoapp.uno.ui.components.TransparentPanel;
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.Screens;

public class Achievement extends GenericMenuScreen {
    public Achievement() {
        super();
        init();
    }

    private void init() {
        ScaledBackground background = new ScaledBackground(Constants.getAsset("mainBg.png"), xSize, ySize,
                new BorderLayout());
        getContentPane().add(background);

        final GenericMenuScreen context = this;

        TransparentPanel northPanel = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
        northPanel.add(Box.createRigidArea(new Dimension(30, 120)));
        BackButton backButton = new BackButton(0.8, Constants.getProximaInstance(36));
        backButton.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.changeScreen(new ScreenObject(context, Screens.TITLE_SCREEN));
            }
        });
        northPanel.add(backButton);

        RoundedRectangle rect = new RoundedRectangle(500, 120, 30, new FlowLayout(), new Color(97, 215, 67));
        SmoothText text = new SmoothText("Test_1");
        TransparentPanel jpanel = new TransparentPanel();
        jpanel.add(rect);
        rect.add(text);
        background.add(backButton, BorderLayout.NORTH);
        background.add(jpanel, BorderLayout.CENTER);

        RoundedRectangle rect2 = new RoundedRectangle(500, 120, 30, new FlowLayout());
        SmoothText text2 = new SmoothText("Test_2");
        TransparentPanel jPanel2 = new TransparentPanel();
        jpanel.add(rect2);
        rect2.add(text2);
        background.add(backButton, BorderLayout.NORTH);
        background.add(jPanel2, BorderLayout.CENTER);
    }
}