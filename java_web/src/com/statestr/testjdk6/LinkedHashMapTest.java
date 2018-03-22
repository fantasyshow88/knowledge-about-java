package com.statestr.testjdk6;

import java.util.LinkedHashMap;

public class LinkedHashMapTest {

	public static void main(String[] args) {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>(10,0.75f,true);
		map.put("bb", 22);
		map.put("aa", 11);
		map.put("cc",33);
		
		System.out.println(map);
		
	}
	
}
