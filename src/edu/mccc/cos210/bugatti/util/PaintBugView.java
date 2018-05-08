package edu.mccc.cos210.bugatti.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class PaintBugView extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int PIXELS_PER_INCH = 256;
	private static final int MAX_WIDTH_INCHES = 2;
	private static final int MAX_LENGTH_INCHES = 2;
	private static final int MARGIN = 2 * 16;
	private enum PaintState { WHITE, BLACK, WHITE_BOX, BLACK_BOX };
	private PaintState paintState = PaintState.WHITE;
	private final IBugPainter bugPainter;
	public PaintBugView(IBugPainter bugPainter) {
		this.bugPainter = bugPainter;
		setBackground(Color.WHITE);
		setPreferredSize(
			new Dimension(
				MAX_WIDTH_INCHES * PIXELS_PER_INCH + MARGIN,
				MAX_LENGTH_INCHES * PIXELS_PER_INCH + MARGIN
			)
		);
		addMouseListener(new MyMouseAdapter());
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		if (paintState == PaintState.WHITE || paintState == PaintState.WHITE_BOX) {
			g2d.setPaint(Color.WHITE);
		} else {
			g2d.setPaint(Color.BLACK);
		}
		g2d.fillRect(0, 0, getWidth(), getHeight());
		AffineTransform gat = new AffineTransform();
		gat.translate(getWidth() / 2, getHeight() / 2);
		gat.scale(1.0, -1.0);
		g2d.transform(gat);
		bugPainter.paintBug(g2d);
		if (paintState == PaintState.WHITE_BOX || paintState == PaintState.BLACK_BOX) {
			Rectangle2D r2d = bugPainter.getBounds2D();
			g2d.setStroke(new BasicStroke(0.0f));
			if (paintState == PaintState.WHITE || paintState == PaintState.WHITE_BOX) {
				g2d.setPaint(Color.BLACK);			
			} else {
				g2d.setPaint(Color.WHITE);
			}
			g2d.draw(r2d);
		}
		g2d.dispose();
	}
	private class MyMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent me) {
			switch (paintState) {
				case WHITE:
					paintState = PaintState.WHITE_BOX;
					break;
				case WHITE_BOX:
					paintState = PaintState.BLACK;
					break;
				case BLACK:
					paintState = PaintState.BLACK_BOX;
					break;
				case BLACK_BOX:
					paintState = PaintState.WHITE;
					break;
				default:
					break;
			}
			repaint();
		}
	}
}
