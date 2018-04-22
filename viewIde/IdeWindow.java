package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import edu.mccc.cos210.ds.fp.javaforth.util.IObserver;
import edu.mccc.cos210.ds.fp.javaforth.util.ISubject;

public class IdeWindow extends JFrame implements IObserver {
	private DictionaryPanel dictPanel;
	private TextEditorPanel textPanel;
	private StackPanel stackPanel;
	private TerminalPanel terminalPanel;
	public IdeWindow() {
		super("Java Forth IDE");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel rootPanel = createRootPanel();
		
		this.add(rootPanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(true);
	}
	private JPanel createRootPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setPreferredSize(new Dimension(1000, 1000));
		panel.setBackground(Color.BLACK);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
    	c.weightx = 0.66;
    	c.weighty = 0.66;
    	c.gridx = 0;
    	c.gridy = 0;
    	c.gridheight = 2;
		this.textPanel = new TextEditorPanel();
		panel.add(
			this.textPanel,
			c
		);
		c.weightx = 0.33;
		c.weighty = 0.33;
		c.gridx = 2;
    	c.gridy = 0;
    	c.gridheight = 1;
		this.stackPanel = new StackPanel();
		stackPanel.setBorder(
			new CompoundBorder(
				new BevelBorder(BevelBorder.RAISED),
				new EtchedBorder()
			)
		);
		panel.add(
			stackPanel,
			c
		);
		c.weightx = 0.33;
		c.weighty = 0.33;
		c.gridx = 2;
    	c.gridy = 1;
		this.dictPanel = new DictionaryPanel();
		dictPanel.setBorder(
			new CompoundBorder(
				new BevelBorder(BevelBorder.RAISED),
				new EtchedBorder()
			)
		);
		panel.add(
			dictPanel,
			c
		);
		c.weightx = 1;
		c.weighty = 0.33;
		c.gridx = 0;
    	c.gridy = 2;
    	c.gridwidth = 3;
		this.terminalPanel = new TerminalPanel(dictPanel);
		terminalPanel.setBorder(
			new CompoundBorder(
				new BevelBorder(BevelBorder.RAISED),
				new EtchedBorder()
			)
		);
		panel.add(
			terminalPanel,
			c
		);
		return panel;
	}

	@Override
	public void update(ISubject s) {
		
	}
}
