package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;
import edu.mccc.cos210.ds.fp.javaforth.util.ByteUtils;
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
	}
	@Override
	public void onStackUpdated(Iterable<Object> stack) {
		list.removeAllElements();
		// TODO
		EventQueue.invokeLater(() -> {
			JScrollBar verticalScrollBar = this.getVerticalScrollBar();
			verticalScrollBar.setValue(verticalScrollBar.getMaximum());
		});
		this.repaint();
	}
}