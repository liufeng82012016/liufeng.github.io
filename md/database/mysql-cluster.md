## 搭建主从数据库集群（5.7）
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

