package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;

public class TerminalPanel extends JScrollPane {
	ForthMachine machine;
	String input;
	JComponent contents;
	JTextArea outputTextArea;
	public TerminalPanel(ForthMachine machine , StackPanel sp) {
		super(null, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setViewportView(createInputAndOutput());
		this.machine = machine;
	}
	public void getStatus() {
		
	}
	public void update(String status) {
		outputTextArea.append(status);
	}
	public String getTerminalInput() {
		return input;
	}
	/**
	 * Helper method for creating the input text and output text area and wrapping them in a JPanel;
	 */
	private JPanel createInputAndOutput() {
		JPanel ioRegion;
		JTextArea inputArea;
		inputArea = new JTextArea();
		inputArea.setPreferredSize(this.getSize());
		inputArea.setTabSize(4);
		inputArea.setFont(new Font(Font.SERIF,Font.PLAIN, 16));
		inputArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER && inputArea.hasFocus()) {
					//Need to find a way to do this line by line
					String input = inputArea.getText();
					if(input.length()>0) {
						machine.interpret(input);
					}
					inputArea.setText("");
					//System.out.println("Your string is " + input);
					inputArea.moveCaretPosition(0);
					outputTextArea.setSize((int) outputTextArea.getSize().getWidth(), 0);
					outputTextArea.append("\t"+ input);
				}
			}
		});
		outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		outputTextArea.setTabSize(4);
		outputTextArea.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
		outputTextArea.setSize((int) outputTextArea.getSize().getWidth(), 0);
		outputTextArea.setWrapStyleWord(true);
		ioRegion = new JPanel();
		ioRegion.setLayout(new BoxLayout(ioRegion, BoxLayout.Y_AXIS));
		ioRegion.add(outputTextArea);
		ioRegion.add(inputArea);
		ioRegion.doLayout();
		return ioRegion;
	}
}
