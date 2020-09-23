package com.statestreet.demo.java.nio.atgg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-09-22 11:30
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        String msg = "hello";

        boolean connect = socketChannel.connect(new InetSocketAddress("127.0.0.1",6666));
        if(!connect){
            if(!socketChannel.finishConnect()){
                System.out.println("做其他事情");
            }
        }
        ByteBuffer buf = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(buf);

        System.in.read();

    }
}
