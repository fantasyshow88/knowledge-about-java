package com.statestreet.demo.java.struct;

public class Node<E> {

	private final E value;
	private Node next;
	
	public Node(E value) {
		super();
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	
	
	public E getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString() + " ";
	}
	
	
	
}
