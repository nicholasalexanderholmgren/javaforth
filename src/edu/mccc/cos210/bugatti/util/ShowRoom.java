package edu.mccc.cos210.bugatti.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.MouseInputAdapter;

public class ShowRoom {
	private List<CarInfo> theCars = new LinkedList<>();
	private JWindow popUp;
	private Dimension imageSize;
	private CarInfo currentPopUp = null;
	public static void main(String... args) throws IOException {
		ShowRoom sr = new ShowRoom();
		sr.loadImages();
		EventQueue.invokeLater(() -> sr.initSwing());
	}
	private void loadImages() throws IOException {
		File imageDir = new File("./images/bugatti");
		File[] images = imageDir.listFiles();
		if (images != null) {
			for (int i = 0; i < images.length; i++) {
				String carName = images[i].getName();
				carName = carName.substring(0, carName.length() - 4);
				CarInfo ci = new CarInfo();
				ci.setName(
					images[i].getName().substring(
						0,
						images[i].getName().length() - 4
					)
				);
				ci.setImage(ImageIO.read(images[i]));
				imageSize = new Dimension(
					ci.getImage().getWidth(),
					ci.getImage().getHeight()
				);
				theCars.add(ci);
			}
		}
	}
	private void initSwing() {
		JFrame jf = new JFrame("Show Room");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(0, 8));
		for (CarInfo ci : theCars) {
			JPanel panel = new CarPanel(ci);
			jp.add(panel);
		}
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		popUp = new JWindow();
		popUp.add(new PopUpView(), BorderLayout.CENTER);
		popUp.setAlwaysOnTop(true);
		popUp.setSize(imageSize);
		popUp.setLocationRelativeTo(jf);
		popUp.setVisible(false);
		jf.setVisible(true);
	}
	private class CarInfo {
		private String name;
		private BufferedImage bi;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public BufferedImage getImage() {
			return bi;
		}
		public void setImage(BufferedImage bi) {
			this.bi = bi;
		}
	}
	private class PopUpView extends JPanel {
		private static final long serialVersionUID = 1L;
		public PopUpView() {
			setBackground(new Color(230, 230, 230));
			setBorder(
				new CompoundBorder(
					new BevelBorder(BevelBorder.RAISED),
					new EtchedBorder()
				)
			);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			if (currentPopUp != null) {
				g2d.drawRenderedImage(
					currentPopUp.getImage(),
					new AffineTransform()
				);
			}
			g2d.dispose();
		}
	}
	private class CarPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private CarInfo carInfo;
		private double scale = 0.25;
		public CarPanel(CarInfo carInfo) {
			this.carInfo = carInfo;
			setPreferredSize(
				new Dimension(
					(int) (carInfo.getImage().getWidth() * scale + 9.0),
					(int) (carInfo.getImage().getHeight() * scale)
				)
			);
			setBackground(Color.WHITE);
			addMouseListener(new MyMouseAdapter());
		}
		private class MyMouseAdapter extends MouseInputAdapter {
			@Override
			public void mousePressed(MouseEvent me) {
				currentPopUp = carInfo;
				popUp.setVisible(true);
			}
			@Override
			public void mouseReleased(MouseEvent me) {
				currentPopUp = null;
				popUp.setVisible(false);
			}
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setFont(
				g2d.getFont().deriveFont(Font.BOLD, 14.0f)
			);
			int w = SwingUtilities.computeStringWidth(
				g2d.getFontMetrics(),
				carInfo.getName()
			);
			g2d.drawString(
				carInfo.getName(),
				(int) (getWidth() / 2.0 - w / 2),
				16
			);
			g2d.drawRenderedImage(
				carInfo.getImage(),
				AffineTransform.getScaleInstance(scale, scale)
			);
			g2d.dispose();
		}
	}
}
