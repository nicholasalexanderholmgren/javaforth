package edu.mccc.cos210.bugatti.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintJob {
	private static final String BUG = "FlamingBurrito";
	private Color theColor;
	private Color theBadge;
	private BufferedImage bi = null;
	public static void main(String... args) throws Exception {
		PaintJob pj = new PaintJob();
		IBugPainter bp = PaintBug.getBugPainter(BUG);
		pj.initImage(bp);
		EventQueue.invokeLater(() -> pj.initSwing());
	}
	private void initImage(IBugPainter bp) throws Exception {
		this.theColor = bp.getAccentColorHint();
		this.theBadge = bp.getContrastColorHint();
		BufferedImage bi_body = ImageIO.read(new FileInputStream("./images/bugatti_body.png"));
		BufferedImage bi_hood = ImageIO.read(new FileInputStream("./images/bugatti_hood.png"));
		BufferedImage bi_hood_mask = new BufferedImage(bi_body.getWidth(), bi_body.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g2dMask = bi_hood_mask.createGraphics();
		g2dMask.drawRenderedImage(bi_hood, new AffineTransform());
		g2dMask.dispose();
		this.bi = new BufferedImage(bi_body.getWidth(), bi_body.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		for (int row = 0; row < bi.getHeight(); row++) {
			for (int col = 0; col < bi.getWidth(); col++) {
				int bodyPixel = bi_body.getRGB(col, row);
				int hoodPixel = bi_hood.getRGB(col, row);
				int maskPixel = bi_hood_mask.getRGB(col, row);
				Color hoodColor = new Color(hoodPixel, true);
				Color maskColor = new Color(maskPixel, true);
				int newPixel = bodyPixel;
				if (hoodColor.getAlpha() == 255) {
					float[] hsb = Color.RGBtoHSB(theColor.getRed(), theColor.getGreen(), theColor.getBlue(), null);
					hsb[2] = hsb[2] * (maskColor.getRed() / 255.0f);
					newPixel = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
				}
				bi.setRGB(col, row, newPixel);
			}
		}
		Graphics2D g2d = createBugGraphics(bp.getBounds2D());
		paintBadge(g2d);
		bp.paintBug(g2d);
		g2d.dispose();
		ImageIO.write(bi, "png", new FileOutputStream("./images/bugatti/" + BUG + ".png"));
	}
	private void paintBadge(Graphics2D g2d) {
		g2d.setPaint(theBadge);
		g2d.fill(new Rectangle2D.Double(-256.0, -256.0, 512.0, 512.0));
	}
	private Graphics2D createBugGraphics(Rectangle2D bounds) {
		Graphics2D g2d = bi.createGraphics();
		AffineTransform gat = new AffineTransform();
		gat.translate(bi.getWidth() / 2.0 + 5.0, bi.getHeight() / 2.0 - 16.0);
		gat.scale(0.25, -0.25);
		g2d.transform(gat);
		return g2d;
	}
	private void initSwing() {
		JFrame jf = new JFrame("Paint Job");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(bi.getWidth(), bi.getHeight());
			}
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawRenderedImage(bi, new AffineTransform());
				g2d.dispose();
			}
		};
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
}
