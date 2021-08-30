### NIO(Non-block io) 非阻塞IO
#### API发布历程
1. JDK1.4发布了java.nio包，它包含
    - 进行异步IO操作的缓冲区ByteBuffer等
    - 进行异步IO操作的管道Pipe
    - 进行异步IO操作的channel（异步/同步），包含ServerSocketChannel，SocketChannel等
    - 多种字符集的编码和解码能力
    - 基于非阻塞IO的多路复用器selector
    - 基于流行的Perl实现的正则表达式类库
    - 文件通道FileChannel
    - 缺陷1 没有统一的文件属性（读写权限等）
    - 缺陷2 API能力比较弱，目录级联/递归需要自己实现
    - 缺陷3 底层存储系统的一些高级API无法调用
    - 缺陷4 所有文件操作都是同步阻塞调用
2. JDK1.7发布NIO 2.0版，修改内容
    - 能够批量获取文件属性
    - 提供AIO功能，支持基于文件的异步IO操作和针对网络套接字的异步操作
    - 完成JSR-51定义的通道功能，包括对配置个多播数据报的支持等
#### 基础
1. 概念
    1. 缓冲区Buffer:NIO类库中所有数据都通过缓冲区操作。写入数据时，写入到缓冲区。读取数据时，读取到缓冲区。
    2. 通道Channel:通道是双向的，可以同时读写，而不像流只是单向的。它分为用于网络读写的SelectableChannel和文件操作的FileChannel。
    3. 多路复用器Selector：JDK使用epoll()来实现它，所以不受最大连接句柄的限制
2. Selector使用
    1. 创建Channel
    ```text
       SocketServerChannel serverChannel = SocketServerChannel.open();
    ```
    2. 绑定端口,设置为非阻塞
    ```text
       serverChannel.getSocket().bind(new InetSocketAddress(...));  
       serverChannel.configureBlock(false);  
    ```
    3. 创建多路复用器
    ```text
       Selector selector = Selector.open();  
       // 这里没看懂
       new Thread(new ReactorTask()).start();
    ```
    4. 注册连接事件    
    ```text
       SelectionKey key = serverChannel.register(selector,SelectionKey.OP_ACCEPT,ioHandler); 
    ```
    5. 轮询
    ```text
       Iterator iterator = selector.selectedKeys().iterator();
       while(iterator.hasNext()){
           SelectionKey selectedKey = (SelectionKey)iterator.next();
           // logic
       }
    ```
    6. 获取新的客户端连接，并设置为非阻塞
    ```text
       serverChannel.accept();
    ```