package edu.mccc.cos210.bugatti.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface IBugPainter {
	public static final Color NO_BADGE = new Color(0, 0, 0, 0);
	public void paintBug(Graphics2D g2d);
	public Rectangle2D getBounds2D();
	default public Color getAccentColorHint() {
		return new Color(0, 140, 200);
	}
	default public Color getContrastColorHint() {
		return NO_BADGE;
	}
}
