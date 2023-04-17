## mongo 使用笔记
1. 搭建集群（3.6版本 副本集）
    1. 下载压缩包，解压
    2. 创建data文件夹/log文件夹，复制并修改配置文件
        1.dataPath  -- 文件路径
        2.logpath   -- 日志文件路径
        3.logappend -- 是否追加
        4.bind_id   -- 允许访问的ip
        5.port      -- 端口
        6.fork      -- 是否后台运行
        7.replset   -- 集群名称
    3. 启动多个节点（mongo --config fileName），mongo --host 127.0.0.1 --port 27017 登录控制台
        1. 初始化副本集
        ```shell
            rs.initiate({
               _id : "rs0",
               members :[
                  {  _id : 0 , host: "192.168.1.1:27017"},
                  {  _id : 1 , host: "192.168.1.1:27019"},
                  {  _id : 2 , host: "192.168.1.1:27019"} 
               ] 
            })
            // 注意，这里的ip必须要members可以互相访问到。如果有历史数据，可能更新该配置无效，可以备份后删除数据充值
            rs.add("127.0.0.1:27017");// 添加节点
            rs.remove(127.0.0.1:27017);// 移除节点
         ```
         2. 查看副本集配置信息 -- rs.conf();
         3. 查看副本集状态 -- rs.status();
         4. 强制设置某个节点为primary
         ```shell
            cfg=rs.conf();
            cfg.members=[cfg.members[3]]
            rs.reconfig(cfg, {force: true});
         ```
2. mongo查询优化
    1. 索引
    2. mongo内存占用
         
         