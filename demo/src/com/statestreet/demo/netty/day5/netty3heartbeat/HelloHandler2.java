package com.statestreet.demo.netty.day5.netty3heartbeat;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

import io.netty.channel.ChannelHandler;

public class HelloHandler2 extends IdleStateAwareChannelHandler{
	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
		
		System.out.println(e.getState());
	}
}
