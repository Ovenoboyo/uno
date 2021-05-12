package com.unoapp.uno.ui.components;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Generic fullscreen frame implementation
 */
public abstract class GenericMenuScreen extends JFrame {
    public final int MAX_COMPONENT_X, MAX_COMPONENT_Y, xSize, ySize;

    protected GenericMenuScreen() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        xSize = ((int) tk.getScreenSize().getWidth());
        ySize = ((int) tk.getScreenSize().getHeight());

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);

        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();

        device.setFullScreenWindow(this);

        MAX_COMPONENT_X = xSize - 120;
        MAX_COMPONENT_Y = ySize - 60;
    }
}
