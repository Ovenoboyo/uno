package com.unoapp.uno.ui.components;

import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * Generic implementation of transparent JPanel
 */
public class TransparentPanel extends JPanel {

    public TransparentPanel() {
        super();
        setOpaque(false);
    }

    public TransparentPanel(LayoutManager mgr) {
        super(mgr);
        setOpaque(false);
    }
}
