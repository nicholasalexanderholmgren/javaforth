package edu.mccc.cos210.ds;

public interface IVector<T> extends IArray<T> {
	public void pushBack(T data);
	public void popBack();
	public T back();
	public int getCapacity();
	public void shrinkToFit();
}
