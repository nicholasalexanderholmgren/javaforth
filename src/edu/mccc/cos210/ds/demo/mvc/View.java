package edu.mccc.cos210.ds.demo.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

public class View extends JPanel {
	private static final long serialVersionUID = 1L;
	private JList<String> jList;
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private BufferedImage bi = null;
	private JPanel photo = new JPanel() {
		private static final long serialVersionUID = 1L;
		@Override
		public Color getBackground() {
			return Color.WHITE;
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(184, 241);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			if (bi != null) {
				g2d.drawRenderedImage(bi, new AffineTransform());
			}
			g2d.dispose();
		}
	};
	public View() {
		try {
			EventQueue.invokeAndWait(
				() -> initSwing()
			);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	public void setPhoto(BufferedImage bi) {
		this.bi = bi;
		repaint();
	}
	public void addName(String name) {
		listModel.addElement(name);
	}
	public JList<String> getJList() {
		return jList;
	}
	public void addListSelectionListener(ListSelectionListener lsl) {
		jList.addListSelectionListener(lsl);
	}
	@Override
	public Color getBackground() {
		return Color.WHITE;
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(184 * 2, 340);
	}
	private void initSwing() {
		JFrame jf = new JFrame("Hello MVC!");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(jList = new JList<String>(listModel), BorderLayout.WEST);
		jList.setBorder(new EmptyBorder(4, 8, 0, 0));
		JPanel jp = new JPanel();
		jp.add(photo);
		add(jp, BorderLayout.EAST);
		jf.add(this, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
}
