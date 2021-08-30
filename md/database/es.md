## elasticsearch 使用笔记
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
        1. index -- database  复数（indices	|  indexes。）
        2. type  -- table
        3. row   -- document
        4. column - field
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
    9. es提供了DSL(Domain	Specific	Language特定领域语言)以JSON请求体的传递查询条件，这看起来和mongo的查询条件类似。
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
    
    