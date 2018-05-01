package edu.mccc.cos210.ds.fp.javaforth.viewIde;

<<<<<<< HEAD
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import javax.swing.JPanel;
=======
import javax.swing.DefaultListModel;
import javax.swing.JList;
>>>>>>> master
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthStack;

@SuppressWarnings("serial")
public class StackPanel extends JScrollPane {
<<<<<<< HEAD
	
	private Timer timer = new Timer();
	private IdeWindow parent;
	private MyPanel panel;
	public StackPanel(IdeWindow parent) {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.panel = (MyPanel) this.getViewport().getView();
		this.parent = parent;
		System.out.println("test");
//		this.panel.setBackground(Color.RED);
=======
	private DefaultListModel<String> list = new DefaultListModel<String>();
	public StackPanel(ForthMachine fm) {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		System.out.println("test");
>>>>>>> master
		JViewport jvp = new JViewport();
		jvp.setView(new JList<String>(list));
		this.setViewport(jvp);
<<<<<<< HEAD
		this.panel = (MyPanel) this.getViewport().getView();	
	}
	public void update() {
		//System.out.println("I work");
		panel.paintAll(getGraphics());
	}
	public class MyPanel extends JPanel {
		MyPanel() {
			super();
		}
		@Override
		public void paintComponent(Graphics g) {
			String stackData = parent.getMachine().getStackAsString();
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(Color.black);
			g2d.setBackground(Color.orange);
			g2d.drawString(stackData, 50, 50);
			//g2d.fill(new Rectangle2D.Double(0.0, 0.0, this.getWidth(), this.getHeight()));
		}
	}
	private void paintSelf(Graphics2D g2d, String s, int fontSize) {
	g2d.setPaint(new Color(130, 130, 130));
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			AffineTransform fat = new AffineTransform();
			fat.setToScale(1.0, 1.0);
			fat.translate(0, 28);
			g2d.setFont(new Font(Font.SERIF, Font.BOLD, fontSize).deriveFont(fat));
			g2d.setPaint(Color.BLACK);
			g2d.drawString(
				s,
			    200,
				150
			);
		}

}
=======
	}
	public void update(ForthStack fs) {
		list.removeAllElements();
		list.addElement(fs.toString());
		// TODO
	}
}
>>>>>>> master
