package edu.mccc.cos210.bugatti.util.bugpainters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import edu.mccc.cos210.bugatti.util.IBugPainter;

/* 
Body lower
Point2D.Double[544.0, 344.0]
Point2D.Double[536.0, 367.0]
Point2D.Double[539.0, 486.0]
Point2D.Double[539.0, 488.0]
Point2D.Double[550.0, 636.0]
Point2D.Double[520.0, 630.0]

Body upper
Point2D.Double[554.0, 236.0]
Point2D.Double[528.0, 224.0]
Point2D.Double[547.0, 208.0]
Point2D.Double[548.0, 209.0]
Point2D.Double[558.0, 198.0]
Point2D.Double[520.0, 188.0]

Wings
Point2D.Double[531.0, 309.0]
Point2D.Double[525.0, 361.0]
Point2D.Double[646.0, 360.0]
Point2D.Double[646.0, 360.0]
Point2D.Double[796.0, 371.0]
Point2D.Double[866.0, 346.0]
Point2D.Double[866.0, 346.0]
Point2D.Double[900.0, 322.0]
Point2D.Double[766.0, 291.0]
Point2D.Double[766.0, 291.0]
Point2D.Double[913.0, 273.0]
Point2D.Double[891.0, 244.0]
Point2D.Double[891.0, 244.0]
Point2D.Double[864.0, 206.0]
Point2D.Double[773.0, 211.0]
Point2D.Double[771.0, 212.0]
Point2D.Double[707.0, 223.0]
Point2D.Double[630.0, 211.0]
Point2D.Double[628.0, 210.0]
Point2D.Double[583.0, 206.0]
Point2D.Double[554.0, 237.0]
Point2D.Double[554.0, 238.0]
Point2D.Double[533.0, 261.0]
Point2D.Double[531.0, 308.0]

Wing line
Point2D.Double[536.0, 273.0]
Point2D.Double[562.0, 288.0]
Point2D.Double[767.0, 291.0]

lines
Point2D.Double[541.0, 580.0]
Point2D.Double[531.0, 588.0]
Point2D.Double[520.0, 588.0]
Point2D.Double[541.0, 530.0]
Point2D.Double[532.0, 540.0]
Point2D.Double[520.0, 540.0]
Point2D.Double[539.0, 486.0]
Point2D.Double[530.0, 492.0]
Point2D.Double[520.0, 492.0]
Point2D.Double[538.0, 441.0]
Point2D.Double[531.0, 453.0]
Point2D.Double[520.0, 453.0]
Point2D.Double[537.0, 409.0]
Point2D.Double[530.0, 416.0]
Point2D.Double[520.0, 417.0]
Point2D.Double[541.0, 349.0]
Point2D.Double[532.0, 365.0]
Point2D.Double[520.0, 364.0]
Point2D.Double[520.0, 222.0]
Point2D.Double[530.0, 224.0]
Point2D.Double[541.0, 214.0]
*/

public class FlamingBurrito implements IBugPainter {
	private static final Rectangle2D BOUNDS = new Rectangle2D.Double(
			-256.0 - 2.0,
			-256.0 - 2.0,
			512.0 + 4.0,
			512.0 + 4.0
		);
	@Override
	public void paintBug(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(3.0f));

		// Draw body.
		Path2D path = new Path2D.Double();
		path.moveTo(-17, 132);
		path.quadTo(-4, 138, -13.5, 146);
		path.quadTo(-19, 151, 0, 156);
		path.quadTo(19, 151, 13.5, 146);
		path.quadTo(4, 138, 17, 132);
		path.lineTo(12, 78);
		path.quadTo(8, 66.5, 9.5, 7);
		path.quadTo(15, -68, 0, -65);
		path.quadTo(-15, -68, -9.5, 7);
		path.quadTo(-8, 66.5, -12, 78);
		path.lineTo(-12, 78);
		path.closePath();
		g2d.setPaint(Color.BLACK);
		g2d.draw(path);
		g2d.setPaint(new Color(16, 182, 36));
		g2d.fill(path);
		
