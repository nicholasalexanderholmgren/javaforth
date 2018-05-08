package edu.mccc.cos210.ds.fp.javaforth;

import java.awt.EventQueue;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.IdeWindow;

public class JavaForth {
	private ForthMachine machine;
	private IdeWindow view;
	JavaForth() {
		this.machine = createForthMachine();
		this.view = createViewIDE(machine);
	}
	public static IdeWindow createViewIDE(ForthMachine machine) {
		return new IdeWindow(machine);
	}
	public ForthMachine createForthMachine() { 
		return new ForthMachine();
	}
	public static void main(String... args) {
		EventQueue.invokeLater(() ->new JavaForth());
	}
}
