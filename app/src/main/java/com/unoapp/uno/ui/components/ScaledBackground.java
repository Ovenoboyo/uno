package com.unoapp.uno.ui.components;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Label to hold icon with scaled version of provided image
 */
public class ScaledBackground extends JLabel {
    /**
     * Label with scaled image icon
     * 
     * @param src source path of image
     * @param width width of icon
     * @param height height of icon
     * @param mgr layout manager of JLabel
     */
    public ScaledBackground(String src, int width, int height, LayoutManager mgr) {
        ImageIcon icon = new ImageIcon(src);
        icon.setImage(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        setIcon(icon);
        setLayout(mgr);
    }

    /**
     * 
     * @param src Src of image
     * @param width width of icon
     * @param height height of icon
     */
    public ScaledBackground(String src, int width, int height) {
        this(src, width, height, new FlowLayout());
    }
}
