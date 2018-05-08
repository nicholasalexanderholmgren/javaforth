package edu.mccc.cos210.bugatti;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MasterTrack {
	private Map<String, Point2D> theMap;
	private JPanel jp;
	public static void main(String... args) throws Exception {
		MasterTrack mt = new MasterTrack();
		mt.theMap = Collections.synchronizedMap(new HashMap<>());
		Shape track = TrackTest1.initTrack();
		EventQueue.invokeAndWait(() -> mt.initSwing(track));
		new Thread(() -> mt.beServer()).start();
	}
	private void beServer() {
		DatagramSocket sock;
		byte[] buffer = new byte[80];
		DatagramPacket pkt = new DatagramPacket(buffer, buffer.length);
		int grimReaper = 0;
		try {
			sock = new DatagramSocket(5972);
			while (true) {
				sock.receive(pkt);
                String message = new String(buffer, 0, pkt.getLength());
                String[] sa = message.split(" ");
                System.out.println("UDPServer: Message received = " + message);
                if (++grimReaper > 100) {
                	theMap.clear();
                	System.out.println("Harvest");
                	grimReaper = 0;
                }
                theMap.put(sa[0], new Point2D.Double(Double.parseDouble(sa[1]), Double.parseDouble(sa[2])));
				pkt.setLength(buffer.length);
				jp.repaint();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	protected void initSwing(Shape track) {
		JFrame jf = new JFrame("Master Track");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp = new MyJPanel(track);
		jf.add(jp, BorderLayout.CENTER);
		jf.setSize(1024, 768);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	private class MyJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private final Ellipse2D e2d = new Ellipse2D.Double(-100.0, -100.0, 200.0, 200.0);
		private Shape track;
		public MyJPanel(Shape t) {
			this.track = t;
			setBackground(new Color(0, 120, 64));
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			AffineTransform gat = new AffineTransform();
			gat.translate(getWidth() - getWidth() / 4.0, getHeight() / 2.0);
			gat.scale(0.1, -0.1);
			g2d.transform(gat);
			g2d.setPaint(Color.BLACK);
			g2d.fill(track);
			Rectangle2D r2d = new Rectangle2D.Double(-110.0, -35.0, 230.0, 50.0);
			g2d.setColor(Color.YELLOW);
			g2d.fill(r2d);
			g2d.setStroke(new BasicStroke(1.0f));
			g2d.draw(r2d);
			for (Point2D p2d : theMap.values()) {
				AffineTransform at = AffineTransform.getTranslateInstance(p2d.getX(), p2d.getY());
				Shape s = at.createTransformedShape(e2d);
				g2d.setColor(Color.GREEN);
				g2d.fill(s);
			}
			g2d.dispose();
		}
	}
}