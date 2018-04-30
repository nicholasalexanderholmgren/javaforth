package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InputStream {
	private ConcurrentLinkedQueue<String> queue;
	public void add(String s) {
		s = s.trim();
		StringTokenizer tokenizer = new StringTokenizer(s);
		while(tokenizer.hasMoreTokens()) {
			queue.add(tokenizer.nextToken());
		}
	}
	public String pull() {
		return queue.poll();
	}
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
