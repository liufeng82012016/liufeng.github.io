## MySQL
### MySQL 存储引擎
1. InnoDB 和MyISAM的区别
   1. InnoDB支持事务，MyISAM不支持 
   2. 对于InnoDB每一条SQL语言都默认封装成事务，自动提交，这样会影响速度，所以最好把多条SQL语言放在begin和commit之间，组成一个事务； 
   3. InnoDB支持外键，而MyISAM不支持。对一个包含外键的InnoDB表转为MYISAM会失败； 
   4. InnoDB是聚集索引，数据文件是和索引绑在一起的，必须要有主键，通过主键索引效率很高。但是辅助索引需要两次查询，先查询到主键，然后再通过主键查询到数据。因此主键不应该过大，因为主键太大，其他索引也都会很大。而MyISAM是非聚集索引，数据文件是分离的，索引保存的是数据文件的指针。主键索引和辅助索引是独立的。 
   5. InnoDB不保存表的具体行数，执行select count(*) from table时需要全表扫描。而MyISAM用一个变量保存了整个表的行数，执行上述语句时只需要读出该变量即可，速度很快； 
   6. Innodb不支持全文索引(MySQL 5.6之后支持)，而MyISAM支持全文索引，查询效率上MyISAM要高； 
   7. InnoDB不能通过直接拷贝表文件的方法拷贝表到另外一台机器， myisam 支持 
   8. InnoDB表支持多种行格式， myisam 不支持 
   9. InnoDB是索引组织表， myisam 是堆表（数据在堆中存储，没有任何特定顺序，append到堆表最后一页，查询时根据索引排序）
    

2. InnoDB 4大特性 
   1. 插入缓冲 
   2. 二次写 
      1. MySQL一个页是16K，将数据写入磁盘以页为单位，而实际上以4K为单位写入磁盘，磁盘以扇区写入，因此不能保证16k页面原子性写入。系统中途宕机可能导致数据文件错误
      2. InnoDB在写入数据页之前，将之写入到一块独立的物理文件位置，然后再写入数据页。如果数据库宕机，那么可以先使用副本页还原该页，再使用redo log重做
   3. 自适应hash 
   4. 预读
3. 日志格式
   1. Statement：每一条会修改数据的sql都会记录在binlog中
   2. Row：不记录sql语句上下文相关信息，仅保存哪条记录被修改
   3. Mixedlevel: 是以上两种level的混合使用，一般的语句修改使用statment格式保存binlog，如一些函数，statement无法完成主从复制的操作，则 采用row格式保存binlog,MySQL会根据执行的每一条具体的sql语句来区分对待记录的日志形式




### mysql 索引相关
1. 为什么使用索引：索引是有序的，查询数据无需遍历，效率趋近于log2n
2. B+树和hash索引
   1. hash：把键通过hash算法换算成hash值
      1. 劣势：
         1. 不支持范围查询
         2. 不知查询后排序
         3. 不支持联合查询最左匹配规则
      2. 优势：
         1. 没有大量重复元素的等值查询，如：select id from table where name = "**";
   2. b+树：平衡的多叉树，从根节点到每个叶子节点的高度相差不超过1.而且同层的指针相互连接，是有序的
   3. b-树和b+树的区别
      1. b-树每个节点都包含key，data，并且叶子节点指针为nul，不保存关键字信息(？？？没太懂)
      2. b+树的叶子节点都包含关键字信息，及指向这些关键字的指针，且叶子节点按照关键字大小自小而大的顺序链接。
   4. 为什么使用b+树，而不是b-树作为索引
      1. b+树的根节点不包含数据，每一次IO读取的索引数据更多
      2. b+树的查询效率稳定为log2n，b-树最好为O(1)
3. 索引分类
   1. 聚集索引
   2. 覆盖索引
   3. 前缀索引
   4. 唯一索引


#### explain查看索引是否有效
- id: SELECT 查询的标识符. 每个 SELECT 都会自动分配一个唯一的标识符.
- select_type: SELECT 查询的类型.
- table: 查询的是哪个表
- partitions: 匹配的分区
- type: join 类型
- possible_keys: 此次查询中可能选用的索引
- key: 此次查询中确切使用到的索引.
- ref: 哪个字段或常数与 key 一起被使用
- rows: 显示此查询一共扫描了多少行. 这个是一个估计值.
- filtered: 表示此查询条件所过滤的数据的百分比
- extra: 额外的信息


