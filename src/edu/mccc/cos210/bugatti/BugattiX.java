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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BugattiX {
	private static String server_address = "localhost";
	private static String BUG = "FlamingBurrito";
	private BufferedImage bug = null;
	private static double[] ON_TRACK_SPEED;
	private static final double OUT_OF_BOUNDS_SPEED = 4.0;
	private static final double TURN_RATE_IN_RADIANS = Math.toRadians(120.0);
	private static int MAX_GEAR;
	private final Ellipse2D marker = new Ellipse2D.Double(-16.0, -16.0, 32.0, 32.0);
	private Point2D currentPosition = new Point2D.Double();
	private double currentSpeed = 0.0;
	private double currentHeadingInRadians = Math.PI / 2.0;
	private int currentGear = 0;
	private double turnRateInRadians = 0.0;
	private boolean racing = false;
	private boolean outOfBounds = true;
	private InetAddress inet_address;
	private int server_port = 5972;
	private String networkKey;
	private long startTime = 0;
	private int nextCheckpoint = 0;
	private Ellipse2D[] wrightPoints = new Ellipse2D[] {
		new Ellipse2D.Double(-5245.0, 1923.0, 250.0, 250.0),
		new Ellipse2D.Double(-2173.0, -125.0, 250.0, 250.0),
		new Ellipse2D.Double(-5245.0, -2173.0, 250.0, 250.0),
		new Ellipse2D.Double(-125.0, 0.0, 250.0, 250.0),
	};
	private boolean turbo = false;
	//private static Sequencer sequencer;
	public BugattiX() {
		bug = getBug();
		ON_TRACK_SPEED = getSpeeds(BUG);
		MAX_GEAR = ON_TRACK_SPEED.length - 1;
	}
	public static void main(String... args) {
		if (args.length != 2) {
			System.err.println("Usage: java -cp bin edu.mccc.cos210.bugatti.BugattiX <bugName> <serverIP>");
			System.exit(-1);
		}
		server_address = args[1];
		BUG = args[0];
		BugattiX b = new BugattiX();
		//MidiPlayer mp = new MidiPlayer("./data/bugatti.mid");
		//sequencer = mp.getSequencer();
		Shape track = initTrack();
		b.initNetwork();
		EventQueue.invokeLater(() -> b.initSwing(track));
	}
	private void initNetwork() {
		try {
			inet_address = InetAddress.getByName(server_address);
			networkKey = getNetworkKey();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private String getNetworkKey() {
		String key = String.format("X_%s_%.3f", BUG, Math.random());
		return key;
	}
	private void networkSend(Point2D p2d) {
		try {
			String data = String.format("%s %f %f", networkKey, p2d.getX(), p2d.getY());
			byte[] buffer = data.getBytes();
			DatagramPacket pkt = new DatagramPacket(buffer, buffer.length, inet_address, server_port);
			DatagramSocket sock = new DatagramSocket();
			sock.send(pkt);
			sock.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
	private void initSwing(Shape track) {
		JFrame jf = new JFrame("Bugatti");
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
				if (wrightPoints[nextCheckpoint].contains(currentPosition)) {
					nextCheckpoint++;
					if (nextCheckpoint == wrightPoints.length) {
						//sequencer.stop();
						racing = false;
						currentSpeed = 0;
						nextCheckpoint = 0;
						System.out.printf("Finish!\n%.2f seconds\n", ((System.currentTimeMillis() - startTime) / 1000.0));
					} else {
						System.out.println("Checkpoint " + nextCheckpoint);	
					}
				}
				if (!track.contains(currentPosition)) {
					if (!outOfBounds) {
						System.out.println("Out of Bounds!");
					}
					outOfBounds = true;
					if (turbo) {
						turbo = false;
						System.out.println("Boost Off");
					}
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
				networkSend(currentPosition);
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
					if (ke.getKeyCode() == KeyEvent.VK_Z) {
						if (turbo) {
							System.out.println("Boost Off");
						}
						turbo = false;
						if (racing) {
							currentSpeed = ON_TRACK_SPEED[currentGear];
						}
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
							//sequencer.start();
							startTime = System.currentTimeMillis();
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
						//sequencer.stop();
						currentSpeed = 0.0;
						currentPosition.setLocation(0.0, 0.0);
						currentHeadingInRadians = Math.PI / 2.0;
						outOfBounds = true;
						racing = false;
						turnRateInRadians = 0.0;
						setCurrentGear(0);
						nextCheckpoint = 0;
						turbo = false;
					}
					if (ke.getKeyCode() == KeyEvent.VK_Z) {
						if (racing) {
							if (!turbo) {
								System.out.println("Turbo!");
								turbo = true;
							}
							currentSpeed = ON_TRACK_SPEED[currentGear] * 2.0;
						}
					}
				}
			}
		);
	}
	private void setCurrentGear(int gear) {
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
	private void drawBug(Graphics2D g2d) {
		AffineTransform at = new AffineTransform();
		at.translate(bug.getWidth() / 8.0, bug.getHeight() / 8.0);
		at.rotate(Math.PI);
		at.scale(0.25, 0.25);
		g2d.drawRenderedImage(bug, at);
	}
	private BufferedImage getBug() {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File("./images/bugatti/" + BUG +".png"));
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		return bi;
	}
	private double[] getSpeeds(String bug) {
		double[] speeds = new double[] { 8.0, 12.0, 16.0, 24.0, 32.0 };
		for (Detail d : details) {
			if (d.getBug().equals(bug)) {
				speeds = d.getSpeeds();
				break;
			}
		}
		return speeds;
	}
	class Detail {
		private String bug;
		private double[] speeds;
		public Detail(String bug, double[] speeds) {
			this.bug = bug;
			this.speeds = speeds;
		}
		public String getBug() {
			return bug;
		}
		public double[] getSpeeds() {
			return speeds;
		}
	};
	private Detail[] details = new Detail[] {
		new Detail("Zubenelgenubi", new double[] { 8.0, 12.0, 24.0, 32.0, 48.0, 64.0, 80.0, }), 
		new Detail("FlamingBurrito", new double[] { 8.0, 16.0, 32.0, 48.0, 64.0, }),               
		new Detail("AngryPorkChop", new double[] { 8.0, 16.0, 32.0, 48.0, 64.0, }),               
		new Detail("ViralInfection", new double[] { 8.0, 16.0, 32.0, 48.0, 64.0, }),             
		new Detail("ScreamingBanshee", new double[] { 8.0, 12.0, 20.0, 32.0, 48.0, }),              
		new Detail("LethalInjection", new double[] { 8.0, 12.0, 20.0, 32.0, 48.0, }),             
		new Detail("BadMedicine", new double[] { 8.0, 12.0, 20.0, 32.0, 48.0, }),             
		new Detail("RedSquirrel", new double[] { 8.0, 12.0, 16.0, 24.0, 32.0, }),         
		new Detail("YellowSquirrel", new double[] { 8.0, 12.0, 16.0, 24.0, 32.0, }),            
		new Detail("RustySpoon", new double[] { 8.0, 12.0, 16.0, 24.0, 32.0, }),             
		new Detail("MommasBadBoy", new double[] { 8.0, 12.0, 16.0, 24.0, 32.0, }),             
		new Detail("PowerTool", new double[] { 8.0, 16.0, 24.0, 32.0, }),               
		new Detail("UnluckyBandit", new double[] { 8.0, 16.0, 24.0, 32.0, }),               
		new Detail("Intimidator", new double[] { 8.0, 12.0, 16.0, 32.0, }),                
		new Detail("WidowMaker", new double[] { 8.0, 12.0, 16.0, 32.0, }),                
		new Detail("SilentNinja", new double[] { 8.0, 16.0, 32.0, }),                 
		new Detail("Undertaker", new double[] { 8.0, 16.0, 32.0, }),                 
		new Detail("MisterLucifer", new double[] { 8.0, 16.0, 32.0, }),                 
		new Detail("Beelzebub", new double[] { 8.0, 12.0, 24.0, }),                 
		new Detail("Apocalypse", new double[] { 8.0, 12.0, 24.0, }),                 
		new Detail("LittleWillie", new double[] { 8.0, 16.0, }), 
	};
}
