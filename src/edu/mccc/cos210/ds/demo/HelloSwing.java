package edu.mccc.cos210.ds.demo;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HelloSwing {
	public static void main(String... args) {
		HelloSwing hs = new HelloSwing();
		try {
			EventQueue.invokeAndWait(() -> hs.initSwing());
		} catch (InvocationTargetException | InterruptedException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		hs.process();
	}
	private void initSwing() {
		final JPanel jp;
		JFrame jf = new JFrame("Hello Swing!");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Calculate star coordinates.
		final double[][] starCoordinates = new double[5][2];
		for(int i = 0; i < 5; i++) {
			starCoordinates[i][0] = 250*Math.sin(Math.toRadians(72*i));
			starCoordinates[i][1] = 250*Math.cos(Math.toRadians(72*i));
		}
		jf.add(jp = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(800, 600);
			}
			@Override
			public Color getBackground() {
				return new Color(240, 180, 33);
			}
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Setup canvas.
				Graphics2D g2d = (Graphics2D) g.create();
				AffineTransform gat = new AffineTransform();
				gat.translate(getWidth() / 2.0, getHeight() / 2.0);
				gat.scale(1.0, -1.0);
				g2d.transform(gat);
				// Draw star.
				GeneralPath path = new GeneralPath();
				path.moveTo(starCoordinates[4][0],starCoordinates[4][1]);
				path.lineTo(starCoordinates[1][0],starCoordinates[1][1]);
				path.lineTo(starCoordinates[3][0],starCoordinates[3][1]);
				path.lineTo(starCoordinates[0][0],starCoordinates[0][1]);
				path.lineTo(starCoordinates[2][0],starCoordinates[2][1]);
				path.closePath();
				// Transform.
				AffineTransform at = new AffineTransform();
				at.rotate(Math.toRadians(Math.random() * 360.0));
				Shape s = at.createTransformedShape(path);
				g2d.setColor(Color.RED);
				g2d.fill(s);
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(13.0f));
				g2d.draw(s);
				g2d.dispose();
			}
		}, BorderLayout.CENTER);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setVisible(true);
		new javax.swing.Timer(100, ae -> jp.repaint()).start();
	}
	private void process() {
		System.out.println("Do some work on the Main Thread.");
	}
}
