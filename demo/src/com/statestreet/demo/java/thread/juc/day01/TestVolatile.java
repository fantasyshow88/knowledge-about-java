package com.statestreet.demo.java.thread.juc.day01;

/*
 * 一、volatile 关键字：当多个线程进行操作共享数据时，可以保证内存中的数据可见。
 * 					  相较于 synchronized 是一种较为轻量级的同步策略。
 * 
 * 注意：
 * 1. volatile 不具备“互斥性”
 * 2. volatile 不能保证变量的“原子性”
 */
public class TestVolatile {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("start main thread~");
		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();
//		Thread.sleep(500);
		while(true){
			//加锁让main线程时刻从主内存中 fresh 最新的值 ，也能达到volatile的效果，只不过锁较重
			
/*			synchronized关键字强制实施一个互斥锁，使得被保护的代码块在同一时间只能有一个线程进入并执行。
			当然synchronized还有另外一个 方面的作用：在线程进入synchronized块之前，会把工作存内存中的所
			有内容映射到主内存上，然后把工作内存清空再从主存储器上拷贝最新的值。而 在线程退出synchronized块时，
			同样会把工作内存中的值映射到主内存，但此时并不会清空工作内存。这样一来就可以强制其按照上面的顺序运行，
			以 保证线程在执行完代码块后，工作内存中的值和主内存中的值是一致的，保证了数据的一致性！*/  
//			synchronized (td) {
				if(td.isFlag()){
					System.out.println(Thread.currentThread().getName() +  " : ------------------");
					break;
				}
				
//			}
		}
		
	}

}

class ThreadDemo implements Runnable {

//	private boolean flag = false;
	private volatile boolean flag = false;

	@Override
	public void run() {
		
		try {
			//以便让主线程先读取 flag 旧值为 false
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		//当执行了该赋值语句之后会把该值刷新到主内存中(不管有没有用volatile修饰，都会刷新到主内存，不同的点是修饰了的话就会让其他线程强制再从主内存load最新的值,不修饰的话用 的都还是旧值)，
		//如果在赋值前别的线程已经读取了该值(false)
		//并且没有用volatile修饰的话别的线程一直持有的就是旧值了（false），如果修饰了就会导致别的线程内缓存无效，
		//需要重新从主内存load最新的值（true）
		flag = true;
		
		System.out.println(Thread.currentThread().getName() + ": flag=" + isFlag());

	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}