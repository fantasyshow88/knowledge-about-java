package com.statestreet.demo.java.struct;

public class Node<E> {

	E value;
	Node next;
	
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
	
	
	
	
	
	
}
