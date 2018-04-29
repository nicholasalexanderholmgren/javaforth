package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Forthstack is a memory segment implementation that is further specialized to aid in managing the stack
 * region of the ForthMachine's memory.
 * The pointers here are not values on the stack, nor does it enforce stack behavior, but rather this class is a tool for keeping track of
 * where the stack is in memory.
 */
public class ForthStack extends AbstractMemorySegment {
	private Object[] stackMemorySegment, memory;
	List<Object> stackList;
	public ForthStack(Object[] memory, int stackPointer) {
		super(stackPointer);	
		this.memory = memory;
		stackList = new ArrayList <Object>(INITIAL_POINTER);
	}
	public Object[] registerStackMemory() {
		
		for (int i = super.INITIAL_POINTER, j = 0 ; i < this.getCurrentPointer()  ; i-- , j++) {			
			stackMemorySegment[j] = memory[i];
		}
			
		return stackMemorySegment;
	}
	
	/**
	 * This methods reads from the provided memory to create a string that represents the stack.
	 * The first character of the string is the top element of the stack, the last element is the bottom.
	 * @return String This returns a string representing the stack.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(stackList.size()>0) {
			for(Object o : stackList) {
				sb.append(o +", ");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
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
		return this.INITIAL_POINTER - this.getCurrentPointer();
	}
	
	public void push(Object n) {
		//stackMemorySegment[stackMemorySegment.length] = n;
		if(getCurrentPointer()>memory.length) {
			System.out.println("How?");
		}
		memory[getCurrentPointer()] = n;
		this.setCurrentPointer(this.getCurrentPointer() - 1);
		stackList.add(n);
		System.out.println("You just pushed " + n + " on!");
		System.out.println(this.toString());
	}
	public Object pop() throws IndexOutOfBoundsException {
		//Object top = stackMemorySegment[stackMemorySegment.length - 1];
		//stackMemorySegment[stackMemorySegment.length - 1] = null;
		if(getCurrentPointer() >= INITIAL_POINTER) {
			throw new IndexOutOfBoundsException();
		}
		Object top = memory[getCurrentPointer()+1];
		this.setCurrentPointer(this.getCurrentPointer() + 1);
		stackList.remove(stackList.size() - 1);
		System.out.println("You just poped " + top + " off!");
		return top;
	}
	
	
}
