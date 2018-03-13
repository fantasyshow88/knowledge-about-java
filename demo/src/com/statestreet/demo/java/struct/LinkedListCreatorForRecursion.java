package com.statestreet.demo.java.struct;

import java.util.ArrayList;
import java.util.List;

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
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		Node node = createLinkedList(list);
		System.out.println(node);
		
	}
}
