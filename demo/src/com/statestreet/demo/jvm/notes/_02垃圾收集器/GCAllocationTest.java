package com.statestreet.demo.jvm.notes._02垃圾收集器;

public class GCAllocationTest {

	public static final int _1M = 1024 * 1024;
	
	/**
	 * VM: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * 
	 * 堆大小20M,新生代大小10M,Eden与一个survivor区(其实总的有两个survivor区)比值为8
	 * 由以上信息得知：(Eden)8x+(survivor)1x+(survivor)1x = 10 * 1024,即新生代(包括一个eden和一个survivor)=8x + 1x=1024 * 9 = 9216k;
	 * 
	 * Heap
		 PSYoungGen      total 9216K, used 7128K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
		  eden space 8192K, 87% used [0x00000000ff600000,0x00000000ffcf6070,0x00000000ffe00000)
		  from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
		  to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
		 ParOldGen       total 10240K, used 4096K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
		  object space 10240K, 40% used [0x00000000fec00000,0x00000000ff000010,0x00000000ff600000)
		 Metaspace       used 2667K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 288K, capacity 386K, committed 512K, reserved 1048576K
	 * @param args
	 */
	public static void main(String[] args) {
		
		byte[] all1,all2,all3,all4;
		all1 = new byte[_1M * 2];
		all2 = new byte[_1M * 2];
		all3 = new byte[_1M * 2];
		all4 = new byte[_1M * 4];//出现一次Minor GC(当到该行时新生代再放不下4M了，只能通过分配担保机制分配到老年代去)
	}
	
}