		// Draw wing.
		Path2D wing = new Path2D.Double();
		wing.moveTo(-5.5, 95.5);
		wing.quadTo(-2.5, 69.5, -63, 70);
		wing.quadTo(-138, 64.5, -173, 77);
		wing.quadTo(-190, 89, -123, 104.5);
		wing.quadTo(-196.5, 113.5, -185.5, 128);
		wing.quadTo(-172, 147, -126.5, 144.5);
		wing.quadTo(-93.5, 138.5, -55, 144.5);
		wing.quadTo(-31.5, 147, -17, 131.5);
		wing.quadTo(-6.5, 119.5, -5.5, 96);
		g2d.setPaint(Color.BLACK);
		g2d.draw(wing);
		g2d.setPaint(new Color(228,241,234));
		g2d.fill(wing);
		wing.closePath();
		
		wing = new Path2D.Double();
		wing.moveTo(5.5, 95.5);
		wing.quadTo(2.5, 69.5, 63, 70);
		wing.quadTo(138, 64.5, 173, 77);
		wing.quadTo(190, 89, 123, 104.5);
		wing.quadTo(196.5, 113.5, 185.5, 128);
		wing.quadTo(172, 147, 126.5, 144.5);
		wing.quadTo(93.5, 138.5, 55, 144.5);
		wing.quadTo(31.5, 147, 17, 131.5);
		wing.quadTo(6.5, 119.5, 5.5, 96);
		g2d.setPaint(Color.BLACK);
		g2d.draw(wing);
		g2d.setPaint(new Color(228,241,234));
		g2d.fill(wing);
		wing.closePath();

		// Draw lines.
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(1.5f));
		QuadCurve2D line;
		line = new QuadCurve2D.Double(-10.5, -40, -5.5, -44, 0, -44);
		g2d.draw(line);
		line = new QuadCurve2D.Double(-10.5, -15, -6, -20, 0, -20);
		g2d.draw(line);
		line = new QuadCurve2D.Double(-9.5, 7, -5, 4, 0, 4);
		g2d.draw(line);
		line = new QuadCurve2D.Double(-9, 29.5, -5.5, 23.5, 0, 23.5);
		g2d.draw(line);
		line = new QuadCurve2D.Double(-8.5, 45.5, -5, 42, 0, 41.5);
		g2d.draw(line);
		line = new QuadCurve2D.Double(-10.5, 75.5, -6, 67.5, 0, 68);
		g2d.draw(line);
		line = new QuadCurve2D.Double(0, 139, -5, 138, -10.5, 143);
		g2d.draw(line);
		line = new QuadCurve2D.Double(-8, 113.5, -21, 106, -123.5, 104.5);
		g2d.draw(line);
		line = new QuadCurve2D.Double(10.5, -40, 5.5, -44, 0, -44);
		g2d.draw(line);
		line = new QuadCurve2D.Double(10.5, -15, 6, -20, 0, -20);
		g2d.draw(line);
		line = new QuadCurve2D.Double(9.5, 7, 5, 4, 0, 4);
		g2d.draw(line);
		line = new QuadCurve2D.Double(9, 29.5, 5.5, 23.5, 0, 23.5);
		g2d.draw(line);
		line = new QuadCurve2D.Double(8.5, 45.5, 5, 42, 0, 41.5);
		g2d.draw(line);
		line = new QuadCurve2D.Double(10.5, 75.5, 6, 67.5, 0, 68);
		g2d.draw(line);
		line = new QuadCurve2D.Double(0, 139, 5, 138, 10.5, 143);
		g2d.draw(line);
		line = new QuadCurve2D.Double(8, 113.5, 21, 106, 123.5, 104.5);
		g2d.draw(line);
	}
	@Override
	public Rectangle2D getBounds2D() {
		return BOUNDS;
	}
	@Override
	public Color getAccentColorHint() {
		return new Color(140, 186, 242);
	}
	@Override
	public Color getContrastColorHint() {
		return IBugPainter.NO_BADGE;
	}
}
