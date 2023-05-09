## elasticsearch 使用笔记

### 参考文档
1. logstash 中文文档：http://doc.yonyoucloud.com/doc/logstash-best-practice-cn/index.html
1. logstash 官方文档：https://www.elastic.co/guide/en/logstash/index.html

### 工作原理
1. 倒排索引：相对于根据文章查找关键词，倒排索引是根据内容查找对应的文章
   1. 单词词典：记录所有文档的单词，记录单词到倒排列表的关联关系。该文件较大，可以使用B+树或哈希拉链发实现
   2. 倒排索引项：
      1. 文档ID
      2. 词频：单词在文档中出现的频率，用于相关性评分
      3. 位置：单词在文档中的位置，用于语句搜索
      4. 偏移：记录单词的开始、结束位置，用于高亮展示
2. 分段存储思想：早期的设计是一个文档建立一个大的倒排索引，每次更新都需重建索引；更改为一个索引文件分段拆分为多个子文件，只需要对应段即可
   1. 查询时查询所有段中满足条件的数据，然后对每个段的数据集合并
   2. 定期合并段，将整体保持在一个合理的大小范围（可配置）
3. 数据写入过程
   1. client向某个节点Node发送写请求
   2. Node通过文档信息，将请求转发到主分片的节点上
   3. 主分片处理完毕，通知部分分片同步数据，向Node返回成功消息
      1. 先将数据写入内存buffer
      2. 然后将数据写入translog日志文件（此时客户端无法搜索到）（经os cache，每5s持久化一次，可能会导致丢数据）
      3. es定时将buffer数据刷入磁盘（先写入os cache（这时候能搜索到），然后进入磁盘），产生segment file（默认每秒1次）（segment file达到一定数量后，会执行merge操作将文件合并）
      4. translog日志文件达到一定大小，会执行一次commit操作（默认30分钟也会commit一次），将os cache缓存的所有数据刷新到磁盘，并更新commit point磁盘文件（es也提供了API，可以手动执行flush）
      5. translog用于恢复内存buffer和os cache未刷盘数据
   4. Node将消息返回给客户端
4. 数据搜索过程
   1. client向集群发送请求，集群随机选择节点Node处理请求
   2. Node先计算文档在哪个主分片上，然后随机选择一个副本完成请求
   3. 如果无法计算数据处于哪个分片，就遍历所有分片
   4. 深翻页问题：ES每一次查询，都需要先查询、合并结果、打分、排序，然后返回分页数据。每次消耗的数据都很多。解决方案：缓存
5. 数据删除
   1. 写入.del文件，数据查询时过滤，merge时删除数据
6. master选举（7.x之后采用raft选举）
   1. 前提：
      1. 只有候选主节点才能成为主节点
      2. 最小主节点数的目的是防止脑裂
   2. 角色
      1. 主节点
      2. 候选节点
      3. 投票节点
      4. 专用主节点
      5. 仅头片节点
   3. 触发选举的条件
      1. 当master-eligible节点数量小于法定票数：当主节点侦测到候选节点数量小于法定票数时，会主动放弃主节点身份
      2. 主节点宕机
   4. 选举过程
      1. 节点启动后互相注册（图），所有候选节点排序，排名第一的节点是准master节点
         1. clusterStateVersion越大，优先级越高
         2. 节点id（启动节点时随机生成）越小，优先级越高
      2. 投票节点向准master节点投票（非准master节点拒绝收到的投票），如果排名第一的节点超过指定票数，则成为master节点，同步节点信息
      3. 如果投票超时未响应，可能会投出第二票导致脑裂（raft协议每周期只能投一票）
7. 概念
   1. TF-IDF
      1. TF（term frequency）词频：一个词在文档中出现的频率
      2. IDF Inverse Document Frequency 反向文档频率：一个词无论出现多少次，都不重要，比如'我'

### 面试
1. 深翻页问题
   1. 不允许深度翻页，或默认深度翻页性能较差
   2. 使用scoll机制，生成所有数据的快照，通过游标获取下一页；性能较好，但是新数据不会展示给用户，且这种模式只能一页页滚动不支持随意翻页
2. 性能优化
   1. OS cache缓存越大，搜索命中缓存高，性能可以由秒提升到毫秒级；建议OS cache容量占数据大小50%以上，100%更好；建议只写入搜索字段
   2. 批量提交
   2. 优化硬盘
   3. 减少副本数量
