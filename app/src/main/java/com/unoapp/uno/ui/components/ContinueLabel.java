package com.unoapp.uno.ui.components;

import java.awt.Dimension;

import javax.swing.JLabel;

import com.unoapp.uno.ui.drawables.ContinueButton;

/**
 * Label to hold continue icon
 */
public class ContinueLabel extends JLabel {
    public ContinueLabel() {
        ContinueButton start = new ContinueButton(177, 75);
        setIcon(start);
        setPreferredSize(new Dimension(start.getIconWidth(), start.getIconHeight()));
    }
}
