package edu.mccc.cos210.ds;

public interface ISet<T> extends java.lang.Iterable<T> {
	public void add(T data);
	public void add(ISet<T> set);
	public void remove(T data);
	public boolean contains(T data);
	public boolean contains(ISet<T> set);
	public ISet<T> union(ISet<T> set);
	public ISet<T> intersection(ISet<T> set);
	public int getSize();
	public boolean isEmpty();
    @Override
    default java.util.Spliterator<T> spliterator() {
        return java.util.Spliterators.spliterator(iterator(), getSize(), java.util.Spliterator.ORDERED);
    }
    default java.util.stream.Stream<T> stream() {
        return java.util.stream.StreamSupport.stream(spliterator(), false);
    }
    default java.util.stream.Stream<T> parallelStream() {
        return java.util.stream.StreamSupport.stream(spliterator(), true);
    }
}
