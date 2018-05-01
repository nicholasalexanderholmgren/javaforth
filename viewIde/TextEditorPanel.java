package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.BorderLayout;
import java.awt.Font;
<<<<<<< HEAD
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
=======
>>>>>>> master
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthMachine;

public class TextEditorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
<<<<<<< HEAD
	JTextArea textArea;
	JPanel contents;
	BreakPointManager breakPointHolder;
	ArrayList<Boolean> breakPointsModel;
	public TextEditorPanel() {
		super(new JTextArea(), 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		breakPointsModel = new ArrayList<>();
		textArea = (JTextArea)this.getViewport().getView(); 
		initConents();
	}
	public String getText() {
		return textArea.getText();
	}
	public String getTextToBreakpoint(int startPoint) {
		String lines[] = getText().split("\\r?\\n");
		StringBuilder sb = new StringBuilder();
		int index = 0;
		while(! breakPointsModel.get(index)) {
			sb.append(lines[index]);
		}
		return sb.toString();
	}
	private void initConents() {
		initTextArea();
		initBreakPointHolder();
	}
	private void initTextArea() {
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
					while(breakPointsModel.size() >= textArea.getLineCount()) {
						breakPointsModel.remove(breakPointsModel.size() - 1);
					}
					while(breakPointsModel.size() < textArea.getLineCount() + 1) {
						breakPointsModel.add(false);
					}
				breakPointHolder.update();
			}
		});
	}
	private void initBreakPointHolder() {
		breakPointHolder = new BreakPointManager();
		this.setRowHeaderView(breakPointHolder);
		breakPointHolder.setLayout(new BoxLayout(breakPointHolder, BoxLayout.Y_AXIS));
		breakPointHolder.add(Box.createHorizontalGlue());
		breakPointHolder.update();
	}
	private class BreakPointManager extends JPanel {
		private static final long serialVersionUID = 1L;
		private List<BreakPoint> bps = new ArrayList<>();
		public BreakPointManager() {
			super();
			this.setMinimumSize(new Dimension(5, 0));
			this.setVisible(true);
		}
		public void update() {
			
			while (bps.size() > textArea.getLineCount() + 1) {
				this.remove(bps.remove(bps.size()-1));
			}
			while (bps.size() < textArea.getLineCount() + 1) {
				int temp = bps.size();
				bps.add(new BreakPoint(temp));
			}
			int count =0;
			for (JComponent c : bps) {
				System.out.println(count);
				count++;
				c.setAlignmentX(Component.CENTER_ALIGNMENT);
				this.add(c);
			}
			repaint();
			System.out.println(breakPointsModel + " " + bps.size() + " " + textArea.getLineCount());
		}	
	}
	private class BreakPoint extends JPanel {
		private static final long serialVersionUID = -1484074903367026090L;
		int index;
		public BreakPoint(int index) {
			super();
			BreakPoint bp = this;
			this.index = index;
			this.setMinimumSize(new Dimension(breakPointHolder.getWidth(), 19));
			this.setMaximumSize(new Dimension(breakPointHolder.getWidth(), 19));
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (bp.contains(e.getPoint())) {
						breakPointsModel.set(index, ! breakPointsModel.get(index));
						breakPointHolder.update();
					}
				}
			});
		}
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			if(breakPointsModel.size() > index && breakPointsModel.get(index)) {
				g2d.setColor(Color.BLUE);
				g2d.fillOval(0, this.getHeight() / 4, breakPointHolder.getWidth(), breakPointHolder.getWidth());
				System.out.println("Breakpoint #" + index + " set to " + breakPointsModel.get(index));
			}
			g2d.setColor(Color.BLACK);
			g2d.drawRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);
		}
	}
=======
	public TextEditorPanel(ForthMachine machine) {
		int lineHeight = 16;
		final JTextPane textPane = new JTextPane();
		JScrollPane textScrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setLayout(new BorderLayout());
		this.add(textScrollPane, BorderLayout.CENTER);
		BreakPointPanel breakPointPanel = new BreakPointPanel(textPane.getStyledDocument(), lineHeight + 2);
		this.add(breakPointPanel, BorderLayout.LINE_START);
		textScrollPane.getVerticalScrollBar().addAdjustmentListener(breakPointPanel);
		StatusPanel statusPanel = new StatusPanel();
		new StatusPanelController(statusPanel, textPane.getStyledDocument(), machine);
		this.add(statusPanel, BorderLayout.PAGE_START);
		textPane.setFont(new Font(Font.SERIF, Font.PLAIN, lineHeight));
	}
	
>>>>>>> master
}
