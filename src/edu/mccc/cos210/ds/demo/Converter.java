package edu.mccc.cos210.ds.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.mccc.cos210.ds.IQueue;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.Queue;
import edu.mccc.cos210.ds.Stack;

public class Converter {
	
	// Implements Edsger Dijkstra's Shunting-Yard Algorithm
	
	private IQueue<Object> outqueue = new Queue<>();
	private IStack<String> opstack = new Stack<>();
	public static void main(String... args) throws SyntaxError {
		new Converter().loop();
	}
	private void loop() throws SyntaxError {
		Pattern p1 = Pattern.compile("(-?[0-9]+\\.?[0-9]*)(.*)");
		Pattern p2 = Pattern.compile("([ \t()*/+-])(.*)");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String s = "";
			while ((s = br.readLine()) != null) {
				String t = s.trim();
				if (t.length() == 0) {
					continue;
				}
				System.out.println(t);
				while (t.length() != 0) {
					Matcher m = p1.matcher(t);
					if (m.matches()) {
						outqueue.enqueue(Double.parseDouble(m.group(1)));
						t = m.group(2);
					} else {
						m = p2.matcher(t);
						if (m.matches()) {
							doToken(m.group(1));
							t = m.group(2);
						} else {
							throw new SyntaxError();
						}
					}
				}
				while (!opstack.isEmpty()) {
					String op = opstack.peek();
					if ("(".equals(op)) {
						throw new SyntaxError();
					}
					opstack.pop();
					outqueue.enqueue(op);
				}
				System.out.println(outqueue);
				calc();
				outqueue = new Queue<>();
			}
		} catch (IOException ex) {
			throw new SyntaxError(ex);
		}
	}
	private void doToken(String s) throws SyntaxError {
		String t = "";
		switch (s) {
			case "(":
				opstack.push(s);
				break;
			case ")":
				t = opstack.peek();
				while (!"(".equals(t)) {
					opstack.pop();
					outqueue.enqueue(t);
					t = opstack.peek();
				}
				opstack.pop();
				break;
			case "+":
			case "-":
				while (!opstack.isEmpty()) {
					t = opstack.peek();
					if ("+".equals(t) || "-".equals(t) || "*".equals(t) || "/".equals(t)) {
						opstack.pop();
						outqueue.enqueue(t);
					} else {
						break;
					}
				}
				opstack.push(s);
				break;
			case "*":
			case "/":
				while (!opstack.isEmpty()) {
					t = opstack.peek();
					if ("*".equals(t) || "/".equals(t)) {
						opstack.pop();
						outqueue.enqueue(t);
					} else {
						break;
					}
				}
				opstack.push(s);
				break;
			case " ":
			case "\t":
				break;
			default:
				throw new SyntaxError();
		}
	}
	private void calc() {
		IStack<Double> stack = new Stack<>();
		Double value = 0.0;
		while (!outqueue.isEmpty()) {
			Object o = outqueue.dequeue();
			if (o instanceof Double) {
				stack.push((Double) o);
			} else {
				String op = (String) o;
				Double d2 = stack.peek();
				stack.pop();
				Double d1 = stack.peek();
				stack.pop();
				switch (op) {
					case "+":
						value = d1 + d2;
						break;
					case "-":
						value = d1 - d2;
						break;
					case "*":
						value = d1 * d2;
						break;
					case "/":
						value = d1 / d2;
						break;
				}
				stack.push(value);
			}
		}
		System.out.println(stack.peek());
	}
	private class SyntaxError extends Exception {
		private static final long serialVersionUID = 1L;
		public SyntaxError(Exception ex) {
			super("Syntax Error", ex);
		}
		public SyntaxError() {
			this(null);
		}
	}
}
