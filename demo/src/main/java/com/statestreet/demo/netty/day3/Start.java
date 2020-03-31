package com.statestreet.demo.netty.day3;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.statestreet.demo.netty.day3.pool.NioSelectorRunnablePool;
/**
 * 启动函数
 *
 */
public class Start {

	public static void main(String[] args) {
		//说说自己理解关于netty的原理（至少针对该代码）：
		/**
		  1.初始化1个boss线程和16个work线程,并往所传的两个cachepool提交各自的任务，执行以下核心代码：
		 * public void run() {
			Thread.currentThread().setName(this.threadName);
			while (true) {
				try {
					wakenUp.set(false);
	
					//1.boss线程：boss在此迎宾，一直等待(除非被wakeup,当被wakeup的时候就不会堵塞在这里，接下去继续往下执行就会执行taskqueue里面的任务-->注册了一个ready事件)。
					//2.work线程：超时500毫秒后返回，不会堵塞
					select(selector);
					
					//执行各自的 taskqueue里面的任务
					processTaskQueue();
					
					//boss线程行为：如果有ready事件到达后(即有客户端连接的话，不然一直堵塞)往work线程的taskQueue里面扔注册read事件的任务。
					//work线程行为：从客户端socketchannel读数据并回写信息给客户端，所以work一直在这个循环里不会堵塞，即使没有可读的socketchannel也会一直在此循环里不停循环
					process(selector);
				} catch (Exception e) {
					// ignore
				}
			}
	
		}
		
		 * 2.boss线程：监听服务器端的accept(前面有些误写成ready事件了)事件，每来一个客户端连接就把该客户端的read事件注册到16个work里的一个，当有16个客户端连接的时候每个work就对应一个客户端，当有32个客户端连接的时候每个work就对应2个客户端，依此类推
		 * boss线程的作用就是当有客户端连接的时候注册read事件到对应的work线程，然后当客户端有向服务器发消息的时候就由work线程来读取这部分信息，即work线程是真正干活的，boss线程是迎宾领班
		 * 
		 */
		
		//初始化线程
		NioSelectorRunnablePool nioSelectorRunnablePool = new NioSelectorRunnablePool(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		
		//获取服务类
		ServerBootstrap bootstrap = new ServerBootstrap(nioSelectorRunnablePool);
		
		//绑定端口
		bootstrap.bind(new InetSocketAddress(10101));
		
		System.out.println("start");
	}

}
