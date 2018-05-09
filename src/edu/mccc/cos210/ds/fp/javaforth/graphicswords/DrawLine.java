package edu.mccc.cos210.ds.fp.javaforth.graphicswords;

import java.awt.Graphics2D;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public class DrawLine extends GraphicsWordBase {
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	public DrawLine(GraphicsFrame frame) {
		super("DRAWLINE", "x1 y1 x2 y2 -- Draw a line from (x1, y1) to (x2, y2).", frame);
	}
	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		// Takes parameter, create new instance and save it to graphics frame.
		DrawLine word = new DrawLine(getFrame());
		word.y2 = (double) stack.pop();
		word.x2 = (double) stack.pop();
		word.y1 = (double) stack.pop();
		word.x1 = (double) stack.pop();
		getFrame().addWord(word);
	}
}
