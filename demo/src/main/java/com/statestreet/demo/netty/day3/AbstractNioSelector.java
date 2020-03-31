package com.statestreet.demo.netty.day3;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

import com.statestreet.demo.netty.day3.pool.NioSelectorRunnablePool;

/**
 * 抽象selector线程类
 * 
 */
public abstract class AbstractNioSelector implements Runnable {

	/**
	 * 线程池
	 */
	private final Executor executor;

	/**
	 * 选择器
	 */
	protected Selector selector;

	/**
	 * 选择器wakenUp状态标记
	 */
	protected final AtomicBoolean wakenUp = new AtomicBoolean();

	/**
	 * 任务队列
	 */
	private final Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<Runnable>();

	/**
	 * 线程名称
	 */
	private String threadName;
	
	/**
	 * 线程管理对象
	 */
	protected NioSelectorRunnablePool selectorRunnablePool;

	AbstractNioSelector(Executor executor, String threadName, NioSelectorRunnablePool selectorRunnablePool) {
		this.executor = executor;
		this.threadName = threadName;
		this.selectorRunnablePool = selectorRunnablePool;
		openSelector();
	}

	/**
	 * 获取selector并启动线程
	 */
	private void openSelector() {
		try {
			this.selector = Selector.open();
		} catch (IOException e) {
			throw new RuntimeException("Failed to create a selector.");
		}
		executor.execute(this);
	}

	@Override
	public void run() {
		
		Thread.currentThread().setName(this.threadName);

		while (true) {
			try {
				wakenUp.set(false);

				select(selector);//boss线程：boss在此迎宾，一直等待(除非被wakeup,当被wakeup的时候就不会堵塞在这里，接下去继续往下执行就会执行taskqueue里面的任务-->注册了一个ready事件)。||work线程：超时500毫秒后返回，不会堵塞

				processTaskQueue();//执行各自的 taskqueue里面的任务

				process(selector);//boss线程行为：如果有ready事件到达后(即有客户端连接的话，不然一直堵塞)往work线程的taskQueue里面扔注册read事件的任务。|| work线程行为：从客户端socketchannel读数据并回写信息给客户端，所以work一直在这个循环里不会堵塞，即使没有可读的socketchannel也会一直在此循环里不停循环
			} catch (Exception e) {
				// ignore
			}
		}

	}

	/**
	 * 注册一个任务并激活selector
	 * 
	 * @param task
	 */
	protected final void registerTask(Runnable task) {
		taskQueue.add(task);

		Selector selector = this.selector;

		if (selector != null) {
			if (wakenUp.compareAndSet(false, true)) {
				selector.wakeup();
			}
		} else {
			taskQueue.remove(task);
		}
	}

	/**
	 * 执行队列里的任务
	 */
	private void processTaskQueue() {
		for (;;) {
			final Runnable task = taskQueue.poll();
			if (task == null) {
				break;
			}
			task.run();
		}
	}
	
	/**
	 * 获取线程管理对象
	 * @return
	 */
	public NioSelectorRunnablePool getSelectorRunnablePool() {
		return selectorRunnablePool;
	}

	/**
	 * select抽象方法
	 * 
	 * @param selector
	 * @return
	 * @throws IOException
	 */
	protected abstract int select(Selector selector) throws IOException;

	/**
	 * selector的业务处理
	 * 
	 * @param selector
	 * @throws IOException
	 */
	protected abstract void process(Selector selector) throws IOException;

}
