package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.List;
import java.util.Stack;

/**
 * Forthstack is a memory segment implementation that is further specialized to aid in managing the stack
 * region of the ForthMachine's memory.
 * The pointers here are not values on the stack, nor does it enforce stack behavior, but rather this class is a tool for keeping track of
 * where the stack is in memory.
 */
public class ForthStack {
	private Stack<Byte> stack;
	public ForthStack() {
		stack = new Stack();
	}
	public ForthStack(Collection<Byte> init) {
		stack = new Stack();
		for(Byte b : init) {
			stack.push(b);
		}
	}
	/**
	 * This methods reads from the provided memory to create a string that represents the stack.
	 * The first character of the string is the top element of the stack, the last element is the bottom.
	 * @return String This returns a string representing the stack.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(Byte b : stack) {
			sb.append(b.toString() + ", ");
		}
		sb.append("]-->");
		return sb.toString();
	}
	/**
	 * This method calculates the height of the stack, which is likely useful for debugging and preventing errors that could happen when a word
	 * requires more arguments than there are elements on the stack.
	 * The stack's height means the number of bytes on the stack, not the number of integers. If a word needs multiple integers then make sure
	 * the stack has two times the number of needed integers in height.
	 * @return int the number of bytes on the stack.
	 */
	public int stackHeight() {
		return stack.size();
	}
	
	public void push(Byte b) {
		stack.push(b);
	}
	public Object pop() throws EmptyStackException {
		return stack.pop();
	}
}
