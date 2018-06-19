package com.roocon.incre;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
	
	private AtomicInteger i = new AtomicInteger(0);
	
	
	public int get() {
		return i.getAndIncrement();
	}
	
	
	public static void main(String[] args) {
		Main m = new Main();
		
		Executor threadPool = Executors.newFixedThreadPool(10);
		
		for(int i = 0;i<10;i++) {
			threadPool.execute(new Increment(m));
		}
		
	}

}
