package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
//import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;

public class TerminalPanel extends JScrollPane {
	//JTextArea inputTextArea;
	//JComponent outputTextArea;
	JTextArea textArea;
	String input;
	JComponent contents;
	public TerminalPanel(ForthMachine machine , StackPanel sp) {
		super(new JTextArea(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.textArea = (JTextArea) this.getViewport().getView();
		this.textArea.setTabSize(4);
		this.textArea.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
		this.textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					//Need to find a way to do this line by line
					input = textArea.getText();
					if(input.length()>0) {
						machine.interpret(input);
					}
					textArea.setText("");
					System.out.println("Your string is " + input);
					sp.paintAll(getGraphics());
					textArea.moveCaretPosition(0);
				}
			}
		});
	}
	public void getStatus() {
		
	}
	public void update(String status) {
	}
	public String getTerminalInput() {
		return input;
	}
}
