package com.unoapp.uno.ui.drawables;

import static java.awt.Color.WHITE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG
 * transcoder</a>.
 */
public class BlankCard {

    /**
     * Paints the transcoded SVG image on the specified graphics context.
     * 
     * @param g Graphics context.
     * @param color color of blank card
     */
    public static void paint(Color color, Graphics2D g) {
        Shape shape = null;

        //

        // _0

        // _0_0
        shape = new RoundRectangle2D.Double(23.5, 23.5, 1073, 1733, 83, 83);
        g.setPaint(color);
        g.fill(shape);
        g.setPaint(WHITE);
        g.setStroke(new BasicStroke(47, 0, 0, 4));
        g.draw(shape);

        // _0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(918.133, 1132.36);
        ((GeneralPath) shape).curveTo(680.378, 1528.94, 327.298, 1737.89, 129.507, 1599.07);
        ((GeneralPath) shape).curveTo(-68.2849, 1460.24, -35.8882, 1026.22, 201.867, 629.637);
        ((GeneralPath) shape).curveTo(439.622, 233.059, 792.702, 24.1077, 990.493, 162.932);
        ((GeneralPath) shape).curveTo(1188.28, 301.756, 1155.89, 735.785, 918.133, 1132.36);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

    }
}
