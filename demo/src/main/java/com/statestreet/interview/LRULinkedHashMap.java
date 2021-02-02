package com.statestreet.interview;

import java.util.*;

//扩展一下LinkedHashMap这个类，让他实现LRU算法
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
	// 定义缓存的容量
	private int capacity;
	private static final long serialVersionUID = 1L;

	// 带参数的构造器
	LRULinkedHashMap(int capacity) {
		// 调用LinkedHashMap的构造器，传入以下参数
		super(capacity, 0.75f, true);
		// 传入指定的缓存最大容量
		this.capacity = capacity;
	}

	// 实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
	@Override
	public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		System.out.println(eldest.getKey() + "=" + eldest.getValue());
		return size() > capacity;
	}

	public static void main(String[] args) throws Exception {

		// 指定缓存最大容量为4
		Map<Integer, Integer> map = new LRULinkedHashMap<>(4);
		map.put(9, 3);
		map.put(7, 4);
		map.put(5, 9);
		map.put(3, 4);
		map.put(6, 6);
		// 总共put了5个元素，超过了指定的缓存最大容量
		// 遍历结果
		for (Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
			System.out.println(it.next().getKey());
		}
	}
}
//
