package edu.mccc.cos210.ds.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;

public class Semaphore2 {
	public static void main(String... args) {
		Semaphore2 s = new Semaphore2();
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
		// {Space} JINGCHAO {Space} FENG {Space} COS {Invert T} 231 {SPACE} {Invert J}
		IArray<Integer> sequence = new Array<Integer>(24);
		sequence.set(0, 26);
		sequence.set(1, 9);
		sequence.set(2, 8);
		sequence.set(3, 13);
		sequence.set(4, 6);
		sequence.set(5, 2);
		sequence.set(6, 7);
		sequence.set(7, 0);
		sequence.set(8, 14);
		sequence.set(9, 26);
		sequence.set(10, 5);
		sequence.set(11, 4);
		sequence.set(12, 13);
		sequence.set(13, 6);
		sequence.set(14, 26);
		sequence.set(15, 2);
		sequence.set(16, 14);
		sequence.set(17, 18);
		sequence.set(18, 27);
		sequence.set(19, 2);
		sequence.set(20, 3);
		sequence.set(21, 1);
		sequence.set(22, 26);
		sequence.set(23, 28);
		final IArray<AffineTransform> frames = new Array<AffineTransform>(29);
		frames.set(0, AffineTransform.getTranslateInstance(0.0 - X_MARGIN, 0.0 - Y_MARGIN)); 				/* A */
		frames.set(1, AffineTransform.getTranslateInstance(-920.0 - X_MARGIN, 0.0 - Y_MARGIN)); 			/* B */
		frames.set(2, AffineTransform.getTranslateInstance(-1841.0 - X_MARGIN, 0.0 - Y_MARGIN)); 			/* C */
		frames.set(3, AffineTransform.getTranslateInstance(-2761.0 - X_MARGIN, 0.0 - Y_MARGIN)); 			/* D */
		frames.set(4, AffineTransform.getTranslateInstance(-3682.0 - X_MARGIN, 0.0 - Y_MARGIN)); 			/* E */
		frames.set(5, AffineTransform.getTranslateInstance(-4602.0 - X_MARGIN, 0.0 - Y_MARGIN)); 			/* F */
		frames.set(6, AffineTransform.getTranslateInstance(0.0 - X_MARGIN, -771.0 - Y_MARGIN)); 			/* G */
		frames.set(7, AffineTransform.getTranslateInstance(-920.0 - X_MARGIN, -771.0 - Y_MARGIN)); 		/* H */
		frames.set(8, AffineTransform.getTranslateInstance(-1841.0 - X_MARGIN, -771.0 - Y_MARGIN)); 		/* I */
		frames.set(9, AffineTransform.getTranslateInstance(-2762.0 - X_MARGIN, -771.0 - Y_MARGIN)); 		/* J */
		frames.set(10, AffineTransform.getTranslateInstance(-3682.0 - X_MARGIN, -771.0 - Y_MARGIN)); 		/* K */
		frames.set(11, AffineTransform.getTranslateInstance(-4603.0 - X_MARGIN, -771.0 - Y_MARGIN)); 		/* L */
		frames.set(12, AffineTransform.getTranslateInstance(0.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 		/* M */
		frames.set(13, AffineTransform.getTranslateInstance(-921.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 		/* N */
		frames.set(14, AffineTransform.getTranslateInstance(-1841.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 	/* O */
		frames.set(15, AffineTransform.getTranslateInstance(-2762.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 	/* P */
		frames.set(16, AffineTransform.getTranslateInstance(-3682.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 	/* Q */
		frames.set(17, AffineTransform.getTranslateInstance(-4603.0 - X_MARGIN, -1542.0 - Y_MARGIN)); 	/* R */
		frames.set(18, AffineTransform.getTranslateInstance(0.0 - X_MARGIN, -2313.0 - Y_MARGIN)); 		/* S */
		frames.set(19, AffineTransform.getTranslateInstance(-921.0 - X_MARGIN, -2314.0 - Y_MARGIN)); 		/* T */
		frames.set(20, AffineTransform.getTranslateInstance(-1841.0 - X_MARGIN, -2314.0 - Y_MARGIN)); 	/* U */
		frames.set(21, AffineTransform.getTranslateInstance(-2762.0 - X_MARGIN, -2314.0 - Y_MARGIN)); 	/* V */
		frames.set(22, AffineTransform.getTranslateInstance(-3682.0 - X_MARGIN, -2314.0 - Y_MARGIN)); 	/* W */
		frames.set(23, AffineTransform.getTranslateInstance(-4603.0 - X_MARGIN, -2314.0 - Y_MARGIN)); 	/* X */
		frames.set(24, AffineTransform.getTranslateInstance(0.0 - X_MARGIN, -3085.0 - Y_MARGIN)); 		/* Y */
		frames.set(25, AffineTransform.getTranslateInstance(-921.0 - X_MARGIN, -3085.0 - Y_MARGIN)); 		/* Z */
		frames.set(26, AffineTransform.getTranslateInstance(-1841.0 - X_MARGIN, -3085.0 - Y_MARGIN)); 	/* Space */
		AffineTransform invertT = AffineTransform.getScaleInstance(-1, 1);
		invertT.concatenate(AffineTransform.getTranslateInstance(-1841.0 - X_MARGIN * 1.9, -2314.0 - Y_MARGIN));
		frames.set(27, invertT);										
		AffineTransform invertJ = AffineTransform.getScaleInstance(-1, 1);
		invertJ.concatenate(AffineTransform.getTranslateInstance(-3682.0 - X_MARGIN * 1.9, -771.0 - Y_MARGIN));
		frames.set(28, invertJ);																	
		
		JFrame jf = new JFrame("Semaphore");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel() {
			private static final long serialVersionUID = 1L;
			int currentIndex = 0;
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
				currentIndex = currentIndex == sequence.getSize() - 1 ? 1 : currentIndex + 1;
				super.repaint();
			}
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				AffineTransform at = frames.get(sequence.get(currentIndex));
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
