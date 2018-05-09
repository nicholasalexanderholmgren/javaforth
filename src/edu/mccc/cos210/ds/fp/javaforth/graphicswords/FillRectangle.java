package edu.mccc.cos210.ds.fp.javaforth.graphicswords;

import java.awt.Graphics2D;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public class FillRectangle extends GraphicsWordBase {
	private int x;
	private int y;
	private int width;
	private int height;
	public FillRectangle(GraphicsFrame frame) {
		super("FILLRECTANGLE", "x y width height -- Fill a rectangle using current color set by SETCOLOR.", frame);
	}
	@Override
	public void draw(Graphics2D g2d) {
		g2d.fillRect(x, y, width, height);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		FillRectangle word = new FillRectangle(getFrame());
		word.height = (int) (double) stack.pop();
		word.width = (int) (double) stack.pop();
		word.y = (int) (double) stack.pop();
		word.x = (int) (double) stack.pop();
		getFrame().addWord(word);
	}
}
