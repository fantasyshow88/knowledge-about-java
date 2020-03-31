package com.statestreet.demo.java.java8;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		int[] a = {1,2,3,0,0};
		System.arraycopy(a, 1, a, 3, 2);
		System.out.println(Arrays.toString(a));
	}
}
