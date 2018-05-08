package edu.mccc.cos210.ds.graph;

public class VertexComparator<T> implements java.util.Comparator<Vertex<T>> {
	@Override
	public int compare(Vertex<T> left, Vertex<T> right) {
		int n = left.getDistance() - right.getDistance();
		return n;
	}
}
