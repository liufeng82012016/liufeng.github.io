## springcloud_alibaba 框架 mysql相关组件测试
===========================================
#### 项目环境(版本号见项目pom)
* spring-boot
* spring-cloud
* spring-cloud-alibaba
* nacos
* spring-cloud-gateway
* openfeign
* mybatis
* mybatis-plus
* shardingSphere
* seata
#### 集成seata,shardingSphere 实现分布式事务，分库分表 20210707 ailwyn
    1. 环境搭建
        1. 安装mysql，执行sql
        2. 安装nacos https://nacos.io/zh-cn/docs/quick-start.html
            1. 打开官网，下载稳定版安装包(也可以自己编译)
            2. 解压
            3. 编辑启动脚本(默认集群启动，需要配置数据源，可修改为单机启动)
            4. 启动服务，默认是127.0.0.1:8848/nacos(username=password=nacos)
            > wget https://github.com/alibaba/nacos/releases/download/2.0.2/nacos-server-2.0.2.tar.gz
            > sudo tar -zxvf nacos-server-2.0.2.tar.gz
            > cd nacos/nacos-server-2.0.2/bin
            > vi startup.sh
            > sudo sh startup.sh
        3. 安装seata https://seata.io/zh-cn/docs/overview/what-is-seata.html
            1. 打开官网，下载稳定版安装包(也可以自己编译)
            2. 解压
            3. 使用参数启动，参数详情见官网
            4. 打开seata官方github，切换分支到对应版本，点击script->config-center，下载config.txt。继续点击nacos(不同的注册中心使用对应的脚本)，下载nacos-config.sh
            5. 上传脚本到服务器(sh文件没有尝试过在windows执行)，修改config.txt内容，执行脚本。然后打开注册中心，可以看到新增的配置项
            > wget https://github.com/seata/seata/releases/download/v1.4.2/seata-server-1.4.2.tar.gz
            > sudo tar -zxvf seata-server-1.4.2.tar.gz
            > cd seata/seata-server-1.4.2
            > sudo nohup seata-server.sh -h 127.0.0.1 -p 8091 -m db -n 1 -e dev &
            > bash nacos-config.sh -h 127.0.0.1 -p 8848 -g dev -t c2b80660-8e5c-4181-8590-6e705eb53b3d
            > -h nacosIp -p nacosPort -g nacosGroup -t nacosNamespace
    2. 测试  
        2.1 事务
            2.1.1 spring事务失效的多个场景
                > 数据库引擎不支持事务(如MyISAM)----------忽略
                > Bean没有被 Spring 管理-----------------测试
                > 方法不是 public 的---------------------spring.AOP未生效
                > 自身调用问题---------------------------测试
                > 数据源没有配置事务管理器-----------------使用需要注意，有的框架已经实现，多个框架整合可能冲突
                > 不支持事务-----------------------------使用者问题
                > 异常未抛出，或者类型不对-----------------spring默认只回滚RunTimeException()
        2.2 分库分表
            2.2.1 写入，校验写入结果是否与分库分表规则相匹配
            2.2.2 selectById()id映射表
            2.2.3 selectPage()归并
    3. 问题点整理
        3.1 ShardingSphere分库分表查询(假设查询第200页，每页数据10条)
            3.1.1 官方推荐 使用主键作为查询条件(第199页id为10000)
                > select * from t_order_info where id > 10000 limit 10
                > 缺点：大数据量的查询性能会比较低，跳页查询业务比较复杂
            3.1.2 假设数据平均分布，每个数据库查询相同的页数再拼接(假设分表数为x，分库y)
                > select * from t_order_info order by id limit (2000/xy),10/xy
                > 缺点：数据可能不准确
            3.1.3 二次查询法，详情见备注文档
            3.1.4 使用NoSql数据库作为索引。NoSql天然是分布式的，可以存储大量数据。根据条件查询出主键，mysql使用主键in查询
#### 原理篇

#### 备注 
    1. 分库分表查询方案1：https://mp.weixin.qq.com/s/h99sXP4mvVFsJw6Oh3aU5A?(已经复制到article目录)
    2. 分库分表查询方案2：https://www.cnblogs.com/lhh-north/p/11140940.html(已经复制到article目录)
    3. shardingSphere中文文档：https://shardingsphere.apache.org/document/current/cn/user-manual/
    4. seata中文文档：https://seata.io/zh-cn
    

