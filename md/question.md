## MySQL数据库相关
1. MySQL有哪些常见的存储引擎？
2. 索引的原理是什么？
3. MySQL三种日志分别起到什么作用？（redoLog，undoLog，binLog）
4. 为什么选择B+树索引？
5. 什么情况下会出现索引失效？
6. 如何查看执行计划？
7. 如何优化SQL查询？
8. MySQL主从复制原理？
9. 数据库死锁的原因？如何快速定位并解决？
10. 事务隔离级别
11. 事务有哪些隔离级别？
12. 每种隔离级别会导致什么问题？
13. mysql 和 oracle默认情况下分别采用哪种隔离级别？
14. mysql如何解决幻读的？
## 中间件篇
1. MQ的相关问题
2. 你用过消息队列吗？用了哪个消息队列？
3. 在使用MQ的时候怎么确保消息 100% 不丢失？
4. 怎么解决消息的重复消费问题？
5. 如何实现顺序消息？
6. 如何解决引入消息后的事务问题？
7. 分库分表相关问题
8. 你用过分库分表吗？
9. 如何实现单个维度的非sharding-key 的查询问题？比如通过userID 作为 sharding-key，那么如何实现基于userName进行查询？（映射法、基因法）
10. 如何实现多个维度的多个字段非 sharding-key 如何查询？时间、用户名、类别等...
11. 多维度查询需要配合其他查询引擎，那么如何实现数据同步？如何保证双写的一致性？
12. 很多情况下并不是一开始就实现分库分表，等我们需要分库分表的时候如何进行数据迁移？
## Redis
1. 用过Redis吗？Redis支持哪些常见的数据结构？
2. Redis的线程模型
3. Redis如何保证数据不丢失的（如何实现持久化）？
4. AOF 和 RDB的实现原理？
5. Redis如何实现高可用？
6. 什么是缓存穿透，缓存击穿，缓存雪崩？分别如何预防解决？
## 分布式锁相关问题
1. 用过分布式锁吗？用什么实现的分布式锁？
2. 有没有用过基于redis分布式锁？有没有用过基于Zookeeper的分布式锁？
3. 如何给锁设置合理的加锁时间？锁超时了怎么办？Redisson看门狗的原理？
4. Redis如何解决集群情况下分布式锁的可靠性？
5. RedLock算法的原理？
## 并发编程篇
### 锁相关
1. 说一下synchronized 底层实现原理？
2. 说一下synchronized、volatile、CAS 的区别？
3. synchronized 和 Lock 有什么区别？
4. 什么是CAS，CAS的原理？
5. CAS有什么缺点？如何解决CAS中常见的ABA问题？
6. AQS的原理，AQS的实现过程是什么？
7. 有没有用过读写锁ReentrantReadWriteLock，说一下ReentrantReadWriteLock的原理？
### 线程池相关
1. 有哪几类线程池？如何创建线程池？
2. 解释一下线程池的核心参数，线程池的执行过程？
3. 如果提交任务时，线程池队列已满，这时候会发生什么？
4. 线程池线上参数如何优化？
## 分布式篇
### 分布式理论
1. 说说你对CAP理论的理解？
2. 说说你用过的注册中心，分别使用了什么模型？（AP，CP）
3. 说说你对BASE理论的理解？
### 分布式事务相关
1. 如何解决分布式事务问题？你用过哪些解决分布式事务的方案？
2. 说一下对2PC，3PC协议的理解？
3. 有没有用过SEATA，SEATA的实现过程是什么？
4. 如何基于MQ实现最终一致性？
### 实战篇
1. 如何设计接口并保证他们的安全?
2. 如何快速定位CPU溢出？
3. 如何设计实现一个限流组件？
4. 如何让系统能抗住预约抢购活动的流量压力？