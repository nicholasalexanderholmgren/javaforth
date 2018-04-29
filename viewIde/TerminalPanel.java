package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import edu.mccc.cos210.ds.fp.javaforth.machineModel.*;

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
	String input;
	public TerminalPanel(ForthMachine machine , StackPanel sp) {
		super(new JTextArea(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.textArea = (JTextArea) this.getViewport().getView();
		this.textArea.setTabSize(4);
		this.textArea.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
		this.textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					//Need to find a way to do this line by line
					input = textArea.getText();
					machine.interpret(input);
					System.out.println("Your string is " + input);
					sp.paintAll(getGraphics());
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
