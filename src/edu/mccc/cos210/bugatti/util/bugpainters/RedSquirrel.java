package edu.mccc.cos210.bugatti.util.bugpainters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import edu.mccc.cos210.bugatti.util.IBugPainter;

public class RedSquirrel implements IBugPainter {
	private static final Rectangle2D BOUNDS = new Rectangle2D.Double(
		-256.0 - 2.0,
		-256.0 - 2.0,
		512.0 + 4.0,
		512.0 + 4.0
	);
	@Override
	public void paintBug(Graphics2D g2d) {
		Path2D body = new Path2D.Double();
		body.moveTo(-256.0, -256.0);
		body.lineTo(256.0, -256.0);
		body.lineTo(0.0, 256.0);
		body.closePath();
		g2d.setPaint(new Color(128, 0, 0));
		g2d.fill(body);
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(3.0f));
		g2d.draw(body);
		Line2D line = new Line2D.Double(0.0, 256.0, 0.0, -256.0);
		g2d.draw(line);
	}
	@Override
	public Rectangle2D getBounds2D() {
		return BOUNDS;
	}
}
