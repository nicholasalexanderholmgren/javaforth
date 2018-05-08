package edu.mccc.cos210.bugatti.util;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class PaintBug {
	private static final String BUG = "FlamingBurrito";
	private static final String BUG_PAINTER_PACKAGE = "edu.mccc.cos210.bugatti.util.bugpainters";
	public static void main(String... args) {
		PaintBug pb = new PaintBug();
		EventQueue.invokeLater(() -> pb.initSwing());
	}
	private void initSwing() {
		JFrame jf = new JFrame("Paint Bug");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new PaintBugView(getBugPainter(BUG)));
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	public static IBugPainter getBugPainter(String bugName) {
		IBugPainter bugPainter = null;
		try {
			bugPainter = (IBugPainter) Class.forName(
				BUG_PAINTER_PACKAGE + "." + bugName
			).getConstructor().newInstance();
		} catch (ReflectiveOperationException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		return bugPainter;
	}
}
