package javaforth.machineModel;

public interface IMemorySegment {
	int getCurrentPointer();
	int setCurrentPointer(int newPointer);
}
