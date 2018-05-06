package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthStack;
import edu.mccc.cos210.ds.fp.javaforth.util.ByteUtils;

@SuppressWarnings("serial")
public class StackPanel extends JScrollPane {
	private DefaultListModel<String> list = new DefaultListModel<String>();
	private List<Byte> rawData;
	private DisplayMode mode;
	public StackPanel(ForthMachine fm) {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		rawData = new ArrayList<>();
		mode = DisplayMode.signedInts;
		JViewport jvp = new JViewport();
		jvp.setView(new JList<String>(list));
		this.setViewport(jvp);
		
	}
	public void update(ForthStack fs) {
		rawData.removeAll(rawData);
		for(Byte b : fs.asList()) {
			rawData.add(b);
		}
		this.repaint();
		// TODO
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		list.removeAllElements();
		switch(mode) {
		case signedInts:
			for(int i = 0; i<rawData.size(); i+=2) {
				list.addElement(ByteUtils.bytesToInt(rawData.get(i), rawData.get(i + 1)).toString());
			}
			break;
		default:
			break;
		}
	}
	private enum DisplayMode{
		hexBytes, signedInts, unsignedInts, signedDoubles, unsignedDoubles
	}
}
