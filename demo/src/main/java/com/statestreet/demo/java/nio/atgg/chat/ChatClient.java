package com.statestreet.demo.java.nio.atgg.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-09-23 11:07
 */
public class ChatClient {

    private SocketChannel socketChannel;

    private Selector selector;

    private String userName;

    public ChatClient() throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        userName = socketChannel.getLocalAddress().toString();
        System.out.println(userName + " connected to server");
    }

    /**
     * read info from server
     */
    public void readInfo(){
        try {
            while (selector.select()>0){
                for(Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();iterator.hasNext();){
                    SelectionKey key = iterator.next();
                    if(key.isReadable()){
                        SocketChannel channel = (SocketChannel)key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array()).trim());
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * send info to server
     */
    public void sendInfo(String info){
        String msg = userName + "说:" + info;
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient();
        new Thread(client::readInfo).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            client.sendInfo(line);
        }
    }



}
