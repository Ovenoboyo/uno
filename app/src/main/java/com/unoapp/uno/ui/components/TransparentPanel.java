package com.unoapp.uno.ui.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class TransparentPanel extends JPanel {

    public TransparentPanel() {
        super();
        setOpaque(false);
    }

    public TransparentPanel(BorderLayout borderLayout) {
        super(borderLayout);
        setOpaque(false);
    }
}
