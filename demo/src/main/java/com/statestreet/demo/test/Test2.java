package com.statestreet.demo.test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class Test2 {

	public static void main(String[] args) {
		testLinkedHashMap();
	}

	private static void testLinkedHashMap() {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>(10,0.75f,true);
		map.put("bb", 22);
		map.put("aa", 11);
		map.put("cc",33);
		map.get("bb");
		for(Iterator<Entry<String, Integer>> ite = map.entrySet().iterator();ite.hasNext();) {
			Entry<String, Integer> entry = ite.next();
			System.out.println(entry.getKey()+"--" + entry.getValue());
		}
		System.out.println(map);
		
	}
}
