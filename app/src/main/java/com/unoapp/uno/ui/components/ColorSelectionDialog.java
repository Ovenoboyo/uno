package com.unoapp.uno.ui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.unoapp.uno.ui.drawables.ColorSelection;
import com.unoapp.uno.utils.Constants;

public class ColorSelectionDialog extends JDialog {
    private JPanel global = new JPanel();
    private JLabel label = new JLabel();

    private final int expandedSize = 145;
    private final int normalSize = 120;
    private final int offset = 30;

    private final onClickListener mListener;

    public ColorSelectionDialog(onClickListener listener) {
        super();
        this.mListener = listener;
        init();
    }

    private void setMouseListener(ColorSelectionImage img, int quadrant) {
        img.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                mListener.onClick(getColorFromQuadrant(quadrant));
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                img.expand();
                int[] coordinates = getCoordinates(quadrant, true);
                img.setBounds(coordinates[0], coordinates[1], expandedSize, expandedSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                img.shrink();
                int[] coordinates = getCoordinates(quadrant, false);
                img.setBounds(coordinates[0], coordinates[1], normalSize, normalSize);
            }

        });
    }

    private void init() {
        setAlwaysOnTop(true);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                return;
            }
        });

        global.setLayout(null);
        for (int i = 1; i <= 4; i++)
            global.add(generateColorArc(i));

        global.setPreferredSize(new Dimension((offset + expandedSize) * 2, ((offset + expandedSize) * 2) + 30));

        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        label.setText("Select Color");
        label.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(label);
        add(global);
    }

    private ColorSelectionImage generateColorArc(int quadrant) {
        ColorSelectionImage img = new ColorSelectionImage(getAngle(quadrant), getColorFromQuadrant(quadrant));

        int[] c = getCoordinates(quadrant, false);
        img.setBounds(c[0], c[1], normalSize, normalSize);

        setMouseListener(img, quadrant);

        return img;
    }

    private int[] getCoordinates(int quadrant, boolean expanded) {
        switch (quadrant) {
            default:
            case 1:
                return (expanded) ? new int[] { expandedSize + offset, offset }
                        : new int[] { expandedSize + offset, expandedSize + offset - normalSize };

            case 2:
                return (expanded) ? new int[] { offset, offset }
                        : new int[] { expandedSize + offset - normalSize, expandedSize + offset - normalSize };

            case 3:
                return (expanded) ? new int[] { offset, expandedSize + offset }
                        : new int[] { expandedSize + offset - normalSize, expandedSize + offset };

            case 4:
                return new int[] { expandedSize + offset, expandedSize + offset };
        }
    }

    private int getAngle(int quadrant) {
        switch (quadrant) {
            default:
            case 1:
                return 0;
            case 2:
                return 90;
            case 3:
                return 180;
            case 4:
                return 270;
        }
    }

    private Constants.Color getColorFromQuadrant(int quadrant) {
        switch (quadrant) {
            default:
            case 1:
                return Constants.Color.BLUE;
            case 2:
                return Constants.Color.RED;
            case 3:
                return Constants.Color.YELLOW;
            case 4:
                return Constants.Color.GREEN;
        }
    }

    public void showDialog() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ColorSelectionImage extends JLabel {
        private final int angle;
        private final Constants.Color color;
        private boolean expanded = false;

        @Override
        public Dimension getPreferredSize() {
            if (!this.expanded)
                return new Dimension(normalSize, normalSize);
            return new Dimension(expandedSize, expandedSize);
        }

        ColorSelectionImage(int angle, Constants.Color color) {
            this.angle = angle;
            this.color = color;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            ColorSelection.paint(g2d, Constants.getColor(color, false), angle, this.expanded, expandedSize - 5,
                    normalSize);

            g2d.dispose();
        }

        private void expand() {
            this.expanded = true;
            revalidate();
        }

        private void shrink() {
            this.expanded = false;
            revalidate();
        }
    }

    public interface onClickListener {
        void onClick(Constants.Color color);
    }
}