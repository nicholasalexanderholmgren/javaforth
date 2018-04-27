package javaforth;



import java.awt.EventQueue;

import javaforth.machineModel.ForthInterpretor;
import javaforth.machineModel.ForthMachine;
import javaforth.viewIde.IdeWindow;
//this is a test

public class JavaForth {
	private ForthMachine machine;
	private ForthInterpretor fi;
	private IdeWindow view;
	JavaForth() {
		
		this.machine = createForthMachine();
		
		this.fi = new ForthInterpretor(machine);
		
		
		System.out.println(fi.getInterpMachine().getFromAddress(2));
		
		this.view = createViewIDE(machine);
		this.machine.registerObserver(this.view);
	}
	public static IdeWindow createViewIDE(ForthMachine machine) {
		return new IdeWindow(machine);
	}
	public ForthMachine createForthMachine() { 
		return new ForthMachine();//this);
	}
	public static void main(String... args) {
		EventQueue.invokeLater(() ->new JavaForth());
	}
}
