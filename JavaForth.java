package edu.mccc.cos210.ds.fp.javaforth;

import java.awt.EventQueue;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.IdeWindow;

public class JavaForth {
	private ForthMachine machine;
	private IdeWindow view;
	JavaForth() {
		this.machine = createForthMachine();
		this.view = createViewIDE();
		this.machine.registerObserver(this.view);
	}
	public static IdeWindow createViewIDE() {
		return new IdeWindow();
	}
	public ForthMachine createForthMachine() { 
		return new ForthMachine();//this);
	}
	public static void main(String... args) {
		EventQueue.invokeLater(() ->new JavaForth());
	}
}
