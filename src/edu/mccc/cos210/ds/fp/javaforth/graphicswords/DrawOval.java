package edu.mccc.cos210.ds.fp.javaforth.graphicswords;

import java.awt.Graphics2D;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public class DrawOval extends GraphicsWordBase {
	private int x;
	private int y;
	private int width;
	private int height;
	public DrawOval(GraphicsFrame frame) {
		super("DRAWOVAL", "x y width height -- Draws the outline of an oval. The result is a circle or ellipse that fits within the rectangle specified by the x, y, width, and height arguments.", frame);
	}
	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawOval(x, y, width, height);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		DrawOval word = new DrawOval(getFrame());
		word.height = ((Double)stack.pop()).intValue();
		word.width = ((Double)stack.pop()).intValue();
		word.y = ((Double)stack.pop()).intValue();
		word.x = ((Double)stack.pop()).intValue();
		getFrame().addWord(word);
	}
}
