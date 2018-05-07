package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;


import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;


public class DictionaryPanel extends JScrollPane {
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> list = new DefaultListModel<String>();

	Map<String,String> dictMap;
	JTextArea textArea;
	JTable table;
	public DictionaryPanel(ForthMachine fm) {
		super(new JScrollPane(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		dictMap = fm.getDictionaryAsMap();
//		this.fillDict(dictMap);
		table = buildTable(dictMap);
		table.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 18));
		table.setGridColor(new Color(0,255,128));
		this.setViewportView(table);
//		JViewport jvp = new JViewport();
//		jvp.setView(new JList<String>(list));
//		jvp.setView(table);
//		this.setViewport(jvp);
	}
	public JTextArea getTextArea() {
		return this.textArea;
	}
	public void fillDict(Map<String, String> dictMap) {
		for (Map.Entry<String, String> entry : dictMap.entrySet())
		{	
			list.addElement(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	public void update(Map< String, String> dictMap) {
		list.removeAllElements();
		fillDict(dictMap);
	}
	
	private JTable buildTable(Map<String, String> dict){
		dict = this.dictMap;
		String columnNames[] = { "WORD", "DEFINITION" };
		Object[][] data = new Object[dict.size()][2];
		int counter = 0;
		for(Map.Entry<String, String> entry : dict.entrySet()) {
		  data[counter][0] = entry.getKey();
		  data[counter][1] = entry.getValue();
		  counter++;
		}
		JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(table.getWidth() * 2, table.getHeight()));
		table.setRowHeight(22);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(1000);
		table.setPreferredScrollableViewportSize(super.getPreferredSize());
		TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.LEFT);	
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		return table;
	}
}
