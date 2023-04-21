## Spring
1. Spring的优点
   1. 控制反转：开发者将bean交给Spring容器管理，并在需要的未知注入，实例之间不再强耦合
   2. AOP：Spring支持面向切面编程，公用系统业务，开发者可以将更多精力聚焦于业务
   3. 具有良好的扩展性和维护性，便于和其他框架配合使用
2. 什么是IOC容器
   1. 没有使用Spring时，程序员需要手动将依赖的对象set到目标对象，代码之间严重耦合，可能存在很多重复对象
   2. 使用IOC将对象交给容器管理，在需要的时候自动注入，并管理bean的生命周期
3. 什么是依赖注入？可以通过多少种方式完成依赖注入？
   1. @Resource（@Autowired）
   2. setter
   3. 构造器
4. 区分 BeanFactory 和 ApplicationContext？
   1. ApplicationContext在BeanFactory的基础上，提供了一些额外功能，如国际化支持、事件传递、资源访问、自动注册BeanPostProcessor
   2. BeanFactory使用懒加载，ApplicationContext在容器启动时实例化bean
5. 区分构造函数注入和 setter 注入
   1. 构造器多用于强依赖，如果存在循环依赖，无法解决
   2. setter多用于弱依赖，依赖晚于对象创建，构造器则相反
6. spring 提供了哪些配置方式？
   1. xml
   2. 基于注解，spring-annotation.xml
   3. java代码
7. Spring 中的 bean 的作用域有哪些?
   1. singleton
   2. prototype
   3. request
   4. session
   5. globalsession
8. 如何理解IoC和DI？
9. 将一个类声明为Spring的 bean 的注解有哪些?
   1. @Configuration
   2. @Component
   3. @Controller（@RestController）
   4. @Service
   5. @Repository
10. Spring 中的 bean 生命周期?
    1. 建BeanFactory类注释
11. 什么是 spring 的内部 bean？
    1. 一个bean仅作用域另一个bean的属性
12. 什么是 spring 装配？
13. 自动装配有什么局限？
    1. 不能自动装配基础数据类型、字符串和Class
    2. 模糊特性：自动装配不如显式装配精确
14. Spring中出现同名bean怎么办？
    1. 内部Bean可以重名
15. Spring 怎么解决循环依赖问题？
16. Spring 中的单例 bean 的线程安全问题？
    1. 创建使用synchronized关键字
    2. 业务代码由开发人员保证
17. 什么是 AOP？
    1. 面向切面编程，将相同逻辑代码抽离，提升代码复用
18. AOP 有哪些实现方式？
    1. jdk
    2. cglib
19. Spring AOP and AspectJ AOP 有什么区别？
    1. Spring AOP基于动态代理实现；只能在运行时织入；仅支持方法级编织；
    2. AOP基于静态织入；支持编译期织入、编译后织入、类加载后织入，不支持运行时织入；支持方法、字段、构造函数等织入；性能更好
20. Spring 框架中用到了哪些设计模式？
21. Spring 事务实现方式有哪些？
22. Spring框架的事务管理有哪些优点？
23. spring事务定义的传播规则
24. SpringMVC 工作原理了解吗?
25. 简单介绍 Spring MVC 的核心组件
## Spring MVC
## Spring Boot
## Spring Cloud

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
1. 为什么用mq
   1. 异步
   2. 削峰
   3. 解耦
2. mq有哪些缺点
   1. 增加系统和运维复杂度，可用性降低
   2. 重复消费问题
   3. 消息丢失
   4. 多个系统的数据一致性
3. 你用过消息队列吗？用了哪个消息队列？
4. 在使用MQ的时候怎么确保消息 100% 不丢失？
   1. 消息发送ack
   2. 消息消费ack
   3. mq系统高可用
5. 怎么保证消息队列高可用？
   1. 
6. 怎么解决消息的重复消费问题？
7. 如何实现顺序消息？
8. 如何解决引入消息后的事务问题？
9. ActiveMQ、RibbitMQ、RocketMQ、Kafka的比较
   1. 并发：前2者万级，后2者10w级
   2. 可靠性：前2者可靠性较好，后2者可靠性非常好
   3. 时效性：RibbitMQ在微秒级，其他3者在ms级
   4. 功能、社区和活跃度
      1. ActiveMQ功能比较完善，偶尔会丢消息，维护越来越少，主要用于解耦和异步，少用于大规模吞吐的场景
      2. RibbitMQ性能极好，延时低，功能比较完善，管理界面非常强大，更新维护较快；吞吐量稍低、erlang源码掌握和二次开发较难
      3. RocketMQ吞吐量高，功能比较完善，更新维护较快；没有按照JMS规范，如果被抛弃可能..
      4. Kafka功能较少，天然适合大数据实时计算和数据采集
10. 分库分表相关问题
11. 你用过分库分表吗？
12. 如何实现单个维度的非sharding-key 的查询问题？比如通过userID 作为 sharding-key，那么如何实现基于userName进行查询？（映射法、基因法）
13. 如何实现多个维度的多个字段非 sharding-key 如何查询？时间、用户名、类别等...
14. 多维度查询需要配合其他查询引擎，那么如何实现数据同步？如何保证双写的一致性？
15. 很多情况下并不是一开始就实现分库分表，等我们需要分库分表的时候如何进行数据迁移？
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
