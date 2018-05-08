package edu.mccc.cos210.ds.demo;

import java.io.File;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

public class MidiDump {
	public MidiDump(String... args) {
		try {
			System.out.println(args[0]);
			Sequence sequence = MidiSystem.getSequence(new File(args[0]));
			float divisionType = sequence.getDivisionType();
			int resolution = sequence.getResolution();
			long microsecondLength = sequence.getMicrosecondLength();
			long tickLength = sequence.getTickLength();
			System.out.println(
				divisionType + " " +
				resolution + " " +
				(double) microsecondLength / 1000000.0 + " " +
				tickLength
			);
			Track[] track = sequence.getTracks();
			for (int i = 0; i < track.length; i++) {
				System.out.print("Track-" + (i + 1));
				int size = track[i].size();
				long ticks = track[i].ticks();
				System.out.println(" " + size + " " + ticks);
				for (int j = 0; j < track[i].size(); j++) {
					MidiEvent midiEvent = track[i].get(j);
					long tick = midiEvent.getTick();
					MidiMessage midiMessage = midiEvent.getMessage();
					int metaType = 0;
					if (midiMessage instanceof MetaMessage) {
						metaType = ((MetaMessage) midiMessage).getType();
					}
					int length = midiMessage.getLength();
					int status = midiMessage.getStatus();
					byte[] message = midiMessage.getMessage();
					System.out.print(
						padLeft(tick, 7) + " " +
						padLeft(length, 3) + " "
					);
					for (int k = 0; k < message.length; k++) {
						System.out.print(" ");
						printHex(message[k]);
					}
					if (
						status == 255 &&
						(
							metaType == 1 ||
							metaType == 2 ||
							metaType == 3 ||
							metaType == 4 ||
							metaType == 5 ||
							metaType == 6
						)
					) {
						System.out.print(" ");
						for (int k = 3; k < message.length; k++) {
							System.out.print((char) message[k]);
						}
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public static void main(String... args) {
		if (args.length == 1) {
			new MidiDump(args[0]);
		} else {
			System.err.println("Usage: java MidiDump file.mid");
			System.exit(-1);
		}
	}
	private void printHex(byte b) {
		String s = Integer.toHexString(b & 0x000000ff);
		switch (s.length()) {
			case 1:
				System.out.print("0" + s);
				break;
			case 2:
				System.out.print(s);
				break;
		}
	}
	private String padLeft(long n, int len) {
		String fill = "        ";
		String ns = String.valueOf(n);
		return fill.substring(0, len - ns.length()) + ns;
	}
}