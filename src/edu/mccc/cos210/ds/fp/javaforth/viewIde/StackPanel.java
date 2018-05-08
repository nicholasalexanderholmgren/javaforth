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
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthStack;
import edu.mccc.cos210.ds.fp.javaforth.util.ByteUtils;

@SuppressWarnings("serial")
public class StackPanel extends JScrollPane {
	private DefaultListModel<String> list = new DefaultListModel<String>();
	private JList<String> myjList = new JList<String>(list);
	private DisplayMode mode = DisplayMode.signedInts;
	public DisplayMode getMode() {
		return mode;
	}
	public void setMode(DisplayMode mode) {
		this.mode = mode;
	}
	public StackPanel(ForthMachine fm) {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		myjList.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 22));
		myjList.setBackground(new Color(0, 255, 128));
		JViewport jvp = new JViewport();
		jvp.setView(myjList);
		this.setViewport(jvp);
	}
	public void update(ForthStack fs) {
		list.removeAllElements();
		List<Byte> rawData = fs.asList();
		if (mode == DisplayMode.signedInts) {
			for (int i = 0; i < rawData.size(); i += 2) {
				list.addElement(ByteUtils.bytesToInt(rawData.get(i + 1), rawData.get(i)).toString());
			}
		}
		else {
			throw new UnsupportedOperationException();
		}
		EventQueue.invokeLater(() -> {
			JScrollBar verticalScrollBar = this.getVerticalScrollBar();
			verticalScrollBar.setValue(verticalScrollBar.getMaximum());
		});
		this.repaint();
	}
}
enum DisplayMode {
	hexBytes, signedInts, unsignedInts, signedDoubles, unsignedDoubles
}
