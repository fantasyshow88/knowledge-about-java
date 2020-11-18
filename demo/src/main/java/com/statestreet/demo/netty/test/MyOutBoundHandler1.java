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
public class MyOutBoundHandler1 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.err.println("MyOutBoundHandler1");
        ByteBuf in = (ByteBuf) msg;
        in.writeBytes("->MyOutBoundHandler1 write".getBytes());
        super.write(ctx, msg, promise);
    }
}
