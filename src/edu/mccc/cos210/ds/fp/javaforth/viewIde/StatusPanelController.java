package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ICanExecuteChangedEventListener;

public class StatusPanelController implements ICanExecuteChangedEventListener{
	private final SimpleAttributeSet highlightAttribute;
	private final SimpleAttributeSet disableHighlightAttribute;
	private int currentLineNumber = 1;
	private StatusPanel statusPanel;
	public StatusPanelController(StatusPanel statusPanel, StyledDocument document, ForthMachine machine) {
		highlightAttribute = new SimpleAttributeSet();
		highlightAttribute.addAttribute(StyleConstants.Background, Color.yellow);
		disableHighlightAttribute = new SimpleAttributeSet();
		disableHighlightAttribute.addAttribute(StyleConstants.Background, Color.white);
		this.statusPanel = statusPanel;
		statusPanel.getStartButton().addActionListener(a -> {
			((JButton) a.getSource()).setText("Continue");
			boolean skipBreakpoint = true;
			for (int i = DocumentUtilities.getLineStartIndexByLineNumber(document, currentLineNumber); i < document.getLength(); i++) {
				if (DocumentUtilities.isBreakPoint(document, i) && !skipBreakpoint) {
					this.highlightLine(document, i);
					return;
				}
				int lineEnd = this.processLine(document, i, machine);
				if (lineEnd == -1) {
					break;
				}
				i = lineEnd;
				skipBreakpoint = false;
			}
			// Ran to completion
			this.reset(document);
		});
		statusPanel.getStepButton().addActionListener(a -> {
			int lineStart = DocumentUtilities.getLineStartIndexByLineNumber(document, currentLineNumber);
			if (lineStart == -1) {
				this.reset(document);
				return;
			}
			this.processLine(document, lineStart, machine);
			this.highlightLine(document, DocumentUtilities.getNextLineStartIndex(document, lineStart) + 1);
		});
		statusPanel.getPauseButton().addActionListener(a -> {
			machine.halt();
		});
		statusPanel.getStopButton().addActionListener(a -> {
			this.reset(document);
		});
	}
	private void highlightLine(StyledDocument document, int lineStart) {
		this.clearHighlight(document);
		int nextLineIndex = DocumentUtilities.getNextLineStartIndex(document, lineStart);
		if (nextLineIndex != -1) {
			document.setCharacterAttributes(lineStart, nextLineIndex - lineStart, this.highlightAttribute, false);
		}
	}
	private void clearHighlight(StyledDocument document) {
		document.setCharacterAttributes(0, document.getLength(), this.disableHighlightAttribute, false);
	}
	private int processLine(StyledDocument document, int i, ForthMachine machine) {
		if (i == 0) {
			this.statusPanel.getStartButton().setText("Continue");
		}
		int lineEnd = DocumentUtilities.getNextLineStartIndex(document, i);
		if (lineEnd != -1) {
			try {
				String forthCode = document.getText(i, lineEnd - i);
				machine.interpret(forthCode, true);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			currentLineNumber++;
		}
		return lineEnd;
	}
	private void reset(StyledDocument document) {
		this.currentLineNumber = 1;
		this.clearHighlight(document);
		this.statusPanel.getStartButton().setText("Start");
	}
	@Override
	public void onCanExecuteChanged(boolean canExecute) {
		statusPanel.getStartButton().setEnabled(canExecute);
		statusPanel.getStepButton().setEnabled(canExecute);
		statusPanel.repaint();
	}
}
