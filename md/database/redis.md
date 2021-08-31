### Redis

### Redis 设计与实现第二版(基于Redis3.0开发版)
#### 1.数据结构与对象
1. Redis每个键值对都是由对象构成
    1. 对象包含字符串对象，列表对象，哈希对象，集合对象，有序集合对象
    2. 每个key都是字符串对象
    3. value可以是5种对象的任意一种
2. 简单动态字符串(Simple Dynamic String,SDS)
    1. 概念：Redis没有使用C语言的字符串(以空字符结尾的字符数组)来表示，而是构建了一种名为简单动态字符串的抽象类型。
    2. 用途：SDS会被用于存储可以修改的字符串值，C字符串用于存储不会变化的字面量；另外，SDS还被用作缓冲区
    3. 结构
     ```text
       struct sdshdr{
           // 记录buf[]已使用的字节的数量，等于sds保存的字符串的长度
           int len;
           // 保存buf[]未使用字节数量
           int free;
           // 字节数组，用于保存字符串
           char buf[];
       }
       注意：SDS遵循C以空字符结尾的习惯，保存空字符的空间不计入len。SDS会自动为空字符分配1的空间，并添加到字符串末尾。
    ```
    4. 与C字符串的区别
        1. C字符串并不记录自身的长度，每次获取长度都需要遍历，直到遇到空字符(\0)为止
        2. C字符串拼接时默认已经分配了足够的内存，实际上不足够时将造成缓冲区溢出(溢出空间的数据被意外修改，SDS不会，它会提前检查空间并自动扩容)
        3. 接2，C字符串除了缓存区溢出，每次拼接都需要重新分配内存，每次截取都需要释放内存(否则会内存泄漏)。SDS为了优化内存分配(频繁操作会影响性能)，使用了预分配和惰性释放策略
        4. C字符串不能保存用空字符分割的字符串，读取到第一个空字符就会认为该字符串已结束，不能保存二进制数据
        5. C可以<string.h>所有函数，SDS只能使用部分API
    5. 策略
        1. 内存预分配
            1. 如果修改后字符串长度(len)小于1MB，且len大于buf.length(小于等于时直接写入，无需扩容)，那么free=len,bug.length=free+len+1(\0).假设修改后len=13，buf.length=27
            2. 如果修改后字符串长度(len)大于等于1MB，将分配1MB的额外空间，即free=1MB
        2. 内存惰性释放：
        3. 二进制安全：SDS API会以二进制来存在buf[]数组的数据，没有任何限制、过滤、假设。读取和写入完全一致
3. 链表
    1. 结构
        ```text
           // 节点
           typedef struct listNode{
              // 前置节点
              listNode *prev;
              // 后置节点
              listNode *next;
              // 节点的值
              void *value;
           }listNode;
           // 链表的一个实现 adlist.h/list
           typedef struct list{
               listNode *head;
               listNode *tail;
               // 链表包含节点数
               unsigned long len;
               // 节点复制函数
               void *(*dup)(void *ptr);
               // 节点释放函数
               void *(*free)(void *ptr);
               // 节点值对比函数，判断节点的值和另一个输入是否相等
               void *(*match)(void *ptr,void *key);
           }list;
       ```
    2. 分类
        - 双端
        - 无环:head.prev 和tail.next都指向NULL，对链表的访问以NULL为终点
        - 带表头和表尾指针
        - 带链表长度计数器
        - 多态?? List<Object>??可以保存不同类型的值
    3. 用途
        1. 列表
        2. 发布/订阅
        3. 慢查询
        4. 监视器
4. 字典(DICT)，又称为符号表，关联数组，映射
    1. 哈希表 结构
         ```text
            typedef struct dictht{
                // 哈希表数组 dict.h/dicEntry
                dicEntry **table;
                // 哈希表大小
                unsigned long size;
                // 哈希表大小掩码，用于计算索引值 sizeMask=size-1
                unsigned long sizeMask;
                // 哈希表已有节点数量
                unsigned long used;
            }dictht;
        
        ```
    2. 哈希表节点结构
        ```text
            typedef struct dicEntry{
                // 哈希表数组 dict.h/dicEntry
                dicEntry *key;
                // 哈希表的值，可以是一个指针，也可以是uint64_t整数或者int64_t整数
                union{
                   void *val;
                   uint64_t u64;
                   int64_t s64;
                }v;
                // 下一个节点，用于解决哈希冲突
                struct dicEntry *next;
            }dicEntry;
        
        ```
    3. 字典结构
        ```text
            typedef struct dict{
                // 类型
                dictType *type;
                // 私有数据
                void privdata;
                // 哈希表 一般只使用其中一个，只有rehash的时候使用另外一个
                dictht ht[2];
                // rehash索引。如果值==-1,标识没有在rehash
                in trehashidx;
            }dict;
           // dictType
           typedef struct dictType{
               // 计算哈希值的函数，等价于java的hashCode()?
               unsigned int (*hashFunction)(const void *key);
               // 复制键的函数
               void *(*keyDup)(void *privdata,const void *key);
               // 复制值的函数
               void *(*valDup)(void *privdata,const void *obj);
               // 对比键的函数
               void *(*keyCompare)(void *privdata,const void *key1,const void *key2);
               // 销毁键的函数
               void *(*keyDestructor)(void *privdata,const void *key);
               // 销毁值的函数
               void *(*valDestructor)(void *privdata,const void *obj);
           }
    
        ```
    4. hash算法：Redis目前使用的是MurmurHash算法，即时输入数据有一定规律，算法仍然保持较好的随机分布性
    5. 解决hash冲突：Redis使用链地址法解决冲突，新数据添加到链表头部
    6. rehash：
    7. 渐进式rehash：分而治之，减少服务器压力