#### 索引失效条件
1. like查询以%开头
2. or查询左右2侧没有同时以索引作为查询条件
3. 组合索引， 不是使用第一列索引：复合索引存储是按照创建的顺序存储的
4. 数据类型隐式转换，varchar不加引号时可能转换为int
5. 索引上使用is null ，is not null，不一定失效？？
6. not，<>, != 等操作符，可能失效（优化器有关，可能走）
7. 对索引字段进行计算，字段使用函数
8. mysql全表扫描比使用索引效率更高时
9. 左右链接，关联的字段编码格式不一样

#### 什么时候不建或者少建索引
1. 表记录太少
2. 经常写入，更新，删除的表
3. 重复且分布平均的字段，如订单状态？
4. 经常和主字段一起查询，但主字段索引值比较多的字段？？？

#### B+索引保存的数据量
1. MySQL采用页存储，每页大小为16K
2. 假设叶子节点大小为1K，主键bigint（8B、int则为4B），主键指针6B
3. 底层叶子结点数量=16k/1K=16
4. 中间或跟节点是索引节点=16K/（8+6）B=16*1024/14=1170
5. 两层B+树可以保存1170*16=18720数据
6. 三层B+树可以保存1170*1170*16=21902400条数据记录

#### 分库分表存在的问题
1. 事务问题
2. 跨库问题
3. 排序问题
4. 分页问题
5. 分布式ID
   1. 独立数据库自增id
   2. uuid
   3. 雪花算法
   4. 步长方式
   5. 发号器

### MySQL 锁

#### 分类

1. 表级锁：开销小，加锁快；不会出现死锁；锁定粒度大，发生锁冲突的概率最高，并发度最低。
2. 行级锁：开销大，加锁慢；会出现死锁；锁定粒度最小，发生锁冲突的概率最低，并发度也最高。
    1. record lock：锁定索引行
    2. gap lock：间隙锁
    3. next-key lock：record lock+gap lock。
        1. 规则
            1. 通常是前开后闭区间（8.0版本可能不包含临界值）
            2. 只有访问到的对象才会加锁
        2. 优化
            1. 命中唯一索引，退化为record key；命中普通索引，左右两边的gap lock+record lock
            2. 索引上的等值查询，没有命中则退化为gap lock
3. 页面锁：开销和加锁时间界于表锁和行锁之间；会出现死锁；锁定粒度界于表锁和行锁之间，并发度一般。

#### 比较

1. 行级锁优缺点
    1. 优点
        - 当在许多线程中访问不同的行时只存在少量锁定冲突。
        - 回滚时只有少量的更改
        - 可以长时间锁定单一的行。
    2. 缺点
        - 比页级或表级锁定占用更多的内存。
        - 当在表的大部分中使用时，比页级或表级锁定速度慢，因为你必须获取更多的锁。
        - 如果你在大部分数据上经常进行GROUP BY操作或者必须经常扫描整个表，比其它锁定明显慢很多。
        - 用高级别锁定，通过支持不同的类型锁定，你也可以很容易地调节应用程序，因为其锁成本小于行级锁定。

#### 符号表示

1. X锁(exclusive lock)：独占锁
    1. select ... from update
2. S锁(share lock)：共享锁
    1. select ... lock in share mode
3. IS(intention share lock):意向共享锁
4. IX(intention exclusive lock):意向排它锁

#### MySQL 锁怎么实现事务的隔离性

### 事务相关
1. 四种隔离级别
- Serializable (串行化)：可避免脏读、不可重复读、幻读的发生。
- Repeatable read (可重复读)：可避免脏读、不可重复读的发生。
- Read committed (读已提交)：可避免脏读的发生。
- Read uncommitted (读未提交)：最低级别，任何情况都无法保证。
2. 事务特性
- 原子性：即不可分割性，事务要么全部被执行，要么就全部不被执行。
- 一致性或可串性。事务的执行使得数据库从一种正确状态转换成另一种正确状态
- 隔离性。在事务正确提交之前，不允许把该事务对数据的任何改变提供给任何其他事务，
- 持久性。事务正确提交后，其结果将永久保存在数据库中，即使在事务提交后有了其他故障，事务的处理结果也会得到保存。
3. 并发控制
- LBCC：Lock-Based Concurrency Control，基于锁的并发控制
- MVCC：Multi-Version Concurrency Control：基于多版本的并发控制协议。纯粹基于锁的并发机制并发量低，MVCC是在基于锁的并发控制上的改进。读不加锁，读写不冲突，主要是在读操作上提高了并发量。
   - 快照读 (snapshot read)：读取的是记录的可见版本 (有可能是历史版本)，不用加锁（共享读锁s锁也不加，所以不会阻塞其他事务的写）
   - 当前读 (current read)：读取的是记录的最新版本，并且，当前读返回的记录，都会加上锁，保证其他事务不会再并发修改这条记录
