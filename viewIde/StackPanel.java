package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class StackPanel extends JScrollPane {
	private MyPanel panel;
	public StackPanel() {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//this.panel = (MyPanel) this.getViewport().getView();
		//this.panel.setBackground(Color.RED);
		JViewport jvp = new JViewport();
		jvp.setView(new MyPanel());
		this.setViewport(jvp);
		this.panel = (MyPanel) this.getViewport().getView();
	}
	public void update(String stack) {
		
	}
	private class MyPanel extends JPanel {
		private String stackData;
		MyPanel() {
			super();
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setPaint(
				new TexturePaint(
					new MyBufferedImage(
						this.getWidth(),
						this.getHeight(),
						BufferedImage.TYPE_3BYTE_BGR,
						"1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n",
						28
					),
					new Rectangle2D.Double(0.0, 0.0, this.getWidth(), this.getHeight())
				)	
			);
			g2d.fill(new Rectangle2D.Double(0.0, 0.0, this.getWidth(), this.getHeight()));
			g2d.dispose();
		}
	}
	private static class MyBufferedImage extends BufferedImage {
		public MyBufferedImage(int width, int height, int type, String s, int fontSize) {
				super(width, height, type);
				paintSelf(this.createGraphics(), s, fontSize);
		}
		private void paintSelf(Graphics2D g2d, String s, int fontSize) {
			g2d.setPaint(new Color(130, 130, 130));
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			AffineTransform fat = new AffineTransform();
			fat.setToScale(1.0, 1.0);
			fat.translate(0, 28);
			g2d.setFont(new Font(Font.SERIF, Font.BOLD, fontSize).deriveFont(fat));
			g2d.setPaint(Color.BLACK);
			g2d.drawString(
				s,
			    0,
				0
			);
		}
	}
}
