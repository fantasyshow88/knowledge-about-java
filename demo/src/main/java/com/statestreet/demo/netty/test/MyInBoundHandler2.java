package com.statestreet.demo.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-11-18 9:34
 */
public class MyInBoundHandler2 extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.err.println("MyInBoundHandler2");
        ByteBuf in = (ByteBuf) msg;
        in.writeBytes("->MyInBoundHandler2".getBytes());
        ctx.writeAndFlush(msg);//从当前节点逆序向前找outbound handler执
//        ctx.channel().writeAndFlush(msg);//从tail部开始向前找 outBound
    }
}
