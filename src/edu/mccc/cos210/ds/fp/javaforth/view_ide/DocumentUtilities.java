package edu.mccc.cos210.ds.fp.javaforth.view_ide;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

public class DocumentUtilities {
	public static final SimpleAttributeSet BreakPointAttribute;
	public static final SimpleAttributeSet DisableBreakPointAttribute;
	public static final int BreakpointAttributeName = 754983202;
	public static final int BreakpointEnabledAttributeValue = 81923748;
	static {
		BreakPointAttribute = new SimpleAttributeSet();
		BreakPointAttribute.addAttribute(BreakpointAttributeName, BreakpointEnabledAttributeValue);
		DisableBreakPointAttribute = new SimpleAttributeSet();
		DisableBreakPointAttribute.addAttribute(BreakpointAttributeName, 0);
	}
	public static int getNextLineStartIndex(StyledDocument document, int index) {
		try {
			while (index < document.getLength() && !document.getText(index, 1).equals("\n")) {
				index++;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return index;
	}
	public static int getLineStartIndexByLineNumber(StyledDocument document, int lineNumber) {
		int currentLine = 1;
		for (int i = 0; i < document.getLength(); i++) {
			int nextLineStartIndex = DocumentUtilities.getNextLineStartIndex(document, i);
			if (currentLine == lineNumber) {
				return i;
			}
			i = nextLineStartIndex;
			currentLine++;
		}
		return -1;
	}
	public static boolean isBreakPoint(StyledDocument document, int charIndex) {
		javax.swing.text.AttributeSet attributes = document.getCharacterElement(charIndex).getAttributes();
		Object value = attributes.getAttribute(DocumentUtilities.BreakpointAttributeName);
		return value != null && (int)value == DocumentUtilities.BreakpointEnabledAttributeValue;
	}
}
