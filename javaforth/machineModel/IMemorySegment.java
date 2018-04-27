package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public interface IMemorySegment {
	int getCurrentPointer();
	int setCurrentPointer(int newPointer);
}