3. 查询优化
   1. 设计优化
      1. 基于日志模版创建索引
      2. 使用别名进行索引管理
      3. 在压力小的时候做force_merge操作，以释放空间
      4. 采用冷热数据分离存储
      5. 采用curator进行生命周期管理
      6. 仅针对需要分词的字段，合理设置分词器
      7. Mapping阶段充分结合各个字段的属性，是否需要检索，是否需要存储
   2. 写入优化
      1. 写入前设置副本数量为0
      2. 入前关闭 refresh_interval 设置为-1，禁用刷新机制
      3. 写入过程中：采取 bulk 批量写入
      4. 写入后恢复副本数和刷新间隔
      5. 尽量使用自动生成的id 
   3. 查询调优 
      1. 禁用 wildcard
      2. 禁用批量 terms（成百上千的场景)
      3. 充分利用倒排索引机制，能 keyword 类型尽量 keyword
      4. 数据量大时候，可以先基于时间敲定索引再检索
      5. 设置合理的路由机制
      6. 数据预热：用户可能频繁访问的数据，提前查询加载到OS cache中
4. 生产环境怎么部署的？
   1. 5台机器，6核64G
   2. 每日2000w条数据，日增500MB
   3. 设置有5个索引，8个分片
### 部署+实战
1. 搭建单机版
    1. 下载压缩包（官网地址：https://www.elastic.co/cn/downloads/elasticsearch），解压
    2. 修改配置文件
        1. cluster-name   如果搭建集群，多个节点的集群名称需要相同
        2. node.name   节点名称，多个节点需要使用的名称
        3. network.host  监听的ip
    3. 创建用户（es不能以root身份启动）
        1. useradd es
        2. passwd es
        3. chown -R es /..
        4. su es
        5. ./elasticsearch &
    4. 解决报错
        1. 虚拟内存不足
        ```text
           #vim /etc/sysctl.conf 
           追加 vm.max_map_count=655360
           # sysctl -p
        ```
2. 搭建集群
    1. 复制单机版的程序文件和配置文件（是否可以指定配置文件启动？）
    2. 修改配置文件http.port和transport.tcp.port，默认值分别为9200,9300；
    3. discovery.seed_hosts: ["127.0.0.1:9300","127.0.0.1:9300","127.0.0.1:9300"]
    4. cluster.initial_master_nodes: ["node-1","node-2","node-3"]
    5. gateway.recover_after_nodes: 3 ;// 数量与集群节点数有关
    6. node.master: true ;// 是否可以成为主节点
    7. node.data: true   ;// 是否保存数据
    8. 启动并查看集群健康状态

3. 搭建skywalk-es服务
    1. 搭建单机版，默认使用H2数据库，这里使用mysql/elasticsearch作为数据库都可以。
        1. 官网下载（http://skywalking.apache.org/downloads/），解压
        2. 配置
            1. storage:
                 selector: ${SW_STORAGE:elasticsearch7}   设置要选用的配置
            2. elasticsearch7:
                   nameSpace: ${SW_NAMESPACE:"es-cluster"}  es集群名称（单机也是这个）
                   clusterNodes: ${SW_STORAGE_ES_CLUSTER_NODES:127.0.0.1:9200}  es地址
                   protocol: ${SW_STORAGE_ES_HTTP_PROTOCOL:"http"} 略
                   trustStorePath: ${SW_STORAGE_ES_SSL_JKS_PATH:"../es_keystore.jks"} 不知道为啥写这个
        3. 初始化或者启动，项目已经打包好了脚本。startup.sh启动所有，也可以单独启动wep/oap.
        4. 查看日志是否启动成功。日志在 ../logs文件夹下
    2. 其他服务启动加载探针 java  -javaagent:/opt/jinjiang/skywalking/skywalk/agent/skywalking-agent.jar -Dskywalking.agent.service_name=kline-service -Dskywalking.collector.backend_service=127.0.0.1:11800  -jar
4. 搭建kibana
    1. 下载与es相同的安装包，并安装node.js（可以选用该版本发布之前的node.js版本）
    2. 解压并修改配置文件 elasticsearch.hosts: ["http://127.0.0.1:9200"]
    3. 启动

5. 搭建elk日志系统
    1. 下载logstash。（es,kibana,logstash属于同一个公司，发布的版本号也完全相同。）
        1.1 解压并修改配置文件，启动
        1.2 启动参数 -f 指定配置文件
        1.3 启动参数 -t 测试配置文件是否正确
    
