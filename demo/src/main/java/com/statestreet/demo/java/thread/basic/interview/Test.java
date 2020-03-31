package com.statestreet.demo.java.thread.basic.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	
	public static void main(String[] args) {
		
		/**
		 * 模拟处理16行日志，下面代码生成了16个日志对象并打印需要 16s 修改下，增加
		 * 4个线程，让时间缩短为4s
		 */
		for(int i= 0;i<16;i++) {//这行代码不能改动
			String log = "" + (i+1);//这行代码不能改动
//			parse(log);
			add4threadsToParse(log);
		}
		pool.shutdown();
		
	}

	/**
	 * add 4 thread to print the log
	 * @param log
	 */
	private static void add4threadsToParse(String log) {
		//也可以用arrayblockqueue 装这些信息再给4个线程取用
		pool.execute(new Runnable() {
			@Override
			public void run() {
				parse(log);
			}
		});
	}

	/**
	 * original one to print log
	 * @param log
	 */
	private static void parse(String log) {
		System.out.println(log+ " : " + System.currentTimeMillis()/1000);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
