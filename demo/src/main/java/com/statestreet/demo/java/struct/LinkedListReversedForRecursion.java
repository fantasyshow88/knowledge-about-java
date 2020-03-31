package com.statestreet.demo.java.struct;

public class LinkedListReversedForRecursion {

	
	public static Node ReversedLinkedList(Node head) {
		if(head == null || head.getNext()== null) {
			return head;
		}
		Node newHead = ReversedLinkedList(head.getNext());
		head.getNext().setNext(head);
		head.setNext(null);
		return newHead;
	}
	
}
