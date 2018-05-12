package edu.mccc.cos210.ds.fp.javaforth.view_ideee;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import edu.mccc.cos210.ds.fp.javaforth.machine_model.ForthMachine;
import edu.mccc.cos210.ds.fp.javaforth.machine_model.ITerminalUpdatedEventListener;
/**
 * Class for displaying the Read-Evaluate-Print-Loop. Can take user input and pass it to the forth machine.
 * 
 * @author Nicholas Holmgren, Jing-Chao Feng, Ryan Hammound
 *
 */
public class TerminalPanel extends JScrollPane implements ITerminalUpdatedEventListener {
	private static final long serialVersionUID = 1L;
	ForthMachine machine;
	String input;
	JComponent contents;
	JTextArea outputTextArea;
	JScrollBar vertical;
	JPanel fullText;
	public TerminalPanel(ForthMachine machine, StackPanel sp) {
		super(null, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		fullText = createInputAndOutput();
		this.setViewportView(fullText);
		vertical = this.getVerticalScrollBar();
		this.machine = machine;
		machine.AddTerminalUpdatedEventListener(this);
	}
	/**
	 * Method for retriveing user input
	 * @return String - current contents of the user input text area.
	 */
	public String getTerminalInput() {
		return input;
	}
	/**
	 * Helper method for creating the input text and output text area and wrapping them in a JPanel;
	 */
	private JPanel createInputAndOutput() {
		//CHANGES
		String arrow = ">";
		JPanel ioRegion;
		JTextArea inputArea;
		inputArea = new JTextArea();
		inputArea.setTabSize(4);
		//CHANGES
		inputArea.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 22));
		inputArea.setBackground(new Color(0, 100, 0));
		inputArea.setCaretColor(new Color(255, 51, 51));
		inputArea.setSelectedTextColor(new Color(0, 255, 0));
		inputArea.setForeground(new Color(0, 255, 0));
		inputArea.append(arrow);
		inputArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER && inputArea.hasFocus()) {
					if (inputArea.getText().length() > 2) {
						String input = inputArea.getText().substring(inputArea.getText().indexOf(">") + 1);
						appendOutput(input);
						machine.interpret(input, false);
						inputArea.setText(arrow);
						inputArea.moveCaretPosition(1);
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent ke) {
				if (inputArea.getText().length() < 2) {
					inputArea.setText(arrow);
					inputArea.moveCaretPosition(1);
				}
			}
		});
		inputArea.setWrapStyleWord(true);
		outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		outputTextArea.setTabSize(4);
		//CHANGED
		outputTextArea.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 22));
		outputTextArea.setWrapStyleWord(true);
		outputTextArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
		outputTextArea.setBackground(new Color(0, 100, 0));
		outputTextArea.setCaretColor(new Color(255, 51, 51));
		outputTextArea.setSelectedTextColor(new Color(50, 205, 50));
		outputTextArea.setForeground(new Color(0, 255, 0));
		ioRegion = new JPanel();
		ioRegion.setLayout(new BoxLayout(ioRegion, BoxLayout.Y_AXIS));
		ioRegion.add(outputTextArea);
		ioRegion.add(inputArea);
		ioRegion.doLayout();
		return ioRegion;
	}
	private void appendOutput(String message) {
		if (message != null) {
			outputTextArea.append(message.trim() + "\n");
			EventQueue.invokeLater(() -> vertical.setValue(vertical.getMaximum()));
		}
	}
	/**
	 * Method for retrieving the output of the forth machines evaluations.
	 * 
	 * @param clear - Whether the output area should be cleared before displaying the new message.
	 * @param message - the message to be displayed in the output area. Appending to the current contents if clear is false.
	 */
	@Override
	public void onTerminalUpdated(boolean clear, String message) {
		if (clear) {
			this.outputTextArea.setText("");
		}
		this.appendOutput(message);
	}
}
