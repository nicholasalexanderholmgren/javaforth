package edu.mccc.cos210.ds.fp.javaforth.graphicswords;

import java.awt.Color;
import java.awt.Graphics2D;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public class SetColor extends GraphicsWordBase {
	private int r;
	private int g;
	private int b;
	private int a;
	public SetColor(GraphicsFrame frame) {
		super("SETCOLOR", "r g b a -- Sets color for the graphics words.", frame);
	}
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(new Color(r, g, b, a));
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		SetColor word = new SetColor(super.getFrame());
		word.a = (int)(double)stack.pop();
		word.b = (int)(double)stack.pop();
		word.g = (int)(double)stack.pop();
		word.r = (int)(double)stack.pop();
		getFrame().addWord(word);
	}
}
