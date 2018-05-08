package edu.mccc.cos210.bugatti;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TrackTest1 {
	private static final double[] ON_TRACK_SPEED = new double[] { 8.0, 12.0, 16.0, 24.0, 32.0 };
	private static final double OUT_OF_BOUNDS_SPEED = 4.0;
	private static final double TURN_RATE_IN_RADIANS = Math.toRadians(120.0);
	private static final int MAX_GEAR = ON_TRACK_SPEED.length - 1;
	private final Ellipse2D marker = new Ellipse2D.Double(-16.0, -16.0, 32.0, 32.0);
	private Point2D currentPosition = new Point2D.Double();
	private double currentSpeed = 0.0;
	private double currentHeadingInRadians = Math.PI / 2.0;
	private int currentGear = 0;
	private double turnRateInRadians = 0.0;
	private boolean racing = false;
	private boolean outOfBounds = true;
	public static void main(String... args) {
		TrackTest1 tt = new TrackTest1();
		Shape track = initTrack();
		EventQueue.invokeLater(() -> tt.initSwing(track));
	}
	static Shape initTrack() {
		Path2D path = new Path2D.Double();
		Path2D centerPath = new Path2D.Double();
		Path2D innerPath = new Path2D.Double();
		Path2D outerPath = new Path2D.Double();
		centerPath.moveTo(0.0, 0.0);
		centerPath.lineTo(2.0, 0.0);
		centerPath.quadTo(3.0, 0.0, 3.0, 1.0);
		centerPath.lineTo(3.0, 4.0);
		centerPath.quadTo(3.0, 5.0, 2.0, 5.0);
		centerPath.quadTo(1.0, 5.0, 1.0, 4.0);
		centerPath.lineTo(1.0, 3.0);
		centerPath.quadTo(1.0, 2.0, 0.0, 2.0);
		centerPath.quadTo(-1.0, 2.0, -1.0, 3.0);
		centerPath.lineTo(-1.0, 4.0);
		centerPath.quadTo(-1.0, 5.0, -2.0, 5.0);
		centerPath.quadTo(-3.0, 5.0, -3.0, 4.0);
		centerPath.lineTo(-3.0, 1.0);
		centerPath.quadTo(-3.0, 0.0, -2.0, 0.0);
		centerPath.closePath();
		innerPath.moveTo(0.0, 0.1);
		innerPath.lineTo(2.0, 0.1);
		innerPath.quadTo(2.9, 0.1, 2.9, 1.0);
		innerPath.lineTo(2.9, 4.0);
		innerPath.quadTo(2.9, 4.9, 2.0, 4.9);
		innerPath.quadTo(1.1, 4.9, 1.1, 4.0);
		innerPath.lineTo(1.1, 3.0);
		innerPath.quadTo(1.1, 1.9, 0.0, 1.9);
		innerPath.quadTo(-1.1, 1.9, -1.1, 3.0);
		innerPath.lineTo(-1.1, 4.0);
		innerPath.quadTo(-1.1, 4.9, -2.0, 4.9);
		innerPath.quadTo(-2.9, 4.9, -2.9, 4.0);
		innerPath.lineTo(-2.9, 1.0);
		innerPath.quadTo(-2.9, 0.1, -2.0, 0.1);
		innerPath.closePath();
		outerPath.moveTo(0.0, -0.1);
		outerPath.lineTo(2.0, -0.1);
		outerPath.quadTo(3.1, -0.1, 3.1, 1.0);
		outerPath.lineTo(3.1, 4.0);
		outerPath.quadTo(3.1, 5.1, 2.0, 5.1);
		outerPath.quadTo(0.9, 5.1, 0.9, 4.0);
		outerPath.lineTo(0.9, 3.0);
		outerPath.quadTo(0.9, 2.1, 0.0, 2.1);
		outerPath.quadTo(-0.9, 2.1, -0.9, 3.0);
		outerPath.lineTo(-0.9, 4.0);
		outerPath.quadTo(-0.9, 5.1, -2.0, 5.1);
		outerPath.quadTo(-3.1, 5.1, -3.1, 4.0);
		outerPath.lineTo(-3.1, 1.0);
		outerPath.quadTo(-3.1, -0.1, -2.0, -0.1);
		outerPath.closePath();
		path.append(innerPath, false);
		path.append(outerPath, false);
		path.setWindingRule(Path2D.WIND_EVEN_ODD);
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(90.0));
		at.scale(1024.0, 1024.0);
		path = (Path2D) at.createTransformedShape(path);
		return path;
	}
	protected void initSwing(Shape track) {
		JFrame jf = new JFrame("Track Test");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new MyJPanel(track);
		jf.add(jp, BorderLayout.CENTER);
		jf.setSize(1024, 768);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		javax.swing.Timer t = new javax.swing.Timer(
			33,
			ae -> {
				currentHeadingInRadians += Math.toRadians(turnRateInRadians);
				currentPosition.setLocation(
					currentPosition.getX() + Math.cos(currentHeadingInRadians) * currentSpeed,
					currentPosition.getY() + Math.sin(currentHeadingInRadians) * currentSpeed
				);
				if (!track.contains(currentPosition)) {
					if (!outOfBounds) {
						System.out.println("Out of Bounds!");
					}
					outOfBounds = true;
					if (racing) {
						currentSpeed = OUT_OF_BOUNDS_SPEED;
						if (currentGear != 0) {
							setCurrentGear(0);
						}
					}
				} else {
					if (outOfBounds) {
						System.out.println("On Track.");
					}
					outOfBounds = false;
				}
				jp.repaint();
			}
		);
		t.start();
		jf.addKeyListener(
			new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent ke) {
					if (ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT) {
						turnRateInRadians = 0.0;
					}
				}
				@Override
				public void keyPressed(KeyEvent ke) {
					if (ke.getKeyCode() == KeyEvent.VK_UP) {
						if (racing) {
							if (currentSpeed != 0.0) {
								setCurrentGear(currentGear + 1 > MAX_GEAR ? MAX_GEAR : currentGear + 1);
							}
							if (outOfBounds) {
								currentSpeed = OUT_OF_BOUNDS_SPEED;
								setCurrentGear(0);
							} else {
								currentSpeed = ON_TRACK_SPEED[currentGear];
							}
						} else {
							setCurrentGear(0);
							currentSpeed = ON_TRACK_SPEED[0];
							System.out.println("Go!");
							racing = true;
						}
					}
					if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
						if (racing) {
							setCurrentGear(currentGear - 1 < 0 ? 0 : currentGear - 1);
							if (outOfBounds) {
								currentSpeed = OUT_OF_BOUNDS_SPEED;
								setCurrentGear(0);
							} else {
								currentSpeed = ON_TRACK_SPEED[currentGear];
							}
						}
					}
					if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
						if (currentSpeed != 0.0) {
							turnRateInRadians = TURN_RATE_IN_RADIANS;
						}
					}
					if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
						if (currentSpeed != 0.0) {
							turnRateInRadians = -TURN_RATE_IN_RADIANS;
						}
					}
					if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
						System.out.println("Reset!");
						currentSpeed = 0.0;
						currentPosition.setLocation(0.0, 0.0);
						currentHeadingInRadians = Math.PI / 2.0;
						outOfBounds = true;
						racing = false;
						turnRateInRadians = 0.0;
						setCurrentGear(0);
					}
				}
			}
		);
	}
	public void setCurrentGear(int gear) {
		if (currentGear != gear) {
			System.out.println("Gear Change: " + (gear + 1));
			this.currentGear = gear;
		}
	}
	private class MyJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private final Stroke trackStroke = new BasicStroke(7.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10.0f, new float[] {83.0f, 83.0f}, 0.0f);
		private final Stroke finishLineStroke = new BasicStroke(17.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10.0f, new float[] {14.75f, 14.25f}, 0.0f);
		private final Stroke markerStroke = new BasicStroke(5.0f);
		private Shape track;
		Line2D finishLine = new Line2D.Double(-96.0, 0.0, 96.0, 0.0);
		public MyJPanel(Shape t) {
			this.track = t;
			setBackground(new Color(0, 120, 64));
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			AffineTransform gat = new AffineTransform();
			gat.translate(getWidth() / 2.0, getHeight() - getHeight() / 4.0);
			gat.scale(1.0, -1.0);
			g2d.transform(gat);
			AffineTransform tat = new AffineTransform();
			tat.rotate(-(currentHeadingInRadians - Math.PI / 2.0));
			tat.translate(-currentPosition.getX(), -currentPosition.getY());
			Shape t = tat.createTransformedShape(track);
			g2d.setPaint(Color.BLACK);
			g2d.setStroke(trackStroke);
			g2d.fill(t);
			g2d.setPaint(Color.YELLOW);
			g2d.draw(t);
			Shape fl = tat.createTransformedShape(finishLine);
			g2d.setPaint(Color.YELLOW);
			g2d.setStroke(finishLineStroke);
			g2d.draw(fl);
			g2d.setPaint(Color.RED);
			g2d.setStroke(markerStroke);
			g2d.draw(marker);
			Graphics2D g2dBug = (Graphics2D) g2d.create();
			drawBug(g2dBug);
			g2dBug.dispose();
			g2d.dispose();
		}
	}
	protected void drawBug(Graphics2D g2d) {
	}
}