5. 跳跃表(skiplist)
    1. 介绍：跳跃表是一种有序数据结构，他通过在每个节点维护多个指向其他节点的指针，达成快速访问节点的需求。
    2. 效率：平均时间O(logN),最大时间O(N),还可以通过顺序性节点进行批量操作，效率接近平衡树
    3. 用途：有序集合，集群节点中作为内部数据结构
    4. 结构
        1. 跳跃表节点
            ```text
               typedef struct zskiplistNode{
                   // 层,指向一个其他元素。数组长度越大，访问越快
                   // 每次创建一个跳跃点节点的时候，都根据幂次定律生成一个介于1-32的随机数作为level数组的大小。
                   struct zskiplistLevel{
                       // 前进指针: 指向表尾
                       struct zskiplistNode *forward;
                       // 跨度：用于记录2个节点的距离，值越大表示距离越远。指向NULL的span=0
                       // 他用来计算排位，在查找过程中，将沿途的跨度累加起来，就是目标节点在跳跃表的排位
                       unsigned int span;
                   }level[];
                   // 后退指针，只有最底层有效，用于由表尾向前遍历
                   struct zskiplistNode *backward;
                   // 分值：多节点的分值可以相等
                   double score;
                   // 成员对象：多节点的对象唯一，先按照分值排序，再按照对象的大小排序。这个对象是一个字符创对象？？
                   robj *obj;
               }zskiplistNode;
            ```
        2. 跳跃表
            ```text
               typedef struct zskiplist{
                   // 表头/尾
                   struct zskiplistNode *head,*tail;
                   // 表中节点的数量
                   unsigned long length;
                   // 表中层数最大的值
                   int level;
               }zskiplist;
            ```
    5. 总结
        1. 跳跃表是一个最多32(并非不能增多，而是认为够用，实际上很少使用这么多层，使用层数保存在level)层的数组，每个数组引用一个跳跃表节点。
        2. 每次查询从最高层往下遍历，遍历到最底层后，由表头向表尾方向遍历
         
6. 整数集合(INTSET)
    1. 介绍： Redis用于保存整数值的集合抽象数据结构，可以保存int16_t,int32_t,int64_t的值，并保证无重复元素。
    2. 结构
    ```text
       typedef struct intset{
           // 编码方式
           uint32_t encoding;
           // 集合包含的元素数量
           uint32_t long length;
           // 保存元素的数组，元素从小到大排列，并且不包含重复项；数组并不保存int8_t的值，而是取决于encoding的值
           int8_t contents[];
       }zskiplist;
    ```
    3. 扩容规则
        1. 类型扩容：encoding包含INtSET_ENC_16,INtSET_ENC_32,INtSET_ENC_64分别对应JAVA的short,int,long.只要其中一个值范围扩大，所有值的类型都会改变。
        2. 数组长度扩容：
        3. 一旦完成类型扩容，就永远无法降级
    4. 用途： 作为集合的底层实现之一，当一个集合只有整数数值，且元素数量不多时采用
