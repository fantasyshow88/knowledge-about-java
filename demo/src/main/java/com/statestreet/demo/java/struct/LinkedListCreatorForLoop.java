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

	/**
	 * 针对训练 相邻节点交换 这种做法好理解些,建立一个虚节点,每次循环交换两个数, 一轮一轮交换, p节点指向下轮交换的节点的前一个节点
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
		printNode(revertNearByNodeTest(node));
		
	}
}
