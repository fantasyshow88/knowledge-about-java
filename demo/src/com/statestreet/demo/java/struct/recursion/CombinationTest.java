package com.statestreet.demo.java.struct.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationTest {

	/**
	 * 
	 * @param selected 选中的集合组合数
	 * @param data 待选的集合
	 * @param n 从待选的集合中选n个数 
	 */
	public static void combination(List<Integer> selected, List<Integer> data,int n) {
		if(n == 0) {
			for (Integer i : selected) {
				System.out.print(i);
 				System.out.print(" ");
			}
			System.out.println();
			return;
		}
		if(data.isEmpty()) {
			return;
		}
		
		selected.add(data.get(0));
		combination(selected, data.subList(1, data.size()), n-1);
		
		selected.remove(selected.size()-1);
		combination(selected, data.subList(1, data.size()), n);;
		
		
	}
	
	public static void main(String[] args) {
		
		combination(new ArrayList(),Arrays.asList(1,2,3,4),2);
	}
	
}