7. 压缩列表(ziplist)
    1. 介绍：压缩列表是Redis为了节约内存而开发的，由一系列特殊编码的连续内存块组成的顺序性数据结构。一个压缩列表可以包含任意多个节点。每个节点可以保存一个字节数组或者一个整数值。
    2. 用途：列表键和哈希键的底层实现之一。当列表键只包含少数列表项，而且每个列表项要么是小整数值，或者是短字符串时使用；对应的，如果一个哈希键的键值对都满足上述要求，也会用作实现哈希键。
    3. 结构
    ```text
       // 这个结构不准确
       typedef struct ziplist{
           // 记录整个压缩列表占用的内存字节数：在对压缩列表内存重分配，或者计算zlend位置时使用
           uint32_t zlbytes;
           // 记录压缩列表表尾节点距离压缩列表的起始地址有多少字节。通过偏移量，可以直接获取尾结点的地址
           uint32_t zltail;
           // 记录压缩列表包含的节点的数量：如果这个值等于最大值65535时，必须遍历列表才知道节点数量
           uint16_t zlen；
           // 压缩列表的各个节点
           entry... X
           // 特殊值OxFF，用于标记压缩列表的末端
           uint8_t zlend;
       }zskiplist;
    ```
    4. 压缩节点构成
        1. 字节数组
            1. 长度小于等于63(2^6-1)的字节数组
            2. 长度小于等于16383(2^14-1)的字节数组
            3. 长度小于等于4294967295(2^32-1)的字节数组
        2. 整数值
            1. 4位长，0-12的无符号整数
            2. 1字节长的有符号整数
            3. 3字节长的有符号整数
            4. int16_t类型整数
            5. int32_t类型整数
            6. int64_t类型整数
        3. 结构
        ```text
           // 这个结构不准确
           typedef struct entry{
               // 1/5字节。如果上一个节点的长度小于254，使用1字节保存；否则第一字节保存0xFE，后面4个字节保存上一个节点的内容
               previous_entry_length;
               // 记录节点content属性保存的数据类型和长度
               encoding;
               // 节点值，和encoding一致
               content;
           }zskiplist;
        ```
        4. 连锁更新
            1. 概念：由于靠前的节点使用空间发生变化，导致所有后续节点需要重新分配内存，可能在新增/删除/更新节点的时候发生
            2. 说明：连锁更新的概率并不高，虽然最坏复杂度为O(N^2),但是平均复杂度为O(N)，不会很影响性能
8. 对象
    1. 对象的类型和编码
        1. 结构
        ```text
           typedef struct redisObject{
               // 类型
               unsigned type;    
               // 编码
               unsigned encoding;    
               // 指向底层实现数据结构的指针
               void *ptr;    
           }robj;
        ```
        2. 类型：Redis的键对象为字符串对象，值对象可以是以下任意一种(type 键)
            - 字符串对象
            - 哈希对象
            - 列表对象
            - 集合对象
            - 有序集合对象
        3. 编码和底层实现：编码方式记录在encoding字段，它可以是以下任意一种。每种类型至少使用了2种编码
            - REDIS_ENCODING_INT        long类型整数
            - REDIS_ENCODING_EMBSTR     embstr编码的动态字符串
            - REDIS_ENCODING_RAW        简单动态字符串
            - REDIS_ENCODING_HT         字典
            - REDIS_ENCODING_LINKEDLIST 双端链表
            - REDIS_ENCODING_ZIPLIST    压缩表
            - REDIS_ENCODING_INTSET     整数集合
            - REDIS_ENCODING_SKIPLIST   跳跃表
    2. 字符串对象
        1. 编码(INT(整数)
        2. EMBSTR(小于等于32字节的字符串，连续内存，和ptr连续，不需要指向额外指针，一次内存分配和释放)
        3. RAW(大于32字节的字符串，redisObject和*ptr指向的地址分开存储 2次内存分配和释放) 3种编码)
        4. 字符串命令
            - SET
            - GET
            - APPEND
            - INCRBYFLOAT
            - INCRBY
            - DECRBY
            - STRLEN
            - SETRANGE
            - GETRANGE
        5. 备注：Redis的EMBSTR没有修改程序，修改后会变成RAW字符串
    3. 列表(底层由ziplist或linkedlist实现)
        1. ziplist编码条件(2者同时满足，配置文件可改)
            1. 列表对象保存的所有字符串对象都小于64字节
            2. 列表对象元素小于512个
        2. 命令列表(略)
    4. 哈希(底层由ziplist或hashtable实现)
        1. ziplist写入过程： 每当新的键值对要加入到哈希对象时，会先将保存了键的压缩节点推入压缩列表表尾，再将值的压缩节点推入到表尾
        2. ziplist编码特点(哈希)
            1. 保存键值对的2个节点总是临近，键前值后
            2. 先加入的键值对在前，后加入的键值对在后
        3. hashtable编码：使用字典键值对来保存，键和值都使用字符串对象保存
        4. ziplist编码条件(2者同时满足，配置文件可改)
            1. 列表对象保存的所有字符串对象都小于64字节
            2. 列表对象元素小于512个
    5. 集合对象(底层由Intset和hashtable实现)
        1. intset使用条件(2者同时满足，配置文件可改)
            1. 列表对象保存的所有元素都是整数值
            2. 列表对象元素小于512个
        2. hashtable实现：key都是字符串对象，value为NULL(Java的Set的实现方式也由HashMap实现)
    6. 有序集合对象(底层由ziplist和skiplist实现)
        1. ziplist保存方式：由连续2个节点保存，值在前，score在后；score小的元素靠近表头，score大的元素靠近表尾
        
            
            
 
            
#### 2.单机数据库的实现
#### 3.多机数据库的实现
#### 4.独立功能的实现