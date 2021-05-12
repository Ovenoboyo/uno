package com.unoapp.uno.ui.drawables;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;

public class ColorSelection {
    private static double[] getQuadrantTranslation(int quadrant, boolean expanded, int expandedSize, int normalSize) {
        double[] tranlations = new double[2];
        switch (quadrant) {
            case 1:
                tranlations[0] = (expanded) ? (-expandedSize + 5 / 2) : -normalSize;
                tranlations[1] = (expanded) ? 5 / 2 : 0;
                break;

            default:
            case 2:
                tranlations[0] = (expanded) ? 5 / 2 : 0;
                tranlations[1] = (expanded) ? 5 / 2 : 0;
                break;

            case 3:
                tranlations[0] = (expanded) ? 5 / 2 : 0;
                tranlations[1] = (expanded) ? (-expandedSize + 5 / 2) : -normalSize;
                break;

            case 4:
                tranlations[0] = (expanded) ? (-expandedSize + 5 / 2) : -normalSize;
                tranlations[1] = (expanded) ? (-expandedSize + 5 / 2) : -normalSize;
                break;
        }

        return tranlations;
    }

    private static int getQuadrantFromAngle(int angle) {
        angle %= 360;
        if (angle < 0)
            angle += 360;
        return (angle / 90) % 4 + 1;
    }

    public static void paint(Graphics2D g, Color color, int rotate, boolean expanded, int expandedSize,
            int normalSize) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        if (expanded)
            arc.setFrame(0, 0, expandedSize * 2, expandedSize * 2);
        else
            arc.setFrame(0, 0, normalSize * 2, normalSize * 2);

        int quadrant = getQuadrantFromAngle(rotate);

        arc.setAngleStart(rotate);
        arc.setAngleExtent(90);

        double[] translations = getQuadrantTranslation(quadrant, expanded, expandedSize, normalSize);
        g2d.translate(translations[0], translations[1]);

        g2d.setPaint(color);

        g2d.fill(arc);

        if (expanded) {
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(5));
            g2d.draw(arc);
        }

        g2d.dispose();
    }
}
