package com.unoapp.uno.ui.components;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class TransparentPanel extends JPanel {

    public TransparentPanel() {
        super();
        setOpaque(false);
    }

    public TransparentPanel(LayoutManager borderLayout) {
        super(borderLayout);
        setOpaque(false);
    }
}
