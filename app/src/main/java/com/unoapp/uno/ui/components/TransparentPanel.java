package com.unoapp.uno.ui.components;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * Generic implementation of transparent JPanel
 */
public class TransparentPanel extends JPanel {

    /**
     * JPanel with opacity 0
     */
    public TransparentPanel() {
        super();
        setOpaque(false);
    }

    /**
     * JPanel with opacity 0
     * 
     * @param mgr layout manager of JPanel
     */
    public TransparentPanel(LayoutManager mgr) {
        super(mgr);
        setOpaque(false);
    }

    /**
     * JPanel with opacity 0
     * 
     * @param mgr layout manager of JPanel
     * @param d dimensions of JPanel
     */
    public TransparentPanel(LayoutManager mgr, Dimension d) {
        super(mgr);
        setOpaque(false);
        setPreferredSize(d);
    }
}
