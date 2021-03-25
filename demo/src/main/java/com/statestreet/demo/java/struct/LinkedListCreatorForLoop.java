package com.statestreet.demo.java.struct;

import java.util.ArrayList;
import java.util.List;

/**
 * 循环不变式 是一句定义各变量所满足是条件
 * @author Administrator
 *
 */
//todo
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

	/**
	 * test
	 * @param head
	 * @return
	 */
	public static Node reverseLinkedListForLoop2(Node head) {
		Node newHead = null;
		Node currentHead = head;
		while(currentHead!= null){
			Node nextHead = currentHead.getNext();
			currentHead.setNext(newHead);
			newHead = currentHead;
			currentHead = nextHead;
		}
		return newHead;
	}

	public static Node reverseLinkedListForLoop3(Node head) {
		Node newHead = null;
		Node currentHead = head;
		while (currentHead != null){
			Node next = currentHead.getNext();
            currentHead.setNext(newHead);
            newHead = currentHead;
			currentHead = next;

		}
		return newHead;
	}


	/**
	 * 循环方式反转链表
	 * 1->2->3->4  => 4->3->2->1
	 * @param head
	 * @return
	 */
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

	/**
	 * 相邻节点反转
	 * 1->2->3->4 ==> 2->1->4->3
	 * 1->2->3->4->5 ==> 2->1->4->3->5
	 * @param head
	 * @return 返回反转后的头节点
	 */
	public static Node reverseLinkedListNearBy(Node head) {
		if(head==null || head.getNext()==null){//链表没有结点或者只有一个结点
			return head;
		}
		Node pre=head;
		Node preNext=head.getNext();
		Node cur=preNext.getNext();

		pre.setNext(cur);//cur可能为空
		preNext.setNext(pre);
		head=preNext;//新的头部; 2->1->3->4->5->6->7->8
		while(cur !=null && cur.getNext()!=null){
			preNext=cur.getNext();//交换时第二个结点元素
			cur=preNext.getNext();//下一次交换的第一个元素

			pre.getNext().setNext(cur);//2 1 3 5 6 7 8
			preNext.setNext(pre.getNext());//2 1 4 3 5 6 7 8
			pre.setNext(preNext);//2 1 4 3 5 6 7 8
			pre=preNext.getNext();//交换后的第二个元素
		}
		return head;

	}



	/**
	 * 第二种 利用虚节点
	 * 1->2->3->4->5 ==> 2->1->4->3->5
	 * @param head
	 * @return
	 */
	public static Node reverseLinkedListNearBy2(Node head) {
		Node dummyHead = new Node(0);
		dummyHead.setNext(head);

		Node p = dummyHead;
		while(p.getNext() != null && p.getNext().getNext() != null ){
			Node node1 = p.getNext();
			Node node2 = node1.getNext();
			Node next = node2.getNext(); // 1 2 3 4 5
			node2.setNext(node1); // 1
			                     //2 3 4 5
			node1.setNext(next); //2 1 3 4 5
			p.setNext(node2);//0 2 1 3 4 5
			p = node1;
		}

		return dummyHead.getNext();

	}

	public static Node reverseLinkedListNearBy11(Node head) {
		Node dummyNode = new Node(0);
		dummyNode.setNext(head);
		Node pointer = dummyNode;// 1 2 3 4
		while (pointer.getNext() != null && pointer.getNext().getNext() != null){
			Node node1 = pointer.getNext();
			Node node2 = node1.getNext();

			Node node3 = node2.getNext();

			node2.setNext(node1);
			node1.setNext(node3);// 2 1 3 4
			pointer.setNext(node2);
			pointer = node1;

		}
		return dummyNode.getNext();
	}

	/**
	 * 针对训练 相邻节点交换 这种做法好理解些,建立一个虚节点,每次循环交换两个数, 一轮一轮交换, p节点指向下轮交换的节点的前一个节点
	 * 1->2->3->4->5 ==> 2->1->4->3->5
	 * @param head
	 * @return
	 */
	public static Node revertNearByNodeTest(Node head){
		Node dummyNode = new Node(0);
		dummyNode.setNext(head);

		Node p = dummyNode;

		while(p.getNext() != null && p.getNext().getNext() != null){
			Node node1 = p.getNext();
			Node node2 = node1.getNext();
			Node node3 = node2.getNext();//1 2 3 4
			node2.setNext(node1);
			node1.setNext(node3);

			p.setNext(node2);
			p = node1;
		}
		return dummyNode.getNext();

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
/*		List<Integer> list = new ArrayList<>();
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
		Node head = deleteIfNode(node,2);
		printNode(head);*/

/*		Node newHead = reverseLinkedListForLoop(node);
		printNode(newHead);*/
		//相邻节点反转
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		Node node = createLinkedList(list);
		printNode(node);
		printNode(reverseLinkedListNearBy6(node));
//		printNode(reverseLinkedListForLoop(node));
//		printNode(reverseLinkedListNearBy5(node));
//		printNode(reverseLinkedListNearBy3(node));
//		printNode(testReverseForLoop_0122(node));
//		printNode(testReverse(node));
	}

	public static Node reverseLinkedListNearBy3(Node head) {
		Node dummyNode = new Node(0);
		dummyNode.setNext(head);

		Node p = dummyNode;
		while(p.getNext() != null && p.getNext().getNext() != null){
			Node node1 = p.getNext();
			Node node2 = node1.getNext();
			Node node3 = node2.getNext();//1 2 3 4 5
			node2.setNext(node1);
			node1.setNext(node3);// 2 1 3 4 5
			p.setNext(node2);
			p= node1;
		}
		return dummyNode.getNext();

	}

	/**test
	 * 1 2 3 4
	 * @param head
	 * @return
	 */
	public static Node reverseLinkedListNearBy4(Node head) {
		Node dummyNode = new Node(0);
		dummyNode.setNext(head);
		Node pointerNode = dummyNode;
		while (pointerNode.getNext() != null && pointerNode.getNext().getNext() != null){
			Node node1 = pointerNode.getNext();
			Node node2 = node1.getNext();

			Node node3 = node2.getNext();
			node2.setNext(node1); //0 2 1 3 4

			node1.setNext(node3);
			pointerNode.setNext(node2);
			pointerNode = node1;
		}
		return dummyNode.getNext();
	}

	/**
	 * test
	 * 1 2 3 4
	 * 2 1 4 3
	 * @param head
	 * @return
	 */
	public static Node reverseLinkedListNearBy5(Node head) {
		Node dummyNode = new Node(0);
		dummyNode.setNext(head);
		Node pointerNode = dummyNode;
		while(pointerNode.getNext() != null && pointerNode.getNext().getNext() != null){
			Node node1 = pointerNode.getNext();
			Node node2 = node1.getNext();

			Node node3 = node2.getNext();

			node2.setNext(node1); // 2 1 3 4
			node1.setNext(node3);
			pointerNode.setNext(node2);
			pointerNode = node1;
		}
		return dummyNode.getNext();
	}


	public static Node reverseLinkedListNearBy6(Node head) {
		Node dummyNode = new Node(0);
		dummyNode.setNext(head);
		Node pointer = dummyNode;
		while (pointer.getNext() != null && pointer.getNext().getNext() != null){
			Node node1 = pointer.getNext();//1 2 3 4
			Node node2 = node1.getNext();
			Node node3 = node2.getNext();

			node2.setNext(node1); // 2 1
			node1.setNext(node3); // 2 1 3 4
			pointer.setNext(node2);
			pointer = node1;
		}

		return dummyNode.getNext();

	}


	/**
	 * @Author xujl2
	 * @Date 16:42 2021/1/22
	 * @return com.statestreet.demo.java.struct.Node
	 **/
	private static Node testReverseForLoop_0122(Node head) {
		Node newHead = null;
		while (head != null){
			Node next = head.getNext();
			head.setNext(newHead);
			newHead = head;
			head = next;
		}
		return newHead;
	}

	/**
	 * 平时联系的方法
	 * @param head
	 */
	private static Node testReverse(Node head) {
		Node dummyNode = new Node(0);
		dummyNode.setNext(head);

		Node p = dummyNode;
		while(p.getNext() != null && p.getNext().getNext() != null){
			Node node1 = p.getNext();
			Node node2 = node1.getNext();
			Node node3 = node2.getNext();

			node2.setNext(node1);
			node1.setNext(node3);

			p.setNext(node2);
			p = node1;
		}
		return dummyNode.getNext();
	}

	/**
	 * 链表排序
	 * @param head
	 * @return
	 */
	private Node sort(Node head){

		return null;
	}


}
