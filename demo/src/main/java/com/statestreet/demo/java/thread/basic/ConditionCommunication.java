package com.statestreet.demo.java.thread.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 主线程循环10次再子线循环20次，如此往复循环50次
 * @author Administrator
 *
 */
public class ConditionCommunication {

	public static void main(String[] args) {
		Outputer2 o = new Outputer2();
		
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
//wait() 和 notify()都要放在监视器里包括的地方，即该线程必须拥有该监视器才能执行该两个方法
//如果对象调用了wait方法就会使持有该对象的线程把该对象的控制权交出去，然后处于等待状态。
//如果对象调用了notify方法就会通知某个正在等待这个对象的控制权的线程可以继续运行。
class Outputer2{
	private boolean shouldMain = false;
	
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public void mainThread() throws InterruptedException {
		
		for(int j = 1;j<=50;j++) {
//			synchronized(this) {
			lock.lock();
			try {
				while(!shouldMain) {
//					this.wait();
					condition.await();
				}
				for(int i=1;i<=10;i++) {
					System.out.println("main thread sequence of " + i + " loop of " + j );
				}	
				shouldMain = false;
//				this.notify();
				condition.signal();
			}finally {
				lock.unlock();
			}
//			}
		}
	}
	
	public void subThread() throws InterruptedException {
		for(int j = 1;j<=50;j++) {
//			synchronized(this) {
			lock.lock();
			try {
				while(shouldMain) {
//					this.wait();
					condition.await();
				}
				for(int i=1;i<=20;i++) {
					System.out.println("sub thread sequence of " + i + " loop of " + j );
				}		
				shouldMain = true;
//				this.notify();
				condition.signal();
			}finally {
				lock.unlock();
			}
//			}
		}
	}
	
}