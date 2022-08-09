### RocketMQ学习

#### 概念
1. Github 官方文档地址：https://github.com/apache/rocketmq/tree/Master/docs/cn
2. 消息模型
   1. Producer：负责生产消息。发送方式：同步发送、异步发送、顺序发送、单向发送。同步和异步方式均需要Broker返回确认信息，单向发送不需要
   2. Broker：负责存储消息，在实际部署中对应一台服务器，每个Broker可以存储多个Topic的消息，每个Topic的消息也可以分片存储在不同的Broker。MessageQueue用于存储消息的物理地址，每个Topic的消息地址存储在多个Message Queue中。ConsumerGroup由多个Consumer实例组成
   3. Consumer：一般是后台系统负责异步消费，有2种消费形式：拉取式消费、推动式消费
3. Topic：表示一类消息的集合，每个主题包含若干条消息，每条消息只能属于一个主题，是RocketMQ消息订阅的基本单位
4. 代理服务器Broker Server
5. 名字服务Name Server
6. 拉取式服务Pull Consumer：应用主动调用Consumer的拉消息方法，主动权由应用控制
7. 推动式服务Push Consumer：Broker收到数据后会主动推送给消费端，该消费模式一般实时性较高
8. 生产者组Producer Group：同一类Producer的集合，这类Producer发送同一类消息且发送逻辑一致。如果发送的是事务消息且原始生产者在发送之后崩溃，则Broker服务器会联系同一生产者组的其他生产者实例以提交或回溯消息
9. 消费者组Consumer Group：同一类Consumer集合，消费同一类消息且消费逻辑一致。消费者组中所有实例必须订阅完全相同的Topic。RocketMQ支持2种消费模式：集群消费和广播消费
10. 集群消费：相同Consumer Group每个Consumer实例平均分摊消息
11. 广播消费：相同Consumer Group每个Consumer实例都接收全量消息
12. 普通顺序消息：普通顺序消费模式下，消费者通过同一个消息队列（Topic分区，称作Message Queue）收到的消息是有顺序的，不同消息队列收到的消息可能是无顺序的
13. 严格顺序消息：此模式下，消费者收到的所有消息都是有顺序的
14. 消息：消息系统所传输信息的物理载体，生产和消费数据的最小单位。每条消息必须属于一个主题。RocketMQ每条消息都有唯一的Message ID，且可以携带具有业务标识的key，系统提供了根据Message ID和Key查询消息的功能
15. 标签Tag：为消息设置的标志，用于同一主题下区分不同类型的消息。来自同一业务单元的消息，可以根据不同业务目的的在同一主题设置不同标签，标签能够有效保持代码的清晰度和连贯性，并优化RocketMQ的查询系统。消费者可以根据不同tag实现不同的消费逻辑
16. 顺序消息
    1. topic有序：整个topic消息有序消费。适合性能要求低，所有消息严格按照FIFO原则进行发布和消费
    2. 分区有序：对于指定topic，所有消息根据sharding key分区，同一个分区内按照FIFO顺序进行发布和消费
17. 消息过滤：消费者可以实现根据tag过滤，在Broker实现。减少consumer消耗，增加Broker消耗和实现复杂度
18. 消息可靠性
    1. Broker非正常关闭
    2. Broker异常crash
    3. os crash
    4. 机器掉电，但立即能回复供电
    5. 机器无法开机
    6. 磁盘设备
    7. 前4种情况下，RocketMQ保证消息不丢失或丢失少量数据（依赖刷盘方式是同步还是异步），5和6属于单点故障，且无法恢复，RocketMQ通过异步复制可保证99%消息不丢失。通过双写技术可避免丢失，但会一定程度上损失性能，适合对可靠性要求很高的场景
