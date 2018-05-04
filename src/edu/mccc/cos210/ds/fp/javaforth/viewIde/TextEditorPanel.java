package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;

public class TextEditorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public TextEditorPanel(ForthMachine machine) {
		int lineHeight = 16;
		final JTextPane textPane = new JTextPane();
		JScrollPane textScrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setLayout(new BorderLayout());
		this.add(textScrollPane, BorderLayout.CENTER);
		BreakPointPanel breakPointPanel = new BreakPointPanel(textPane.getStyledDocument(), lineHeight + 2);
		this.add(breakPointPanel, BorderLayout.LINE_START);
		textScrollPane.getVerticalScrollBar().addAdjustmentListener(breakPointPanel);
		StatusPanel statusPanel = new StatusPanel();
		new StatusPanelController(statusPanel, textPane.getStyledDocument(), machine);
		this.add(statusPanel, BorderLayout.PAGE_START);
		textPane.setFont(new Font(Font.SERIF, Font.PLAIN, lineHeight));
	}
	
}
