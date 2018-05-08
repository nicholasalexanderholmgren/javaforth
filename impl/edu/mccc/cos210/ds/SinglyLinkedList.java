package edu.mccc.cos210.ds;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements ISinglyLinkedList<T> {
	protected SNode head = null;
	private int size = 0;
	@Override
	public void addFirst(T data) {
		if(data == null)
			throw new IllegalArgumentException("data");
		SNode node = new SNode(data);
		if(head != null) {
			node.setNextNode(head);
		}
		size++;
		head = node;
	}
	@Override
	public T getFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		return head.data;
	}
	@Override
	public T removeFirst() {
		SNode head = this.head;
		if(head == null)
			throw new NoSuchElementException();
		SNode nextNode = head.getNextNode();
		if(nextNode == null)
			this.head = null;
		else
			this.head = nextNode;
		size--;
		return head.getData();
	}
	@Override
	public void addLast(T data) {
		if(data == null)
			throw new IllegalArgumentException("data");
		if(head == null) {
			head = new SNode(data);
			size++;
			return;
		}
		SNode lastNode = this.getLastNode();
		lastNode.setNextNode(new SNode(data));
		size++;
	}
	@Override
	public T getLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		return getLastNode().getData();
	}
	private SNode getLastNode() {
		if(head == null)
			throw new java.util.NoSuchElementException();
		SNode current = head;
		SNode nextNode = head.getNextNode();
		while (nextNode != null) {
			current = nextNode;
			nextNode = current.getNextNode();
		}
		return current;
	}
	@Override
	public int getSize() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		SNode node = head;
		sb.append("[ ");
		while (node != null) {
			sb.append(node.getData().toString() + " ");
			node = node.getNextNode();
		}
		sb.append("]");
		return sb.toString();
	}
	@Override
	public java.util.Iterator<T> iterator() {
		return new SIterator();
	}
	private class SIterator implements java.util.Iterator<T> {
		private SNode next = head;
		@Override
		public boolean hasNext() {
			if (next == null) {
				return false;
			}
			return true;
		}
		@Override
		public T next() {
			if (next == null) {
				throw new java.util.NoSuchElementException();
			}
			T data = next.getData();
			next = next.getNextNode();
			return data;
		}
	}
	protected class SNode {
		private SNode nextNode;
		private T data;
		public SNode(T data) {
			setData(data);
		}
		public SNode getNextNode() {
			return nextNode;
		}
		public void setNextNode(SNode nextNode) {
			this.nextNode = nextNode;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
	}
}
