package edu.mccc.cos210.ds.fp.javaforth.viewIde;


import java.awt.Font;
import java.awt.event.KeyListener;
import java.util.Map;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class DictionaryPanel extends JScrollPane {
	JTextArea textArea;
	public DictionaryPanel() {
		super(new JTextArea(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.textArea = (JTextArea) this.getViewport().getView();
		this.textArea.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
		System.out.println(this.textArea.getKeyListeners().length);
		for (KeyListener kr : this.textArea.getKeyListeners()) {
			this.textArea.removeKeyListener(kr);
		}
	}
	public JTextArea getTextArea() {
		return this.textArea;
	}
	public void update(Map< String, String> dict) {
		
	}
}
