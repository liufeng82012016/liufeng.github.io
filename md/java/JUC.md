# JUC包
### JUC包源码地址：md/java/源码/java/util/concurrent
### AQS

1. 描述：Abstract Queued Synchronizer，抽象队列同步器
2. 实现原理：
    1. AQS维护了volatile变量state，代表了加锁的状态，初始值为0；另外一个变量node，保存了当前持有锁的线程，默认为null
    2. 当thread1尝试获取锁
        1. 如果当前锁没有被占用，通过CAS修改state=1,node.thread=thread1
        2. 如果当前锁已经被使用，不同的锁有不同的实现
            1. 可重入锁判断是否当前线程持有锁，若是，通过CAS增加state的值；若否，则加入等待队列
            2. 不可重入锁？？？
    3. 当持有锁的线程释放锁，将唤醒队列中的下一个节点
3. 非公平锁：线程想要获取锁的时候，不判断是否有线程正在队列中排队，而是直接尝试通过CAS加锁
4. 公平锁：线程想要获取锁的时候，判断是否有线程正在队列中排队，如果有线程正在等待，不会尝试获取锁而是到CLH队列尾部进行排队
5. 独占锁
6. 共享锁
7. 源码
### CLH队列

### JUC包

1. 锁
2. 信号量
3. CountDownLatch和CyclicBarrier