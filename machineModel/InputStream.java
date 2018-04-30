package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * Input stream is a facade that provides a simplified version of a queue for use in the forth machine. User input 
 * should be passed in by the add method,  
 */
public class InputStream {
	private ConcurrentLinkedQueue<String> queue;
	public InputStream() {
		queue = new ConcurrentLinkedQueue<>();
	}
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
	public void flush() {
		queue = new ConcurrentLinkedQueue<>();
	}
}
