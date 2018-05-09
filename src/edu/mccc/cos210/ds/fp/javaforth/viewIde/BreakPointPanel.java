package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledDocument;
/**
 * Class for containing and updating the region where breakpoints are held. 
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
@SuppressWarnings("serial")
public class BreakPointPanel extends JPanel implements DocumentListener, MouseListener, AdjustmentListener {
	// AdjustmentListener Added to scroll bar of text editor.
	
	private StyledDocument document;
	private int lineHeight;
	private int margin = 2;
	private int topMargin = 6;
	private int currentScrollBarValue = 0;
	public BreakPointPanel(StyledDocument styledDocument, int lineHeight) {
		int width = lineHeight + this.margin * 2;
		this.setMinimumSize(new Dimension(width, 0));
		this.setPreferredSize(new Dimension(width, 0));
		this.document = styledDocument;
		this.document.addDocumentListener(this);
		this.addMouseListener(this);
		this.lineHeight = lineHeight;
	}
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		this.repaint();
	}
	@Override
	public void insertUpdate(DocumentEvent arg0) {
		this.repaint();
	}
	@Override
	public void removeUpdate(DocumentEvent arg0) {
		this.repaint();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		int lineNumber = (((arg0.getY() + this.currentScrollBarValue) - topMargin) 
				/ (this.lineHeight + this.margin * 2)) + 1;
		int currentLine = 1;
		for (int i = 0; i < this.document.getLength(); i++) {
			int nextLineStartIndex = DocumentUtilities.getNextLineStartIndex(this.document, i);
			if (currentLine == lineNumber) {
				if (DocumentUtilities.isBreakPoint(this.document, i)) {
					this.document.setCharacterAttributes(i, nextLineStartIndex - i, 
							DocumentUtilities.DisableBreakPointAttribute, false);
				}
				else {
					this.document.setCharacterAttributes(i, nextLineStartIndex - i, 
							DocumentUtilities.BreakPointAttribute, false);					
				}
				break;
			}
			i = nextLineStartIndex;
			currentLine++;
		}
		this.repaint();
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		int currentLine = 1;
		for (int i = 0; i < this.document.getLength(); i++) {
			javax.swing.text.AttributeSet attributes = this.document.getCharacterElement(i).getAttributes();
			Object value = attributes.getAttribute(DocumentUtilities.BreakpointAttributeName);
			if (attributes != null && value != null && (int) value == 
					DocumentUtilities.BreakpointEnabledAttributeValue) {
				this.paintBreakPoint(currentLine, g2d);
			}
			i = DocumentUtilities.getNextLineStartIndex(this.document, i);
			paintLineNumber(currentLine, g2d);
			currentLine++;
		}
		g2d.dispose();
	}
	//Added to pain line numbers in the text panel
	private void paintLineNumber(int lineNumber , Graphics g2d) {
		g2d.setColor(Color.RED);
		g2d.drawString(Integer.toString(lineNumber) , this.margin + 5 , topMargin + (lineNumber - 1) * (this.lineHeight + this.margin * 2) + this.margin - this.currentScrollBarValue + 13);
	}
	private void paintBreakPoint(int lineNumber, Graphics2D g2d) {
		int circleSize = this.lineHeight - 2;
		g2d.setColor(Color.ORANGE);
		g2d.fillOval(this.margin, 
				topMargin + (lineNumber - 1) * (this.lineHeight + this.margin * 2) + this.margin - this.currentScrollBarValue, 
				circleSize, circleSize);
	}
	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		this.currentScrollBarValue = arg0.getValue();
		this.repaint();
	}
}
