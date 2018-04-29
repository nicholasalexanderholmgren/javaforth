package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextEditorPanel extends JScrollPane {
	private static final long serialVersionUID = 1L;
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
			public void keyPressed(KeyEvent arg0) {
					while(breakPointsModel.size() > textArea.getLineCount()) {
						breakPointsModel.remove(breakPointsModel.size() - 1);
					}
					while(breakPointsModel.size() < textArea.getLineCount()) {
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
	}
	private class BreakPointManager extends JPanel {
		private static final long serialVersionUID = 1L;
		private List<BreakPoint> bps = new ArrayList<>();
		public BreakPointManager() {
			super();
			this.setMinimumSize(new Dimension(5, 0));
		}
		public void update() {
			System.out.println(breakPointsModel);
			while (bps.size() > textArea.getLineCount() + 1) {
				bps.remove(bps.size()-1);
			}
			while (bps.size() < textArea.getLineCount() + 1) {
				int temp = bps.size();
				bps.add(new BreakPoint(temp));
			}
			for (JComponent c : bps) {
				c.setAlignmentX(Component.CENTER_ALIGNMENT);
				this.add(c);
			}
			repaint();
		}	
	}
	private class BreakPoint extends JPanel {
		private static final long serialVersionUID = -1484074903367026090L;
		int index;
		public BreakPoint(int index) {
			super();
			BreakPoint bp = this;
			this.index = index;
			this.setMinimumSize(new Dimension(breakPointHolder.getWidth(), textArea.getFont().getSize() * 19));
			this.setMaximumSize(new Dimension(breakPointHolder.getWidth(), textArea.getFont().getSize() * 19));
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
				g2d.fillOval(0, 0, 8, 8);
				System.out.println("Breakpoint #" + index + " set to " + breakPointsModel.get(index));
			}
			g2d.setColor(Color.BLACK);
			g2d.drawRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);
		}
	}
}
