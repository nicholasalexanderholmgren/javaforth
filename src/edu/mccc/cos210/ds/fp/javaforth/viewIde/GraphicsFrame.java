package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import javax.swing.JFrame;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.graphicswords.GraphicsWordBase;

public class GraphicsFrame extends JFrame {
	private SinglyLinkedList<GraphicsWordBase> words = new SinglyLinkedList<>(); 
	public void clear() {
		this.words = new SinglyLinkedList<>();
		this.repaint();
	}
//	@Override
//	public void paintComponent(g2d) {
//		// for each word, call GraphicsWordBase.paint(g2d);
//		g2d.dispose();
//	}
	public void addWord(GraphicsWordBase word) {
		this.words.addLast(word);
		this.repaint();
	}
}
