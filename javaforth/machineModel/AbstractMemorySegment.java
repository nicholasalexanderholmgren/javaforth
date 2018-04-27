package javaforth.machineModel;
/**
 * AbstractMemorySegment is an abstract class that implements major portions of the IMemory Segment interface.
 * Memory segments keep track of their initial pointer, which cannot be changed after creation, 
 * and their current pointer, which can be changed during use.
 * 
 */
public abstract class AbstractMemorySegment implements IMemorySegment {
	int INITIAL_POINTER;
	int currentPointer;
	public AbstractMemorySegment(int initialPointer) {
		INITIAL_POINTER = initialPointer;
		currentPointer = initialPointer;
	}
	
	public int getCurrentPointer() {
		return currentPointer;
	}
	public int setCurrentPointer(int newPointer) {
		int temp = currentPointer;
		currentPointer = newPointer;
		return temp;
	}
}
