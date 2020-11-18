package com.statestreet.demo.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-11-18 9:34
 */
public class MyOutBoundHandler2 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.err.println("MyOutBoundHandler2");
        ByteBuf in = (ByteBuf) msg;
        in.writeBytes("->MyOutBoundHandler2 write".getBytes());
        ctx.writeAndFlush(msg);
    }
}
