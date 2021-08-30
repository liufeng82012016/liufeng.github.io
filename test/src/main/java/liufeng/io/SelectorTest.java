package liufeng.io;

import org.junit.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2021/8/23 18:12
 */
public class SelectorTest {

    @Test
    public void selectorServer() throws Exception {
        int port = 8080;
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
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
        for (; ; ) {
            try {
                // 无限循环 ，等待事件
                selector.select();
            } catch (IOException ex) {
                ex.printStackTrace();
                // handle exception
                break;
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
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
//                        System.out.println("Accepted connection from " + client);
//                        boolean connected = client.isConnected();
//                        System.out.println("connected " + connected);
                        client.write(msg);
                    } else if (key.isWritable()) {
                        // 如果发送数据
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer != null && buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                    } else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        client.write(msg);
                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        // 在关闭时忽略
                        key.channel().close();
                    } catch (IOException cex) {
                    }
                }
            }
        }
    }

    @Test
    public void socket() {
        final SocketChannel socketChannel;
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
            while (!socketChannel.finishConnect()) {
                System.out.println("连接正在建立，请稍等！");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            return;
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap("i am client".getBytes());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        socketChannel.write(byteBuffer);
                    } catch (Exception e) {
                        //
                    }
                }
            }
        }).start();
    }


    @Test
    public void test() {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("127.0.0.1", 8080));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.write("exit".getBytes());
            out.flush();
            out.close();
//            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//class Client {



//    public static void main(String[] args) {
//        new Thread(new ClientThread()).start();
//    }
//
//    public void checkStatus(String input) {
//        if ("exit".equals(input.trim())) {
//            System.out.println("系统即将退出，bye~~");
//            System.exit(0);
//        }
//    }


//}


//class ClientThread implements Runnable {
//    private SocketChannel sc;
//    private boolean isConnected = false;
//    Client client = new Client();
//
//    public ClientThread() {
//        try {
//            sc = SocketChannel.open();
//            sc.configureBlocking(false);
//            sc.connect(new InetSocketAddress("127.0.0.1", 8080));
//            while (!sc.finishConnect()) {
//                System.out.println("同" + "127.0.0.1" + "的连接正在建立，请稍等！");
//                Thread.sleep(10);
//            }
//            System.out.println("连接已建立，待写入内容至指定ip+端口！时间为" + System.currentTimeMillis());
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                Scanner scanner = new Scanner(System.in);
//                System.out.print("请输入要发送的内容：");
//                String writeStr = scanner.nextLine();
//                client.checkStatus(writeStr);
//                ByteBuffer bb = ByteBuffer.allocate(writeStr.length());
//                bb.put(writeStr.getBytes());
//                bb.flip(); // 写缓冲区的数据之前一定要先反转(flip)
//                while (bb.hasRemaining()) {
//                    sc.write(bb);
//                }
//                bb.clear();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            if (Objects.nonNull(sc)) {
//                try {
//                    sc.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        } finally {
//            if (Objects.nonNull(sc)) {
//                try {
//                    sc.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//    }
//}
