package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;;

@SuppressWarnings("serial")
public class GraphicsFrame extends JFrame {
	private Panel panel;
	public GraphicsFrame() {
		super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		super.getContentPane().setPreferredSize(new Dimension(800, 800));
		panel = new Panel();
		panel.setSize(800, 800);
		super.getContentPane().add(panel);
		super.setSize(800, 800);
		super.setResizable(false);
	}
	public void clear() {
		EventQueue.invokeLater(() -> this.panel.clear());
	}
	public void addWord(GraphicsWordBase word) {
		EventQueue.invokeLater(() -> {
			super.setVisible(true);
			this.panel.addWord(word);
		});
	}
	class Panel extends JComponent {
		private SinglyLinkedList<GraphicsWordBase> words = new SinglyLinkedList<>();
		public void clear() {
			this.words = new SinglyLinkedList<>();
			this.repaint();
		}
		public void addWord(GraphicsWordBase word) {
			this.words.addLast(word);
			this.repaint();
		}
		@Override
		public void paintComponents(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			// for each word, call GraphicsWordBase.draw(g2d);
			for (GraphicsWordBase word : words) {
				word.draw(g2d);
			}
			g2d.dispose();
		}
	}
}