19. 至少一次：每个消息必须投递一次
20. 回溯消息
21. 事务消息：指应用本地食物和发送消息操作可以被定义到全局事务中，要么同时成功，要么同时失败
22. 定时消息（延迟队列）：消息到Broker后，不会立即被消费，等待特定时间投递给真正的topic。一共18个topic，可通过messageDelayLevel配置，该属性属于Broker，而不是topic。
23. 消息重试：consumer消费消息失败之后，让消息再消费一次。RocketMQ为每个消费者组设置一个重试队列，暂时保存因为各种异常导致consumer端无法消费的消息
24. 消息重投：生产者发送消失时，同步消息发送失败会重投，异步消息会重试，oneway没有任何保证。重投会保证消息尽可能成功，不丢失，但可能会造成消息重复
25. 流量控制
    1. 生产者流控：因为Broker处理能力达到瓶颈
       1. commitLog文件被锁时间超过osPageCacheBusyTimeOutMills（默认1000ms）
       2. Broker每隔10ms检查send请求队列头请求的等待时间，如果超过waitTimeMillsInSendQueue（默认200ms）
       3. Broker通过拒绝send请求方式实现流量控制
       4. 注意：生产者流控，不会尝试消息重投
    2. 消费者流控：消费能力达到瓶颈
       1. 消费者本地缓存消息数超过pullThresholdForQueue时，默认1000
       2. 消费者本地缓存消息大小超过pullThresholdSizeForQueue时，默认100MB
       3. 消费者本地缓存消息跨度超过consumeConcurrentlyMaxSpan时，默认2000
       4. 消费者流控的结果是降低拉取频率
26. 死信队列 Dead-Letter Message
    1. 消息达到最大重试次数后仍然消费失败，就会放入死信队列
    2. 可通过console控制台对死信队列中的消息进行重发来使得消费者实例再次进行消费
27. NameServer
    1. Broker管理：保存路由信息，提供心跳检测机制
    2. 路由信息管理：每个NameServer保存全量Broker信息，不胡想通讯。Broker向每个NameServer注册路由信息
28. Broker：主要负责消息存储、投递、查询，高可用。包含数个重要子模块
    1. Remoting Module：整个Broker的实体，负责处理来自client端的请求
    2. Client Manager：负责管理客户端和维护Consumer的Topic订阅消息
    3. Store Service：提供API接口以处理消息存储到物理硬盘和查询功能
    4. HA Service：高可用服务
    5. Index Service：索引服务
29. 部署架构
    1. NameServer无状态
    2. Broker
       1. 分为Master和Slave，Master可以有多个Slave，一个Slave只能对应一个Master。两者对应关系通过指定相同的BrokerName，不同的BrokerId来定义，BrokerId=0表示Master，非0表示Slave
       2. Master可以部署多台，每个Master与NameServer集群中每个节点建立长链接，定时注册Topic信息到所有NameServer
       3. 注意：只有BrokerId=1的Slave节点才会参与消息的读负载
    3. Producer随机与NameServer建立长连接，定期从NameServer获取Topic路由信息，并向提供Topic服务的Broker建立长连接，定时发送心跳。Producer无状态
    4. Consumer随机与NameServer建立长连接，定期从NameServer获取Topic路由信息，并向提供Topic服务的Master、Slave建立长连接，定时发送心跳

