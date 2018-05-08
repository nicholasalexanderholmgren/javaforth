package edu.mccc.cos210.ds.fp.javaforth.graphicswords;

import java.awt.Graphics2D;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public class ClearGraphics extends GraphicsWordBase {
	public ClearGraphics(String name, String description, GraphicsFrame frame) {
		super(name, description, frame);
		
	}
	@Override
	void draw(Graphics2D g2d) {
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary) {
		this.frame.clear();
	}
}
