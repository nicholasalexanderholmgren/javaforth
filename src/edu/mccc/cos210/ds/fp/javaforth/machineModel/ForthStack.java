package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import edu.mccc.cos210.ds.fp.javaforth.util.ByteUtils;

/**
 * Forthstack is a memory segment implementation that is further specialized to aid in managing the stack
 * region of the ForthMachine's memory.
 * The pointers here are not values on the stack, nor does it enforce stack behavior, but rather this class is a tool for keeping track of
 * where the stack is in memory.
 */
public class ForthStack {
	private Stack<Byte> stack;
	public ForthStack() {
		stack = new Stack<>();
	}
	public ForthStack(List<Byte> init) {
		stack = new Stack<>();
		for(Byte b : init) {
			stack.push(b);
		}
	}
	/**
	 * This methods reads from the provided memory to create a string that represents the stack in the format of 
	 * "[top byte, second byte, ..., bottom byte]-->".
	 * @return String This returns a string representing the stack.
	 */
	public String toString() {
		return stack.toString();
	}
	public List<String> asList() {
		ArrayList<String> strings = new ArrayList<>();
		for(Byte b : stack) {
			strings.add(Integer.toHexString(Byte.toUnsignedInt(b)));
		}
		return strings;
	}
	/**
	 * This method calculates the height of the stack, which is likely useful for debugging 
	 * and preventing errors that could happen when a word
	 * requires more arguments than there are elements on the stack.
	 * The stack's height means the number of bytes on the stack, not the number of integers.
	 *  If a word needs multiple integers then make sure
	 * the stack has two times the number of needed integers in height.
	 * @return int the number of bytes on the stack.
	 */
	public int stackHeight() {
		return stack.size();
	}
	/**
	 * 
	 * @param byt
	 */
	public void push(Byte byt) {
		stack.push(byt);
	}
	/**
	 * Method for pushing integers on to the stack. This method handles 
	 * @param n - the value being put on the stack as an integers, should be <2^16 or will be modulod into that range.
	 */
	public void push(Integer n) {
		
	}
	public Byte popByte() throws EmptyStackException {
		return stack.pop();
	}
	public Integer popInteger() throws EmptyStackException {
		Byte leading = this.popByte();
		Byte trailing = this.popByte();
		int ans = ByteUtils.bytesToInt(leading, trailing);
		return ans;
	}
}