6. 实现自定义搜索服务
7. ES学习笔记
    1. es为java客户端提供了2种api，客户端通过9300端口（配置文件的transport.tcp.port）与服务端通信。
        1. 节点客户端(node	client)： 节点客户端以无数据节点(none	data	node)身份加入集群，换言之，它自己不存储任何数据，但是它知道数据在集群中的具体位置，并且能够直接转发请求到对应的节点上。
        2. 传输客户端(Transport	client)： 这个更轻量的传输客户端能够发送请求到远程集群。它自己不加入集群，只是简单转发请求给集群中的节点。
    2. 可以通过RestAPI和es server进行通信（数据格式为json），默认端口为9200，可通过配置文件http.port进行修改
        1. 查询节点 文档数量 curl -XGET http://localhost:9200/_count?pretty
        2. 查询集群健康程度  curl -XGET 'http://localhost:9200/_cluster/health?pretty'
        3. ?pretty 会将返回json文档格式化为更方便查看的格式
        4. 许多文档或者博客会省略掉 curl ... protocol://ip:port 格式，简写成 GET /_count?pretty
    3. 可以将es的数据与关系型数据库相对应（相似而非全等）
        1. index -- table  复数（indices	|  indexes）
        2. type  -- 关键查询条件？
        3. mapping -- 表结构定义，定义了type中每个字段名称、类型
        4. row   -- document
        5. column - field
    4. 写入数据
    ```text
       PUT	/megacorp(index)/employee(type)/1 (documentId)
       {
               "first_name"	:	"John",
               "last_name"	:		"Smith",
               "age"	:								25,
               "about"	:						"I	love	to	go	rock	climbing",
               "interests":	[	"sports",	"liufeng"	]
       }
    ```
    5. 查询数据，接上 GET /megacorp(index)/employee(type)/1  （select * from table where id = 1）
    6. exist判断     HEAD /megacorp(index)/employee(type)/1  (备注：待验证)
    7. 查询数据，仅指定type  GET	/index/type/_search
    8. 条件查询             GET	/megacorp/employee/_search?q=last_name:Smith（select * from table where last_name = 'Smith'  q是查询字符串的意思）
    9. es提供了DSL(Domain Specific Language特定领域语言)以JSON请求体的传递查询条件，这看起来和mongo的查询条件类似。
        1. match  匹配
        2. gt     greater than
        3. about  全文搜索，可以想象成一个文档是一个大的字符串，结果会以参数和字符串的匹配度倒序返回
        4. match_phrase   怎么说呢，match_phrase相比match更精准一些？
        5. highlight      高亮
        6. aggs   聚合
    10. 集群状态
        1. green	 所有主要分片和复制分片都可用
        2. yellow	 所有主要分片可用，但不是所有复制分片都可用
        3. red	 不是所有的主要分片都可用
8. Logstash 学习笔记 （https://doc.yonyoucloud.com/doc/logstash-best-practice-cn/filter/grok.html）
    8.1 四大核心模块 input/output/codec/filter
    8.2 工作流程 input读取数据，filter进行拦截/修改，codec编码，output输出到指定文件
    8.3 filter 核心-- gork，内置了很多正则表达式，可以直接使用（如DATA），也可以自定义进行引用
    ```text
       filter {
           grok {
               patterns_dir => "/path/to/your/own/patterns"
               match => {
                   # message会形成一个新的字段
                   "message" => "%{SYSLOGBASE} %{DATA:message}"
               }
               overwrite => ["message"]
           }
       }
    ```
    8.4 discover默认展示所有字段，点击左侧的available fields（鼠标悬浮会新增add选项），选择并add，即可只展示选中字段。



### ElasticSearch权威指南学习笔记
1. 介绍
   1. 基于Apache Lucene的开源搜索引擎，提供简单的restful API，屏蔽lcene的复杂性
   2. 分布式的实时文件存储，每个字段都被索引并可被搜索
   3. 分布式的实时分析搜索引擎
   4. 可以扩展到上百台服务器，处理PB级结构化或非结构化数据
2. 节点和集群
   1. 节点是一个运行着的elasticsearch的实例
   2. 集群是一组具有相同cluster.name的节点集合，共享数据并提供故障转移和扩展功能。当加入新的节点或者删除节点时，集群就会感知到并平衡数据
      1. 集群中会有一个主节点，临时管理集群级别的一些变更，如新建或删除索引、增加或移除节点。不参与文档级别的变更或搜索
3. 与ES的交互
   1. Java API（9300端口，版本需要与ES集群节点一致，否则可能无法识别）
      1. 节点客户端：不存储数据，但知道数据在集群中的具体位置，并且能够直接转发请求到对应节点上
      2. 传输客户端：更轻量，能发送请求到远程集群，它自己不加入集群，只是简单转发请求给集群中节点
   2. Restful API（9200端口，json格式）
    
    