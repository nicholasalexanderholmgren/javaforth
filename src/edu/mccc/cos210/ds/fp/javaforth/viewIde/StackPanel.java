package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthStack;

@SuppressWarnings("serial")
public class StackPanel extends JScrollPane {
	private DefaultListModel<String> list = new DefaultListModel<String>();
	public StackPanel(ForthMachine fm) {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JViewport jvp = new JViewport();
		jvp.setView(new JList<String>(list));
		this.setViewport(jvp);
	}
	public void update(ForthStack fs) {
		list.removeAllElements();
		for(String s : fs.asList()) {
			list.addElement(s);
		}
		// TODO
	}
}
