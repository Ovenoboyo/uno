package com.unoapp.uno.ui.components;

import java.awt.*;
import java.awt.geom.*;
import static java.awt.Color.*;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG
 * transcoder</a>.
 */
public class DrawFour {
        /**
         * Paints the transcoded SVG image on the specified graphics context.
         * 
         * @param g Graphics context.
         */
        public static void paint(Graphics2D g2d, double x, double y) {
                Shape shape = null;
                //

                // _0

                // _0_0
                Graphics2D g = (Graphics2D) g2d.create();
                g.translate(x, y);
                g.scale(0.15, 0.15);
                shape = new RoundRectangle2D.Double(452.6000061035156, 17, 240.24600219726562, 374.4460144042969,
                                84.46160125732422, 84.46160125732422);
                g.setPaint(new Color(0x036BD2));
                g.fill(shape);
                g.setPaint(WHITE);
                g.setStroke(new BasicStroke(32.8462f, 0, 0, 4));
                g.draw(shape);

                // _0_1
                shape = new RoundRectangle2D.Double(334.35400390625, 130.5540008544922, 240.24600219726562,
                                374.4460144042969, 84.46160125732422, 84.46160125732422);
                g.setPaint(new Color(0x6CB560));
                g.fill(shape);
                g.setPaint(WHITE);
                g.draw(shape);

                // _0_2
                shape = new RoundRectangle2D.Double(135.39999389648438, 17, 240.24600219726562, 374.4460144042969,
                                84.46160125732422, 84.46160125732422);
                g.setPaint(new Color(0xEFD341));
                g.fill(shape);
                g.setPaint(WHITE);
                g.draw(shape);

                // _0_3
                shape = new RoundRectangle2D.Double(17.153799057006836, 130.5540008544922, 240.24600219726562,
                                374.4460144042969, 84.46160125732422, 84.46160125732422);
                g.setPaint(new Color(0xCE3C3D));
                g.fill(shape);
                g.setPaint(WHITE);
                g.draw(shape);
                g.dispose();

        }
}
