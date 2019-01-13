package com.roocon.thread.ta1;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
	
	Lock lock = new ReentrantLock(false);
	
	
	public void a () {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + "   a");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public static void main(String[] args) throws IOException {
		System.in.read();
		System.out.println("EBD");
	}

}
