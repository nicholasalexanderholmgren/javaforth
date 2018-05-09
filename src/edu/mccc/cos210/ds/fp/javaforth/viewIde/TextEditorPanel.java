package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;

public class TextEditorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final JTextPane textPane = new JTextPane();
	public TextEditorPanel(ForthMachine machine) {
		int lineHeight = 16;
		JScrollPane textScrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setLayout(new BorderLayout());
		this.add(textScrollPane, BorderLayout.CENTER);
		BreakPointPanel breakPointPanel = new BreakPointPanel(textPane.getStyledDocument(), lineHeight + 2);
		breakPointPanel.setPreferredSize(new Dimension(35, 50));
		this.add(breakPointPanel, BorderLayout.LINE_START);
		textScrollPane.getVerticalScrollBar().addAdjustmentListener(breakPointPanel);
		StatusPanel statusPanel = new StatusPanel();
		new StatusPanelController(statusPanel, textPane.getStyledDocument(), machine);
		this.add(statusPanel, BorderLayout.PAGE_START);
		textPane.setFont(new Font(Font.SERIF, Font.PLAIN, lineHeight));
	}
	/**
	 * 
	 * @return String - the contents of the text area of this panel. Useful for saving.
	 */
	public String getTextContents() {
		return textPane.getText();
	}
	/**
	 * Method for loading text into the text editor. Useful for loading files.
	 * @param s - the new text to load into the text area
	 */
	public void setTextContents(String s) {
		textPane.setText(s);
	}
}
