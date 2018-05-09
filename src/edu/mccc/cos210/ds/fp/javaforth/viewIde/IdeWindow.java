package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;
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
		MyMenuBar jmb = new MyMenuBar();
		setJMenuBar(jmb);
		jmb.init();
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
	@SuppressWarnings("serial")
	private class MyMenuBar extends JMenuBar {
		JMenu file;
		Action save, open, saveAs, quit;
		String priorSave;
		String fileName;
		JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
		public MyMenuBar() {
			super();
			dialog.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File arg0) {
					if (arg0.isDirectory()) {
						return true;
					}
					return arg0.getName().endsWith(".fs");
				}
				@Override
				public String getDescription() {
					return "Forth Script Files";
				}
			});
		}
		public void init() {
			initFileMenu();
			initEditMenu();
		}
		private void initFileMenu() {
			this.file = new JMenu();
			this.file.setText("File");
			initSave();
			initSaveAs();
			initOpen();
			initQuit();
			this.add(file);
		}
		private void initSave() {
			save = new AbstractAction("Save") {
				private static final long serialVersionUID = 1L;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (!textPanel.getTextContents().equals(priorSave)) {
						save();
					}
				}
			};
			file.add(save);
		}
		private void initSaveAs() {
			saveAs = new AbstractAction("Save As") {
				private static final long serialVersionUID = 1L;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (!textPanel.getTextContents().equals(priorSave)) {
						saveAs();
					}
				}
			};
			file.add(saveAs);
		}
		private void saveAs() {
			if (dialog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				fileName = dialog.getSelectedFile().getAbsolutePath();
				if (!fileName.endsWith(".fs")) {
					fileName = fileName.concat(".fs");
				}
				save();
			}
		}
		private void save() {
			if (fileName == null) {
				saveAs();
				return;
			}
			if (!textPanel.getTextContents().equals(priorSave)) {
				try {
					FileWriter writer = new FileWriter(fileName);
					writer.write(textPanel.getTextContents());
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		private void initOpen() {
			open = new AbstractAction("Open") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					open();
				}
			};
			file.add(open);
		}
		private void open() {
			if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedReader reader = new BufferedReader(new FileReader(dialog.getSelectedFile()));
					StringBuilder sb = new StringBuilder();
					while (reader.ready()) {
						sb.append(reader.readLine() + "\n");
					}
					reader.close();
					textPanel.setTextContents(sb.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		private void initQuit() {
			file.add("Quit");
		}
		private void initEditMenu() {
		}
	}
}
