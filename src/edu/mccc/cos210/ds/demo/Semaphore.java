package edu.mccc.cos210.ds.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;

public class Semaphore {
	public static void main(String... args) {
		Semaphore s = new Semaphore();
		EventQueue.invokeLater(() -> s.initSwing(s.initImage()));
	}
	private BufferedImage initImage() {
		BufferedImage bi = null;
		try {
			 bi = ImageIO.read(new File("./images/semaphore.gif"));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		return bi;
	}
	private void initSwing(final BufferedImage bi) {
		final double X_MARGIN = 32.0;
		final double Y_MARGIN = 8.0;
		IArray<Point2D> frames = new Array<>(27);
		frames.set(0, new Point2D.Double(0.0 - X_MARGIN, 0.0 - Y_MARGIN)); 				/* A */
		frames.set(1, new Point2D.Double(-920.0 - X_MARGIN, 0.0 - Y_MARGIN)); 			/* B */
		frames.set(2, new Point2D.Double(-1841.0 - X_MARGIN, 0.0 - Y_MARGIN)); 			/* C */
		frames.set(3, new Point2D.Double(-2761.0 - X_MARGIN, 0.0 - Y_MARGIN)); 			/* D */
		frames.set(4, new Point2D.Double(-3682.0 - X_MARGIN, 0.0 - Y_MARGIN)); 			/* E */
		frames.set(5, new Point2D.Double(-4602.0 - X_MARGIN, 0.0 - Y_MARGIN)); 			/* F */
		frames.set(6, new Point2D.Double(0.0 - X_MARGIN, -771.0 - Y_MARGIN)); 			/* G */
		frames.set(7, new Point2D.Double(-920.0 - X_MARGIN, -771.0 - Y_MARGIN)); 		/* H */
		frames.set(8, new Point2D.Double(-1841.0 - X_MARGIN, -771.0 - Y_MARGIN)); 		/* I */
		frames.set(9, new Point2D.Double(-2762.0 - X_MARGIN, -771.0 - Y_MARGIN)); 		/* J */
		frames.set(10, new Point2D.Double(-3682.0 - X_MARGIN, -771.0 - Y_MARGIN)); 		/* K */
		frames.set(11, new Point2D.Double(-4603.0 - X_MARGIN, -771.0 - Y_MARGIN)); 		/* L */
		frames.set(12, new Point2D.Double(0.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 		/* M */
		frames.set(13, new Point2D.Double(-921.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 		/* N */
		frames.set(14, new Point2D.Double(-1841.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 	/* O */
		frames.set(15, new Point2D.Double(-2762.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 	/* P */
		frames.set(16, new Point2D.Double(-3682.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 	/* Q */
		frames.set(17, new Point2D.Double(-4603.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 	/* R */
		frames.set(18, new Point2D.Double(0.0 - X_MARGIN, -2313.0 - Y_MARGIN)); 		/* S */
		frames.set(19, new Point2D.Double(-921.0 - X_MARGIN, -2314.0 - Y_MARGIN)); 		/* T */
		frames.set(20, new Point2D.Double(-1841.0 - X_MARGIN, -2314.0 - Y_MARGIN)); 	/* U */
		frames.set(21, new Point2D.Double(-2762.0 - X_MARGIN, -2314.0 - Y_MARGIN)); 	/* V */
		frames.set(22, new Point2D.Double(-3682.0 - X_MARGIN, -2314.0 - Y_MARGIN)); 	/* W */
		frames.set(23, new Point2D.Double(-4603.0 - X_MARGIN, -2314.0 - Y_MARGIN)); 	/* X */
		frames.set(24, new Point2D.Double(0.0 - X_MARGIN, -3085.0 - Y_MARGIN)); 		/* Y */
		frames.set(25, new Point2D.Double(-921.0 - X_MARGIN, -3085.0 - Y_MARGIN)); 		/* Z */
		frames.set(26, new Point2D.Double(-1841.0 - X_MARGIN, -3085.0 - Y_MARGIN)); 	/* Space */
		JFrame jf = new JFrame("Semaphore");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel() {
			private static final long serialVersionUID = 1L;
			int frameNumber = 26;
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(936, 793);
			}
			@Override
			public Color getBackground() {
				return Color.WHITE;
			}
			@Override
			public void repaint() {
				frameNumber = frameNumber == frames.getSize() - 1 ? 0 : frameNumber + 1;
				super.repaint();
			}
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				AffineTransform at = AffineTransform.getTranslateInstance(
					frames.get(frameNumber).getX(),
					frames.get(frameNumber).getY()
				);
				g2d.drawRenderedImage(bi, at);
				g2d.dispose();
			}
		};
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		new javax.swing.Timer(500, ae ->  jp.repaint()).start();
	}
}
