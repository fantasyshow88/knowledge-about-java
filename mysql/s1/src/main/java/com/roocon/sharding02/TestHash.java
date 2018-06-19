package com.roocon.sharding02;

public class TestHash extends Object {
	
	
	public static void main(String[] args) {
		
//		String s = "acadsfsadc";
		
		TestHash t1 = new TestHash();
		TestHash t2 = new TestHash();
		
		System.out.println(t1.hashCode());
		System.out.println(t2.hashCode());
		
//		366712642
//		System.out.println(new TestHash("sss").hashCode());
		
	}
	
}
