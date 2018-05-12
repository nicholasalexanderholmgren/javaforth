package edu.mccc.cos210.ds.fp.javaforth.view_ide;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	private JButton startButton;
	public JButton getStartButton() {
		return startButton;
	}

	public JButton getStepButton() {
		return stepButton;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}

	public JButton getStopButton() {
		return stopButton;
	}

	private JButton stepButton;
	private JButton pauseButton;
	private JButton stopButton;

	public StatusPanel() {
		startButton = new JButton("Start");
		stepButton = new JButton("Step");
		pauseButton = new JButton("Pause");
		stopButton = new JButton("Stop");
		this.add(startButton);
		this.add(stepButton);
		this.add(pauseButton);
		this.add(stopButton);
	}
}