4. MySQL默认情况下auto commit，不支持事务
5.  二阶段提交
   1. innoDB将数据页加载到buffer pool
   2. 将数据行记录写入undo log
   3. 更新buffer pool
   4. 执行期写入redo log（prepare，1阶段提交）
   5. 执行器写入binlog日志（binlog不存在或不完整，redo log 不生效，相当于事务回滚）
   6. 执行器写入redo log日志（commit 二阶段提交）




### 搭建主从数据库集群（5.7）
1. 修改主库配置文件(/etc/mysql/mysql.conf.d/mysqld.cnf)
   // 每个服务的serverId不一样
   server-id      = 135
   // 日志文件名称
   log-bin         = mysql-bin
   // 要同步的数据库名称
   binlog-do-db    = jinjiang_balance
   // 要忽略的数据库名称
   binlog-ignore-db= mysql,information_schema,performance_schema
   // 日志清理期限
   expire-logs-days= 7
2. 修改从库配置文件
   server_id      = 181
   replicate-do-db = jinjiang_balance
   replicate-ignore-db= mysql,information_schema,performance_schema
   relay-log       = mysql-relay-bin
   read-only       = 1

3. 同步主库信息
   CHANGE MASTER TO MASTER_HOST='192.168.31.73', MASTER_PORT=3306, MASTER_USER='root',MASTER_PASSWORD='root', MASTER_LOG_FILE='mysql-bin.000017',MASTER_LOG_POS=154;
   // 必须和【主数据库】的信息匹配。
   // 主库IP
   // 主库端口
   // 访问主库且有同步复制权限的用户
   // 登录密码
   // 从主库的该log_bin文件开始读取同步信息，主库show master status返回结果
   // 从文件中指定位置开始读取，主库show master status返回结果
   start slave;
   // 开始同步
4. 数据不同步
   stop slave;// 停止同步
   set global sql_slave_skip_counter=10;// 跳过错误（数量可调整）
   start slave;// 重启服务
### 面试题
1. MySQL数据库作发布系统的存储，一天五万条以上的增量，预计运维三年,怎么优化？
   1. 设计良好的数据库结构，允许部分数据冗余，尽量避免join查询，提高效率。
   2. 选择合适的表字段数据类型和存储引擎，适当的添加索引。
   3. mysql库主从读写分离。
   4. 找规律分表，减少单表中的数据量提高查询速度。
   5. 添加缓存机制，比如redis，memcached等。
   6. 不经常改动的页面，生成静态页面。
   7. 书写高效率的SQL。比如 SELECT * FROM TABEL 改为 SELECT field_1, field_2, field_3 FROM TABLE. 
2. char和varchar的区别
   1. char存储时，如果字符数不足，末尾以空格填充
   2. char获取时，会去掉尾部的空格，varchar不会
   3. char的内存占用和编码格式有关
3. text和blob的区别 
   1. text保存字符数据，blob保存二进制数据
4. count(*) 、 count(常量) 、 count(id) 、 count(字段)的区别
   1. count(*) 、 count(常量)直接统计表中的总行数，效率较高
   2. count(id) 还需要把数据返回给MySQL Server端进行累加计数
   3. count(字段)需要筛选不为null字段


### MySQL json操作
1. 数据查询
   1. SQL：select JSON_EXTRACT(columnName,'$.field') from tableName where conditions
   2. columnName：数据库哪一列
   3. $.field：要查询json内的哪个字段，可以继续.
   4. 等效SQL：select columnName -> '$.field' from tableName where conditions
2. json是否包含字段 JSON_CONTAINS
3. JSON_SET:更新如果key存在则覆盖，不存在则新增
4. JSON_INSERT:查询数据，不会替换已经存在的值
5. JSON_REPLACE:只是替换已经存在的值，不存在的不会管
6. JSON_KEYS:获取json文档中所有键key




