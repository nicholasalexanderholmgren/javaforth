package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;
import edu.mccc.cos210.ds.IMap;
import edu.mccc.cos210.ds.Map;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.IDictionaryUpdatedEventListener;
/**
 * Class for managing the display of a table containing the dictionary element names as the first element of each row and the 
 * descriptions of those dictionary elements in the second element of that row.
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public class DictionaryPanel extends JScrollPane implements IDictionaryUpdatedEventListener {
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> list = new DefaultListModel<String>();
	Map<String, String> dictMap = new edu.mccc.cos210.ds.Map<>();
	JTextArea textArea;
	JTable table;
	/**
	 * Constructor method. Initializes the contents of the panel and subscribes the the given forth machine's dictionary update
	 * events.
	 * @param fm - the machine to subscribe to.
	 */
	public DictionaryPanel(ForthMachine fm) {
		super(new JScrollPane(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table = buildTable(dictMap);
		table.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 18));
		table.setGridColor(new Color(0, 255, 128));
		this.setViewportView(table);
		fm.AddDictionaryUpdatedEventListener(this);
	}
	private JTextArea getTextArea() {
		return this.textArea;
	}
	private void fillDict(Map<String, ForthWordBase> dictMap) {
		this.dictMap = new Map<>();
		for (String s : dictMap.keySet()) {
			this.dictMap.put(s, dictMap.get(s).getDescription());
		}
	}
	private JTable buildTable(Map<String, String> dict) {
		dict = this.dictMap;
		String columnNames[] = { "WORD", "DEFINITION" };
		Object[][] data = new Object[dict.getSize()][2];
		int counter = 0;
		for (IMap.Entry<String, String> entry : dict) {
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
	private final Object updateOperationLock = new Object();
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDictionaryUpdated(edu.mccc.cos210.ds.Map<String, ForthWordBase> entries) {
		// Cannot call invokeAndWait because it might come from the UI thread.
		EventQueue.invokeLater(() -> {
			synchronized (updateOperationLock) {
				list.removeAllElements();
				fillDict(entries);
				this.setViewportView(buildTable(dictMap));
			}
		});
	}
}
