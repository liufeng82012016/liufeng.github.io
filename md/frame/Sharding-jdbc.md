### Apache下分库分表方案实现
#### 实现原理
1. 重写4大对象
   1. DataSource
      1. ShardingDataSource(分片数据源)
      2. MasterSlaveDataSourceFactory(主从数据源)
      3. EncryptDataSourceFactory(脱敏数据源)
   2. Connection
   3. Statement
   4. ResultSet(ShardingResultSet)
      1. SimpleQueryShardingEngine 完成 SQL  解析、路由、改写
      2. StatementExecutor 进行 SQL 执行
      3. MergeEngine 对结果进行合并处理
#### SpringBoot 集成
1. 依赖
```text

```
2. 配置
```yaml
spring:
  main:
    allow-bean-definition-overriding: true
  shardingsphere:
    datasource:
      # 多个数据源名称
      names: ds0,ds0-slave0,ds1,ds1-slave0,ds2,ds2-slave0
      ds0:
        # 单个数据源配置，连接池配置待续
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/example?serverTimezone=UTC&characterEncoding=utf8
        password: root
        type: com.zaxxer.hikari.HikariDataSource
        username: root
      ds0-slave0:
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/example?serverTimezone=UTC&characterEncoding=utf8
        password: root
        type: com.zaxxer.hikari.HikariDataSource
        username: root
      ds1:
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/example?serverTimezone=UTC&characterEncoding=utf8
        password: root
        type: com.zaxxer.hikari.HikariDataSource
        username: root
      ds1-slave0:
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/example?serverTimezone=UTC&characterEncoding=utf8
        password: root
        type: com.zaxxer.hikari.HikariDataSource
        username: root
      ds2:
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/example?serverTimezone=UTC&characterEncoding=utf8
        password: root
        type: com.zaxxer.hikari.HikariDataSource
        username: root
      ds2-slave0:
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/example?serverTimezone=UTC&characterEncoding=utf8
        password: root
        type: com.zaxxer.hikari.HikariDataSource
        username: root

    props:
      sql:
        # 是否显示sql
        show: true
    # 分库分表配置
    sharding:
      # 主从配置
      master-slave-rules:
        ms-ds0:
          masterDataSourceName: ds0
          slaveDataSourceNames:
          # 从库配置，可以配置多个从库
          - ds0-slave0
          # 负载均衡算法
          loadBalanceAlgorithmType: ROUND_ROBIN
        ms-ds1:
          masterDataSourceName: ds1
          slaveDataSourceNames:
          - ds1-slave0
          loadBalanceAlgorithmType: ROUND_ROBIN
        ms-ds2:
          masterDataSourceName: ds2
          slaveDataSourceNames:
          - ds2-slave0
          loadBalanceAlgorithmType: ROUND_ROBIN
      tables:
        t_example_order:
          actualDataNodes: ms-ds${0..2}.t_example_order
          databaseStrategy:
            inline:
              shardingColumn: user_id
              # user_id对3取余，该数据写入ms-ds${0..2}库
              algorithmExpression: ms-ds${user_id % 3}
          keyGenerator:
            # 生成主键的算法及主键字段，内置SNOWFLAKE/UUID，也可以自己实现
            type: SNOWFLAKE
            column: id
            props: #属性配置, 注意：使用SNOWFLAKE算法，需要配置worker.id与max.tolerate.time.difference.milliseconds属性。若使用此算法生成值作分片值，建议配置max.vibration.offset属性
              <property-name>:  <property-value>
        binding_tables:
        - t_example_order
      default_dataSource_name: ds0
      defaultTableStrategy:
        none:
      defaultKeyGenerator:
        type: SNOWFLAKE
        column: id
```