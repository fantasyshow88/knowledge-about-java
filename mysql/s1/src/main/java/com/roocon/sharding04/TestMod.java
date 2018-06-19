package com.roocon.sharding04;

import java.util.Scanner;

public class TestMod {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		System.out.println("分片的结果路由到：" + (num % 4));
		
		sc.close();
	}

}
