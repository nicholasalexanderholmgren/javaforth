package edu.mccc.cos210.ds;

import java.util.Iterator;

public class Map<K, V> implements IMap<K, V>, Iterable<IMap.Entry<K, V>> {
	private IResizableArray<IOrderedList<IMap.Entry<K, V>>> theVector = new ResizableArray<>(1);
	private int size = 0;
	public Map() {
		for (int i = 0; i < theVector.getSize(); i++) {
			theVector.set(i, new OrderedList<IMap.Entry<K, V>>());
		}
	}
	@Override
	public boolean containsKey(K key) {
		int index = compress(key.hashCode());
		for (edu.mccc.cos210.ds.IMap.Entry<K, V> item : theVector.get(index)) {
			if (item.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public V get(K key) {
		V value = null;
		IMap.Entry<K, V> entry = new Map.Entry<K, V>(key, null);
		int index = compress(hash(entry));
		IOrderedList<IMap.Entry<K, V>> list = theVector.get(index);
		for (edu.mccc.cos210.ds.IMap.Entry<K, V> item : list) {
			if (item.getKey().equals(key)) {
				value = item.getValue();
			}
		}
		return value;
	}
	@Override
	public ISet<K> keySet() {
		Set<K> set = new Set<K>();
		for (IOrderedList<IMap.Entry<K, V>> list : theVector) {
			for (edu.mccc.cos210.ds.IMap.Entry<K, V> item : list) {
				set.add(item.getKey());
			}
		}
		return set;
	}
	@Override
	public void put(K key, V value) {
		if ((double) getSize() > theVector.getSize() * 0.7) {
			increaseCapacity();
		}
		IMap.Entry<K, V> entry = new Map.Entry<K, V>(key, value);
		int index = computeIndex(entry);
		IOrderedList<IMap.Entry<K, V>> list = theVector.get(index);
		if (!theVector.get(index).contains(entry)) {
			list.add(entry);
			size++;
			return;
		}
		Iterator<edu.mccc.cos210.ds.IMap.Entry<K, V>> iterator = list.iterator();
		while (iterator.hasNext()) {
			edu.mccc.cos210.ds.IMap.Entry<K, V> e = iterator.next();
			if(e.getKey().equals(key)) {
				iterator.remove();
				list.add(entry);
				break;
			}
		}
	}
	@Override
	public int getSize() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return getSize() == 0;
	}
	@Override
	public String toString() {
		return theVector.toString();
	}
	private int hash(IMap.Entry<K, V> entry) {
		return entry.hashCode();
	}
	private int compress(int hash) {
		return compress(hash, theVector.getSize());
	}
	private int compress(int hash, int mod) {
		return Math.abs(hash % mod);
	}
	private int computeIndex(IMap.Entry<K, V> entry) {
		int index = compress(hash(entry));
		return index;
	}
	private void increaseCapacity() {
		IResizableArray<IOrderedList<IMap.Entry<K, V>>> newVector = new ResizableArray<>(theVector.getSize() * 2);
		for (int i = 0; i < newVector.getSize(); i++) {
			newVector.set(i, new OrderedList<IMap.Entry<K, V>>());
		}
		for (IMap.Entry<K, V> data : this) {
			int index = compress(hash(data), newVector.getSize());
			newVector.get(index).addFirst(data);
		}
		theVector = newVector;
	}
	public static class Entry<S, T> implements IMap.Entry<S, T> {
		private S key;
		private T value;
		public Entry(S key, T value) {
			setKey(key);
			setValue(value);
		}
		@Override
		public S getKey() {
			return key;
		}
		@Override
		public void setKey(S key) {
			this.key = key;
		}
		@Override
		public T getValue() {
			return value;
		}
		@Override
		public void setValue(T value) {
			this.value = value;
		}
		@Override
		public int hashCode() {
			return getKey().hashCode();
		}
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object o) {
			if (o instanceof Map.Entry) {
				Map.Entry<S, T> that = (Map.Entry<S, T>) o;
				return this.key.equals(that.key);
			} else {
				return false;
			}
		}
		@Override
		public String toString() {
			return "{ " + getKey().toString() + " : " + getValue().toString() + " }";
		}
	}
	@Override
	public java.util.Iterator<IMap.Entry<K, V>> iterator() {
		IOrderedList<IMap.Entry<K, V>> dataList = new OrderedList<>();
		for (int i = 0; i < theVector.getSize(); i++) {
			for (IMap.Entry<K, V> data : theVector.get(i)) {
				dataList.addFirst(data);
			}
		}
		return dataList.iterator();
	}
}
