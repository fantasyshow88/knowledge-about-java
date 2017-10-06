package com.statestreet.demo.java.thread.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 主线程循环10次再子线循环20次，如此往复循环50次
 * @author Administrator
 *
 */
public class ConditionCommunicationFor3 {

	public static void main(String[] args) {
		Outputer4 o = new Outputer4();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					o.one();
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
					o.two();
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
					o.three();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
	}

	
}
//wait() 和 notify()都要放在监视器里包括的地方，即该线程必须拥有该监视器才能执行该两个方法
//如果对象调用了wait方法就会使持有该对象的线程把该对象的控制权交出去，然后处于等待状态。
//如果对象调用了notify方法就会通知某个正在等待这个对象的控制权的线程可以继续运行。
//按照顺序 one --> two --> three 循环执行
class Outputer4{
	int i = 1;
	Lock lock = new ReentrantLock();
	Condition condition1 = lock.newCondition();
	Condition condition2 = lock.newCondition();
	Condition condition3 = lock.newCondition();
	
	public void one() throws InterruptedException {
		
		for(int j = 1;j<=50;j++) {
			lock.lock();
			try {
				while(i != 1) {
					condition1.await();
				}
				for(int i=1;i<=10;i++) {
					System.out.println("one thread sequence of " + i + " loop of " + j );
				}	
				i= 2;
				condition2.signal();
			}finally {
				lock.unlock();
			}
		}
	}
	
	public void two() throws InterruptedException {
		for(int j = 1;j<=50;j++) {
			lock.lock();
			try {
				while(i != 2) {
					condition2.await();
				}
				
				for(int i=1;i<=10;i++) {
					System.out.println("two thread sequence of " + i + " loop of " + j );
				}
				i= 3;
				condition3.signal();
			}finally {
				lock.unlock();
			}
		}
	}
	
	public void three() throws InterruptedException {
		for(int j = 1;j<=50;j++) {
			lock.lock();
			try {
				while(i != 3) {
					condition3.await();
				}
				
				for(int i=1;i<=10;i++) {
					System.out.println("three thread sequence of " + i + " loop of " + j );
				}	
				i= 1;
				condition1.signal();
			}finally {
				lock.unlock();
			}
		}
	}
}