package edu.mccc.cos210.ds.fp.javaforth.graphicswords;

import java.awt.Graphics2D;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public abstract class GraphicsWordBase extends ForthWordBase {
	protected GraphicsFrame frame;
	public GraphicsWordBase(String name, String description, GraphicsFrame frame) {
		super(name, description, false);
		this.frame = frame;
	}
	abstract void draw(Graphics2D g2d);
}
