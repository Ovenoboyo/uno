package com.unoapp.uno.ui.components;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Label to hold icon with scaled version of provided image
 */
public class ScaledBackground extends JLabel {
    public ScaledBackground(String src, int width, int height, LayoutManager mgr) {
        ImageIcon icon = new ImageIcon(src);
        icon.setImage(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        setIcon(icon);
        setLayout(mgr);
    }

    /**
     * 
     * @param src Src of image
     * @param width witdth of scaled image
     * @param height height of scaled image
     */
    public ScaledBackground(String src, int width, int height) {
        this(src, width, height, new FlowLayout());
    }
}
