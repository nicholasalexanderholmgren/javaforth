package edu.mccc.cos210.ds.fp.javaforth.graphicswords;

import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public class FillQuadCurve extends GraphicsWordBase {
	private double x1;
	private double y1;
	private double ctrlx;
	private double ctrly;
	private double x2;
	private double y2;
	public FillQuadCurve(GraphicsFrame frame) {
		super("FILLQUADCURVE", "x1 y1 ctrlx ctrly x2 y2 -- Fills a Quadratic Curve using current corlor.", frame);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(Graphics2D g2d) {
		QuadCurve2D q = new QuadCurve2D.Double();
		q.setCurve(x1, y1, ctrlx, ctrly, x2, y2);
		g2d.fill(q);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		FillQuadCurve word = new FillQuadCurve(getFrame());
		word.y2 = (double) stack.pop();
		word.x2 = (double) stack.pop();
		word.ctrly = (double) stack.pop();
		word.ctrlx = (double) stack.pop();
		word.y1 = (double) stack.pop();
		word.x1 = (double) stack.pop();
		getFrame().addWord(word);
	}
}
