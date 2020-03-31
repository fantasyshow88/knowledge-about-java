package com.statestreet.demo.java.thread.basic.review20180206;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

import com.statestreet.demo.java.thread.basic.review20180206.ThreadTest.MyTimerTask;

public class ThreadTest {
	int i = 1;
	public static void main(String[] args) {
		
//		test1();
		
//		timerTest();
//		notifyWaitTest();
//		multiThreadOpTest();
		//atomicInteger,atomicIntegerArray,AtomicIntegerFieldUpdater
		
		//semaaphore 可以控制同时访问资源的并发线程数
		//countDownLatch 可以用百米赛跑的教练员和运动员之间的关系，运动员们等教练口令，走后教练员等运动员跑完再计成绩
		//Cyclicbarrier 等待大家都到了某一个点再继续下去做事情
		//Exchanger 线程之间交换东西
	}

	/*
	 * 4 个线程，两个队j 加1，两个对j 减一
	 */
	private static void multiThreadOpTest() {
		/*
		 * class Operator{
		 * 
		 * public synchronized void inc(){
		 * 			j++;
		 * }
		 * public synchronized void reduce(){
		 * 			j--;
		 * }
		 * 
		 * }
		 */
	}
	
	

	/*************************************************************************/
	private static void ThreadLocalTest() {
		/**
		 * 一个 threadLocal 只能存储一个对象
		 */
		ThreadLocal<String> t = new ThreadLocal<>();
		
	}

	/**********************************************************************************************************
	/**
	 * 子线程循环10次，主线程循环20次，如此循环10次
	 */
	private static void notifyWaitTest() {
		ThreadTest t = new ThreadTest();
		OutPut o = t.new OutPut();
		new Thread() {
			public void run() {
				for(int i= 0;i<10;i++) {
					o.main();
				}
				
			};
		}.start();
			
		new Thread() {
			public void run() {
				for(int i= 0;i<10;i++) {
					o.sub();
				}
				
			};
		}.start();
		
	}
	
	class OutPut{
		boolean shouldMain = false;
		
		public synchronized void  main() {
			while(!shouldMain) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int i = 0;i<10;i++) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("main ~~");
			}
			shouldMain = false;
			this.notify();
		}
		
		public synchronized void sub() {
			while(shouldMain) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int i = 0;i<20;i++) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("sub ~~");
			}
			shouldMain = true;
			this.notify();
			
		}
	}
	
	
	/***********************************************************************/

	private static void timerTest() {
		new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(new Date().getSeconds());
				}
			};
		}.start();
		Timer t = new Timer();
		t.schedule(new ThreadTest().new MyTimerTask() , 1000);
	}

	class MyTimerTask extends TimerTask{
		@Override
		public void run() {
			System.out.println("bombing");
			i++;
			Timer t = new Timer();
			t.schedule(new MyTimerTask(), 1000 + 1000 * (i%2));
		}
		
	}
	
	
	/**
	 * override run method
	 */
	private static void test1() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("runnable~");
			}
		}) {
			@Override
			public void run() {
				System.out.println("run method ~");
			}
		}.start();
	}
	
}
