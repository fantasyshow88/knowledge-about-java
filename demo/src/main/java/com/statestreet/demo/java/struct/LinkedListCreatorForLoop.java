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

	/**
	 * 如果节点为value 则删除该节点
	 */
	public static Node deleteIfNode(Node head,int value){
		Node previousNode = null;
		Node currentNode = head;
		while (currentNode != null){
			if(Integer.parseInt(currentNode.getValue().toString()) == value){
				if(previousNode == null){
					head = head.getNext();
					currentNode = head;
				}else{
					Node nextNode = currentNode.getNext();
					previousNode.setNext(currentNode.getNext());
					currentNode = nextNode;
				}
			}else{
				Node nextNode = currentNode.getNext();
				previousNode = currentNode;
				currentNode = nextNode;
			}
		}
		return head;
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(2);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(2);
		list.add(2);
		list.add(4);
		list.add(2);
		Node node = createLinkedList(list);
		printNode(node);
		System.out.println("~~~~~~~~~~~~~~");
/*		Node newHead = reverseLinkedListForLoop(node);
		printNode(newHead);*/
		Node head = deleteIfNode(node,2);
		printNode(head);


		
	}
}
