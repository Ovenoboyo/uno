package com.unoapp.uno.ui.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.unoapp.uno.Application;
import com.unoapp.uno.Application.ScreenObject;
import com.unoapp.uno.abstracts.MouseClickListener;
import com.unoapp.uno.ui.components.BackButton;
import com.unoapp.uno.ui.components.GenericMenuScreen;
import com.unoapp.uno.ui.components.RoundedRectangle;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.SmoothText;
import com.unoapp.uno.ui.components.TransparentPanel;
import com.unoapp.uno.ui.components.TriangleArrow;
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.Screens;

public class Achievement extends GenericMenuScreen {
    public Achievement() {
        super();
        init();
        rectangle();
        selection();
    }

    private void init() {
        ScaledBackground background = new ScaledBackground(Constants.getAsset("mainBg.png"), xSize, ySize,
                new BorderLayout());
        getContentPane().add(background);

        final GenericMenuScreen context = this;

        TransparentPanel Panel = new TransparentPanel();
        Panel.setLayout(new BoxLayout(Panel, BoxLayout.PAGE_AXIS));

        Panel.add(selection());
        Panel.add(Box.createVerticalGlue());
        Panel.add(Box.createVerticalGlue());
        background.add(Panel, BorderLayout.CENTER);
        Panel.add(Box.createVerticalGlue());
        Panel.add(Box.createVerticalGlue());
        Panel.add(Box.createVerticalGlue());

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
        background.add(northPanel, BorderLayout.NORTH);

        TransparentPanel jPanel3 = new TransparentPanel();
        jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.PAGE_AXIS));
        // jPanel3.add(Box.createVerticalGlue());
        // jPanel3.add(Box.createVerticalGlue());
        jPanel3.add(Box.createVerticalGlue());
        jPanel3.add(rectangle());
        // jPanel3.add(jpanel2);
        jPanel3.add(Box.createVerticalGlue());
        jPanel3.add(Box.createVerticalGlue());
        jPanel3.add(Box.createVerticalGlue());
        // jPanel3.add(Box.createVerticalGlue());
        // jPanel3.add(Box.createVerticalGlue());
        background.add(jPanel3, BorderLayout.SOUTH);
        // rectangle();

    }

    private TransparentPanel rectangle(){
        RoundedRectangle rect = new RoundedRectangle(MAX_COMPONENT_X - 500, 109, 30, new FlowLayout(), new Color(97, 215, 67));
        rect.setLayout(new BoxLayout(rect, BoxLayout.PAGE_AXIS));
        TransparentPanel align = new TransparentPanel(new BorderLayout());
        align.setPreferredSize(new Dimension(MAX_COMPONENT_X - 500, 32));
        SmoothText text = new SmoothText("Test_1");
        TransparentPanel jpanel = new TransparentPanel();
        align.add(Box.createVerticalGlue());
        align.add(Box.createVerticalGlue());
        align.add(text, BorderLayout.WEST);
        rect.add(align);
        align.add(Box.createVerticalGlue());
        align.add(Box.createVerticalGlue());
        jpanel.add(rect);
        
        return jpanel;
    }

    private TransparentPanel selection(){
        RoundedRectangle rect_player = new RoundedRectangle(MAX_COMPONENT_X - 900, 79, 30, new FlowLayout());
        rect_player.setLayout(new BoxLayout(rect_player, BoxLayout.PAGE_AXIS));
        TransparentPanel align_player = new TransparentPanel(new BorderLayout());
        align_player.setPreferredSize(new Dimension(MAX_COMPONENT_X - 900, 32));
        SmoothText text_1 = new SmoothText("My name is Chaman:");
        SmoothText text_2 = new SmoothText("Player 1");
        align_player.add(Box.createVerticalGlue());
        align_player.add(Box.createVerticalGlue());
        rect_player.add(align_player);
        align_player.add(text_1, BorderLayout.WEST);
        // align_player.add(TriangleArrow(true));
        align_player.add(text_2, BorderLayout.WEST);
        // align_player(add.TriangleArrow(false));
        TransparentPanel playerPanel = new TransparentPanel();
        align_player.add(Box.createVerticalGlue());
        align_player.add(Box.createVerticalGlue());
        playerPanel.add(rect_player);

        return playerPanel;
    }
}