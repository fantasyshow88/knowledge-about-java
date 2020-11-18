package com.statestreet.demo.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-11-18 9:34
 */
public class MyInBoundHandler1 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.err.println("MyInBoundHandler1");
        ByteBuf in = (ByteBuf) msg;
        in.writeBytes("->MyInBoundHandler1".getBytes());
        ctx.fireChannelRead(msg);
    }
}
