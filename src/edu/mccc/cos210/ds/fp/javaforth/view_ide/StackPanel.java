package edu.mccc.cos210.ds.fp.javaforth.view_ide;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import edu.mccc.cos210.ds.fp.javaforth.machine_model.ForthMachine;
import edu.mccc.cos210.ds.fp.javaforth.util.IStackUpdatedEventListener;

@SuppressWarnings("serial")
public class StackPanel extends JScrollPane implements IStackUpdatedEventListener {
	private DefaultListModel<String> list = new DefaultListModel<String>();
	private JList<String> myjList = new JList<String>(list);
	public StackPanel(ForthMachine fm) {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		myjList.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 22));
		myjList.setBackground(new Color(0, 255, 128));
		JViewport jvp = new JViewport();
		jvp.setView(myjList);
		this.setViewport(jvp);
		fm.AddStackUpdatedEventListener(this);
	}
	@Override
	public void onStackUpdated(Iterable<Object> stack) {
		list.removeAllElements();
		for (Object o : stack) {
			if (o instanceof Double) {
				if (((double) o % 1) == 0) {
					list.addElement(new DecimalFormat("0").format((double)o));
					continue;
				}
			}
			list.addElement(o.toString());
		}
		EventQueue.invokeLater(() -> {
			JScrollBar verticalScrollBar = this.getVerticalScrollBar();
			verticalScrollBar.setValue(verticalScrollBar.getMinimum());
		});
		this.repaint();
	}
}
