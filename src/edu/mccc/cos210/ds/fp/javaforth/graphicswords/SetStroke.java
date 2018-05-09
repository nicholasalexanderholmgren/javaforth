package edu.mccc.cos210.ds.fp.javaforth.graphicswords;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public class SetStroke extends GraphicsWordBase {
	private float width;
	public SetStroke(GraphicsFrame frame) {
		super("SETSTROKE", "n1 -- Sets graphics stroke width in number of pixels.", frame);
	}
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(width));
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		SetStroke word = new SetStroke(super.getFrame());
		word.width = (float)(double)stack.pop();
		getFrame().addWord(word);
	}
}
