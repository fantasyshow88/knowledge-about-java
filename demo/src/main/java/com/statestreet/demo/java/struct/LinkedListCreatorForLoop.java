package com.statestreet.demo.java.struct;

import java.util.ArrayList;
import java.util.List;

/**
 * 循环不变式 是一句定义各变量所满足是条件
 * @author Administrator
 *
 */
public class LinkedListCreatorForLoop{

	public static Node createLinkedList(List<Integer> values) {
		Node pre = null;
		Node head = null;
		for(int i=0;i<values.size();i++) {
			Integer v = values.get(i);
			Node node = new Node(v);
			if(pre != null) {
				pre.setNext(node);
			}else {
				pre = node;
				head = node;
			}
			pre = node;

		}
		return head;
		
	}
	
	public static Node reverseLinkedListForLoop(Node head) {
		Node newHead = null;
		Node currHead = head;
		while(currHead != null) {
			Node nextNode = currHead.getNext();
			currHead.setNext(newHead);
			newHead = currHead;
			currHead = nextNode;
			
		} 
		return newHead;
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
		Node newHead = reverseLinkedListForLoop(node);
		printNode(newHead);
		
	}
}
