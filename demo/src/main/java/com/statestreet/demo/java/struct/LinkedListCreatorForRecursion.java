package com.statestreet.demo.java.struct;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LinkedListCreatorForRecursion {

	
	public static Node createLinkedList(List<Integer> values) {
		if(values.isEmpty()) {
			return null;
		}
		Node firstNode = new Node(values.get(0));
		Node headOfSubList = createLinkedList(values.subList(1, values.size()));
		firstNode.setNext(headOfSubList);
		return firstNode;
	}
	
	public static void printNode(Node head) {
		System.out.println();
		while(head != null) {
			System.out.print(head);
			head = head.getNext();
		}
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		Node node = createLinkedList(list);
		printNode(node);
		Node newHead = LinkedListReversedForRecursion.ReversedLinkedList(node);
		printNode(newHead);
		
		//可用于归并排序 大数据量
		PriorityQueue<Integer> q = new PriorityQueue<>();
		q.add(3);
		q.add(7);
		q.add(9);
		q.add(1);
		q.add(1);
		System.out.println();
		System.out.println(q.poll());
		System.out.println(q.poll());
		
	}
}