### 设计
1. 消息存储整体架构
   1. 消息存储整体架构
      1. CommitLog
         1. 默认大小为1G
         2. 文件名：长20位，保存偏移量，左侧补0
         3. 消息顺序写入日志文件
      2. ConsumeQueue
         1. 消息消费队列，主要为了提高消息消费的性能。RocketMQ基于Topic订阅，如果遍历commitLog检索消息效率非常低
         2. ConsumeQueue记录了指定Topic下队列消息在CommitLog的起始物理偏移量offset，消息大小size和消息Tag的hashCode值
         3. ConsumeQueue可看作是基于topic的commitlog索引文件，文件夹组织格式如下：topic/queue/file，具体路径为$HOME/store/consumequeue/{topic}/{queue}/{fileName}
         4. ConsumeQueue采用定长设计，每个条目共20字节
            1. 8字节物理偏移量
            2. 4字节的消息长度
            3. 8字节Tag的hashCode
         5. 单个文件由30w条目组成，可以像数组一样访问，每个ConsumeQueue约5.72M
      3. IndexFile
         1. 提供了根据key或时间区间查询消息的方法
         2. 文件存储位置在$HOME/store/index/fileName,fileName以创建时间戳命名
         3. 固定的单个IndexFile约400M，可存储2000W索引
         4. IndexFile此层存储设计为在文件系统中实现hashMap结构（Hash索引）
   2. 页缓存（PageCache）与内存映射（Mmap内存映射）
      1. PageCache 
         1. 是OS对文件的缓存，用于加速对文件的读写
         2. 通常来说，程序对文件顺序读写的速度几乎接近于内存读写速度，主要原因在于OS使用PageCache对读写访问操作进行了性能优化，将一部分内存作为PageCache。对于数据写入，OS会先写入cache，异步刷盘到磁盘。对于数据读取，如果一次读取文件未命中PageCache，OS从物理磁盘上访问文件的同时，会顺序对其他相邻块的数据文件进行读取
         3. ConsumeQueue数据较小且为顺序读取，即使消息堆积也不会影响性能
         4. CommitLog文件较大且有较多的随机读取，选择合适的系统IO调度算法如Deadline且块存储采用SSD，随机读性能会有提升
      2. 内存映射
         1. 利用NIO的FileChannel模型将磁盘文件直接映射到用户态的内存地址，对文件操作转化为对内存地址进行操作，从而极大的提高了文件读写效率（因为内存映射机制，RocketMQ采用鼎昌存储，方便一次将整个文件映射到内存）
   3. 消息刷盘
      1. 同步刷盘：只有消息真正持久化到磁盘后，Broker才会给Producer一个成功的ACK响应。性能会收到比较大的影响，多用于金融业务
      2. 异步刷盘：将消息写入到PageCache即可将成功的ACK返回给Producer
2. 通信机制
   1. 基本通讯流程
      1. Broker：启动后注册到所有NameServer，每隔30s定时上报Topic路由信息
      2. Producer：发送消息时，从本地缓存的TopicPublishInfoTable获取路由信息，没有则会从NameServer拉取，每隔30s向NameServer拉取一次路由信息
      3. Consumer：如上，获取路由信息。完成客户端负载均衡后，选择其中某个或几个消息队列来拉取信息并进行消费
   2. 通信类结构
      1. 协议涉及与编解码
      2. 消息通信方式和流程：同步、异步、单向
      3. Reactor多线程设计
         1. NettyBoss_%d  Reactor主线程，负责监听TCP网络请求，建立连接，创建SocketChannel，并注册到Selector
         2. NettyServerEPOLLSelector_%d_%d  Reactor线程池，监听网络数据
         3. NettyServerCodecThread_%d  worker线程池，负责SSL验证、编解码、空间检查、网络连接管理
         4. RemotingExecutorThread_%d  业务processor处理线程池
   3. 消息过滤
      1. Tag过滤：根据hashCode过滤，不保证准确，客户端需要对tag做equals判断，不相等则丢弃
      2. SQL92过滤：
   4. 负载均衡
      1. Producer负载均衡：使用selectOneMessageQueue()从TopicPublishInfoTable中的messageQueueList选择一个队列进行发送，具体策略在MQFaultStrategy中定义，其中有一个latencyFaultTolerance会对失败broker进行退避，是实现消息发送高可用的核心关键所在
      2. Consumer负载均衡：push模式只是对pull模式的一种封装，其本质实现为拉取线程在服务器拉取到一批消息后，提交到消费者线程池中，不停顿的继续向服务器拉取消息
         1. Consumer心跳包发送：通过定时任务不断向RocketMQ集群中所有Broker发送心跳（消费分组名称、订阅关系集合、消息通信模式和客户端id值等信息）。Broker将心跳信息回复在ConsumerManager的本地缓存变量consumeTable，同时将封装后的客户端网络通道信息保存在本地缓存变量channelInfoTable中，为之后做Consumer端的负载均衡提供可以依据的元数据信息
         2. Consumer负载均衡核心类-RebalanceImpl