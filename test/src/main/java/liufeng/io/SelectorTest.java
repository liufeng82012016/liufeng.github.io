package liufeng.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/8/23 18:12
 */
public class SelectorTest {

    public static void main(String[] args) throws Exception {
        SelectorTest selectorTest = new SelectorTest();
//        new Thread(() -> selectorTest.socket()).start();
        selectorTest.selectorServer();
    }

//    @Test
    public void selectorServer() throws Exception {
        int port = 8080;
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        System.out.println("open select server");
        serverChannel.configureBlocking(false);
        ServerSocket serverSocket = serverChannel.socket();
        // 绑定端口
        InetSocketAddress address = new InetSocketAddress(port);
        serverSocket.bind(address);
        // 开启selector
        Selector selector = Selector.open();
        // 注册监听事件
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("I am server!\r\n".getBytes());
        out:
        for (; ; ) {
            // 无限循环 ，等待（阻塞）事件
            int select = selector.select();
            while (select < 1) {
                System.out.println("event ex");
                continue out;
            }
            // 获取事件
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable() || key.isConnectable()) {
                        // 如果是连接事件
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        // 客户端注册读写事件
                        client.register(selector, SelectionKey.OP_READ);
                        System.out.println("Accepted connection from " + client);
                        client.write(msg);
                    } else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(36);
                        int read = client.read(byteBuffer);
                        System.out.println("receive " + read + "   " + new String(byteBuffer.array(), 0, read));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    key.cancel();
                    try {
                        // 在关闭时忽略
                        key.channel().close();
                    } catch (IOException cex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

//    @Test
    public void socket() {
        SocketChannel socketChannel;
        while (true) {
            try {
                socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
                socketChannel.configureBlocking(false);
                System.out.println("connect ");
                Selector selector = Selector.open();
                socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                while (true) {
                    selector.select();
                    // 获取事件
                    Set<SelectionKey> readyKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = readyKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        try {
                            if (key.isReadable()) {
                                SocketChannel client = (SocketChannel) key.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(36);
                                int read = client.read(byteBuffer);
                                System.out.println("receive " + read + "   " + new String(byteBuffer.array(), 0, read));
                            }else if (key.isWritable()){
                                ByteBuffer byteBuffer = ByteBuffer.wrap("i am client".getBytes());
                                socketChannel.write(byteBuffer);
                                System.out.println("sendMsg");

                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            key.cancel();
                            try {
                                // 在关闭时忽略
                                key.channel().close();
                            } catch (IOException cex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }

            } catch (Exception e) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


}


