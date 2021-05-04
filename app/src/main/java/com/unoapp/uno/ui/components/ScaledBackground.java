package com.unoapp.uno.ui.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.LayoutManager;

public class ScaledBackground extends JLabel {
    public ScaledBackground(String src, int width, int height, LayoutManager mgr) {
        ImageIcon icon = new ImageIcon(src);
        icon.setImage(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        setIcon(icon);
        setLayout(mgr);
    }
}
