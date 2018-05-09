package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Graphics2D;
import javax.swing.JFrame;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;;

public class GraphicsFrame extends JFrame {
	private SinglyLinkedList<GraphicsWordBase> words = new SinglyLinkedList<>(); 
	
	public GraphicsFrame(ForthMachine fm) {
		
	}
	public void clear() {
		this.words = new SinglyLinkedList<>();
		this.repaint();
	}
	public void paintComponent(Graphics2D g2d) {
		// for each word, call GraphicsWordBase.paint(g2d);
		double[] initPoints = words;
		
		for(GraphicsWordBase word : words) {
			
		}
		g2d.dispose();
	}
	public void addWord(GraphicsWordBase word) {
		this.words.addLast(word);
		this.repaint();
	}
}
