package com.statestreet.demo.java.thread.basic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 主线程循环10次再子线循环20次，如此往复循环50次
 *  *  			抛出异常   	 特殊值  		阻塞 		超时 
	插入 		add(e) 		offer(e) 	put(e) 		offer(e, time, unit) 
	移除			remove() 	poll() 		take() 		poll(time, unit) 
	检查			element() 	peek() 		不可用 		不可用 
 * @author Administrator
 *
 */
public class ArrayBlockQueueDemoForCommunication {

	public static void main(String[] args) {
		Outputer3 o = new Outputer3();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					o.mainThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					o.subThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
	}

	
}
class Outputer3{
	private boolean shouldMain = false;
	ArrayBlockingQueue queue1 = new ArrayBlockingQueue<>(1);
	ArrayBlockingQueue queue2 = new ArrayBlockingQueue<>(1);

	{//匿名构造方法（创建几个对象就调用几次），在所有构造方法执行之前执行
		try {
			queue2.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void mainThread() throws InterruptedException {
		for(int j = 1;j<=50;j++) {
			queue2.put(1); 
				for(int i=1;i<=10;i++) {
					System.out.println("main thread sequence of " + i + " loop of " + j );
				}	
			queue1.take();
		}
	}
	
	public void subThread() throws InterruptedException {
		for(int j = 1;j<=50;j++) {
			queue1.put(1);
				for(int i=1;i<=20;i++) {
					System.out.println("sub thread sequence of " + i + " loop of " + j );
				}	
			queue2.take();
		}
	}
	
}