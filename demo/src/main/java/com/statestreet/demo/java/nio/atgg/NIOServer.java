package com.statestreet.demo.java.nio.atgg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-09-22 10:27
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            if(selector.select(1000) > 0){
                for(Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();iterator.hasNext();){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isAcceptable()){
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("客户端连接成功:" + socketChannel.getRemoteAddress());
                        System.out.println("客户端连接后 ，注册的selectionkey=" + selector.keys());
                        for (SelectionKey key : selector.keys()) {
                            System.out.println(key.channel());
                        }

                    }
                    if(selectionKey.isReadable()){
                        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
                        socketChannel.read(byteBuffer);
                        System.out.println("msg receive from client: " + new String(byteBuffer.array()));
                        byteBuffer.flip();
                        byte[] dst = new byte[byteBuffer.limit()];
                        byteBuffer.get(dst);
                        System.out.println("msg receive from client22: " + new String(dst));
                    }
                    iterator.remove();
                }

            }
            System.out.println("服务器等待1秒没连接");
        }


    }

}
