package edu.mccc.cos210.ds.fp.javaforth.controlwords;

public abstract class ForthLoopWordBase extends ForthControlWordBase {
	private double n1;
	private double n2;
	private ForthLoopWordBase parentLoop;
	public ForthLoopWordBase(String name, String description) {
		super(name, description, true);
	}
	public double getN1() {
		return n1;
	}
	public void setN1(double n1) {
		this.n1 = n1;
	}
	public double getN2() {
		return n2;
	}
	public void setN2(double n2) {
		this.n2 = n2;
	}
	public ForthLoopWordBase getParentLoop() {
		return parentLoop;
	}
	public void setParentLoop(ForthLoopWordBase parentLoop) {
		this.parentLoop = parentLoop;
	}
}
