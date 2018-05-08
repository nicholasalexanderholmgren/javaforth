package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;

public class IdeWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private DictionaryPanel dictPanel;
	private TextEditorPanel textPanel;
	private StackPanel stackPanel;
	private TerminalPanel terminalPanel;
	private ForthMachine machine;
	public IdeWindow(ForthMachine machine) {
		super("Java Forth IDE");
		this.machine = machine;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		JPanel rootPanel = createRootPanel();
		this.add(rootPanel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(true);
	}
	private JPanel createRootPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		this.textPanel = new TextEditorPanel(machine);
		this.stackPanel = new StackPanel(machine);
		this.dictPanel = new DictionaryPanel(machine);
		this.terminalPanel = new TerminalPanel(machine, stackPanel);
		terminalPanel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED), new EtchedBorder()));
		JSplitPane stackAndDictionarySplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.stackPanel, this.dictPanel);
		JSplitPane editorAndStackDictionarySplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.textPanel, stackAndDictionarySplitPane);
		editorAndStackDictionarySplitPane.setDividerLocation(0.75);
		JSplitPane editorStackDictionaryAndTerminalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editorAndStackDictionarySplitPane, this.terminalPanel);
		editorStackDictionaryAndTerminalSplitPane.setDividerLocation(0.75);
		panel.add(editorStackDictionaryAndTerminalSplitPane, BorderLayout.CENTER);
		EventQueue.invokeLater(() -> {
			editorAndStackDictionarySplitPane.setDividerLocation(0.75);
			editorStackDictionaryAndTerminalSplitPane.setDividerLocation(0.75);
		});
		return panel;
	}
	public ForthMachine getMachine() {
		return machine;
	}
}
