package edu.mccc.cos210.ds;

public interface IResizableArray<T> extends IArray<T> {
	public void resize(int size);
	public void resize(int size, T init);
}
