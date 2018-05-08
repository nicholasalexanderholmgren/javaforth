package edu.mccc.cos210.ds.fp.javaforth.graphicswords;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.StreamTokenizer;
import javax.swing.JFrame;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.controlwords.ForthControlWord;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public class LineTo extends GraphicsWordBase {
	private double x;
	private double y;
	public LineTo(GraphicsFrame frame) {
		super("LINETO", "TODO", frame);
//		this.frame = frame;
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary) {
		// Let's say LINETO takes 2 argument from stack
//		this.x = stack.pop();
//		this.y = stack.pop();
//		super.frame.addWord(this);
	}
	@Override
	void draw(Graphics2D g2d) {
//		g2d.drawLine(x1, y1, x2, y2);
	}
}
