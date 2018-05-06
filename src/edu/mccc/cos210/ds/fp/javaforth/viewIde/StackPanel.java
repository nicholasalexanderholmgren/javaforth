package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Color;
import java.awt.Font;
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
	private JList<String> myjList = new JList<String>(list);
	private List<Byte> rawData;
	private DisplayMode mode;
	public StackPanel(ForthMachine fm) {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		rawData = new ArrayList<>();
		mode = DisplayMode.signedInts;
		myjList.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 22));
		myjList.setBackground(new Color(0,255,128));
		JViewport jvp = new JViewport();
		jvp.setView(myjList);
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
				list.addElement(ByteUtils.bytesToInt(rawData.get(i + 1), rawData.get(i)).toString());
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
