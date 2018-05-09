package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.awt.Graphics2D;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public abstract class GraphicsWordBase extends ForthWordBase{
	private GraphicsFrame frame;
	protected GraphicsFrame getFrame() {
		return frame;
	}
	public GraphicsWordBase(String name, String description, GraphicsFrame frame) {
		super(name, description);
		this.frame = frame;
	}
	abstract public void draw(Graphics2D g2d);
}
