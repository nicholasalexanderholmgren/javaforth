package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class TerminalPanel extends JScrollPane {
	JTextArea textArea;
	public TerminalPanel(DictionaryPanel dp) {
		super(new JTextArea(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.textArea = (JTextArea) this.getViewport().getView();
		this.textArea.setTabSize(4);
		this.textArea.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
		this.textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					//System.out.println(textArea.getText());
					dp.getTextArea().setText(textArea.getText());
				}
			}
		});
	}
	public void getStatus() {
		
	}
}
