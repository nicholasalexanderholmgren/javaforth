package edu.mccc.cos210.ds.fp.javaforth.util;

public interface ISubject {
	void registerObserver(IObserver o);
	void removeObserver(IObserver o);
	void notifyObservers();
}