package com.statestreet.demo.java.thread.basic;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {

	public static void main(String[] args) throws Exception {
		//basicTimerUsage();

	/*	只有一层	
	 * new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					System.out.println("bombing!");
					new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							System.out.println("bombing!");
						}
					}, 3000);
				}
			}, 2000);*/
		
		new Timer().schedule(new MyTimerTask(1), 2000);
		
		
		
		while(true) {
			Thread.sleep(1000);
			System.out.println(new Date().getSeconds());
		}
	}


	private static void basicTimerUsage() {
		//1s 后 bombing ,然后每隔2s bombing
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("bombing ~");
			}
		}, 1000,2000);
	}
	
	
}

class MyTimerTask extends TimerTask{
	private int i;
	
	public MyTimerTask(int i) {
		this.i = i;
	}
	
	public MyTimerTask() {
	}
	@Override
	public void run() {
		System.out.println("bombing!");
		i = i % 2;
		System.out.println("=========" + i);
		new Timer().schedule(new MyTimerTask(++i), 1000 + 1000 * i);
		
	}
	
}
