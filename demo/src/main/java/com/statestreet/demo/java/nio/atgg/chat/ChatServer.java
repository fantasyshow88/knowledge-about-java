package com.statestreet.demo.java.nio.atgg.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-09-22 16:41
 */
public class ChatServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public ChatServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 6666));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen(){
        try {
            while(selector.select()>0){
                for(Iterator<SelectionKey> ite = selector.selectedKeys().iterator();ite.hasNext();){
                    SelectionKey selectionKey = ite.next();
                    if(selectionKey.isAcceptable()){
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println(socketChannel.getRemoteAddress() + ": 上线~" );
                    }
                    if(selectionKey.isReadable()){
                        readData(selectionKey);
                    }
                    ite.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read data from client
     * @param selectionKey
     */
    private void readData(SelectionKey selectionKey) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel)selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.read(byteBuffer);
            String msg = new String(byteBuffer.array()).trim();
            System.out.println(msg);
            sendMsgToOtherClient(socketChannel, msg);

        } catch (IOException e) {
            try {
                System.out.println(socketChannel.getRemoteAddress() + ": 下线!");
                selectionKey.cancel();
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 转发数据到其它客户端
     * @param socketChannel
     * @param msg
     */
    private void sendMsgToOtherClient(SocketChannel socketChannel, String msg) throws IOException {
        for (SelectionKey key : selector.keys()) {
            SelectableChannel channel = key.channel();
            if(channel instanceof SocketChannel && channel != socketChannel){
                ((SocketChannel) channel).write(ByteBuffer.wrap(msg.getBytes()));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer();
        server.listen();
    }


}
