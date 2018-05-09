package edu.mccc.cos210.ds.fp.javaforth.graphicswords;

import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public class DrawCubicCurve extends GraphicsWordBase {
	private double x1;
	private double y1;
	private double ctrlx1;
	private double ctrly1;
	private double ctrlx2;
	private double ctrly2;
	private double x2;
	private double y2;
	public DrawCubicCurve(GraphicsFrame frame) {
		super("DrawCubicCurve", "x1 y1 ctrlx1 ctrly1 ctrlx2 ctrly2 x2 y2 -- Draws a cubic curve using current color set by SETCOLOR.", frame);
	}
	@Override
	public void draw(Graphics2D g2d) {
		CubicCurve2D c = new CubicCurve2D.Double();
		c.setCurve(x1, y1, ctrlx1,
		           ctrly1, ctrlx2, ctrly2, x2, y2);
		g2d.draw(c);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		DrawCubicCurve word = new DrawCubicCurve(getFrame());
		word.y2 = (double)stack.pop();
		word.x2 = (double)stack.pop();
		word.ctrly2 = (double)stack.pop();
		word.ctrlx2 = (double)stack.pop();
		word.ctrly1 = (double)stack.pop();
		word.ctrly1 = (double)stack.pop();
		word.y1 = (double)stack.pop();
		word.x1 = (double)stack.pop();
		getFrame().addWord(word);
	}
}
