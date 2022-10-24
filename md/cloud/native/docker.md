### Docker 学习笔记

#### 3大组件
- 仓库：类似git，docker有一个中央仓库，包含构建好的组件(hub.docker.com 一般使用国内镜像)
- 镜像：只读文件，一个镜像可以构建多个容器（在我理解就是编译好的软件包），一个镜像有多个标签(个人理解为版本号)。
- 容器：安装好，待用的软件？？

####  常用命令
- docker version
- docker info
- docker --help

- docker images 列举本地镜像，每个镜像有个唯一id
- docker search -f \[stars=] image-name
- docker pull image-name:TAG  tag可省略，默认latest
- docker rmi image-name:tag|image-id 删除镜像

- docker run \[options] image \[command] \[args] 创建并运行容器(如果本地没有，自动从远程拉取)
- docker ps \[options] 查看容器列表
- docker start container-id|container-name 启动容器
- docker stop container-id|container-name  停止容器，相当于kill -15
- docker kill container-id|container-name  停止容器，相当于kill -9
- docker rm container-id|container-name  删除容器
- docker logs -f -t --tail \[n] container-id  -f指最新数据 -t显示时间戳 tail显示最后多少条
- docker top container-id 查看容器进程
- docker attach container-id 进入容器根目录
- docker exec -t container-id command 执行命令并返回结果（如果command和attach效果相同）
- docker cp container-id:remotePath localPath 复制文件到本机
- docker commit -m "提交信息" -a "作者" container-id image-name:TAG 将容器副本提交为镜像
- docker run -it -v localPath:dockerContainerPath image 创建数据卷并可以通信，如果dockerContainerPath:ro，表示容器只读(只能同步主机数据，自己不能操作)
- docker inspect container-id

- exit 关闭容器并退出
- ctrl+P+Q 推出容器，但不关闭

#### dockerfile
1. 概念：用来构建docker镜像的构建文件，由一系列命令和参数构成
2. 使用步骤
    1. 编写docker文件
    2. 使用docker build命令构建自定义镜像
    3. run
3. 语法规则
    1. 每条保留字都为大写字母，且至少有一个参数
    2. 指令按照从上到下的顺序执行
    3. \#表示注释
    4. 每条指定都会创建一个新的镜像层，并堆镜像进行提交
4. 执行流程
    1. docker从基础镜像运行一个容器
    2. 执行一条指令并对容器做出修改
    3. 执行类似docker commit的操作提交一个新的镜像层
    4. docker再基于一个刚提交的镜像运行一个新容器
    5. 执行docker的下一条指令直到所有指令都执行完成
5. 保留字
    - FROM 基础镜像，说明当前镜像基于那个镜像的
    - MAINTAINER 作者姓名和邮箱
    - RUN 要执行的命令
    - EXPOSE 对外暴露的端口
    - WORKDIR 用户进入容器默认工作目录
    - ENV 构建过程中设置环境变量
    - ADD 将指定文件拷贝到指定目录并自动处理URL、解压
    - COPY 将指定文件拷贝到指定目录
    - VOLUME 容器数据卷，用于数据保存和持久化工作
    - CMD 指定容器启动时要运行的命令，dockerfile可以有多个CMD指令，但只有最后一个生效。CMD会被docker run后面的参数替换
    - ENTRYPOINT 和CMD类似，但不是被docker run 的参数后面替换，而是将参数追加
    - ONBUILD 当构建一个被继承的dockerfile时，父镜像在被子镜像继承后父镜像的onbuild触发
    - .dockerignore
    - USER
5. 示例
```text
#### docker centos dockerfile 实例
FROM centos
ENV DEFAULTPATH /usr/share
WORKDIR $DEFAULTPATH
RUN yum install vim
RUN yum install net-tools
EXPOSE 80
CMD echo "hello, centos"
CMD /bin/bash
```

备注：
    - scratch 类似Java 的Object类，是所有镜像的base镜像


#### docker镜像
![docker tomcat镜像示意图](../img/docker-image-tomcat.png)

#### docker 容器数据卷
1. 作用：关闭容器时，将容器数据持久化（如果没有commit，容器删除后数据会丢失掉）。类似Redis的持久化
2. 特点
    - 数据卷可用于容器之间共享数据
    - 卷中的更改可直接生效
    - 卷中的更改不会包含在镜像的更新中
    - 数据卷的生命周期持续到没有容器使用它为止
3. 创建方式
    - docker run -it -v localPath:dockerContainerPath image 命令
    - dockerfile创建
4. 数据卷容器：容器间传递共享，知道没有容器使用它为止

#### 打包SpringBoot
1. 创建一个SpringBoot项目，打包jar
2. 创建Dockerfile（无后缀） ![springboot 打包docker](../img/spring-boot-dockerfile.png)
3. 运行docker -build -t name:tag dir
   1. 报错 failed to solve with frontend dockerfile.v0: failed to create LLB definition: docker.io/library/java:8: not found
   2. https://www.codenong.com/35325103/ 可能是多个镜像互相影响导致
   3. 解决：将java:8改成java:9，打包成功，使用docker images; 查看所有镜像，大小为579MB
   4. 创建并启动容器docker run --name hot -p 8292:8292 -d hot:v1.0.0
   5. 启动失败 no main manifest attribute, in /hot.jar.SpringBoot 启动异常NPE，怀疑是打包的jdk（8）和docker构建jdk（9）版本差异
   6. 修改dockerfile：FROM openjdk:8-jre。打包成功，运行成功，接口访问成功


    

#### 示例：安装Nginx(代码来源于菜鸟教程)
1. 打开docker仓库，获取nginx版本列表
2. 拉取nginx镜像，docker pull nginx:latest(latest是版本号)
3. 使用docker images查看本地镜像，如图 ![docker-images](../img/docker-images.png)
4. 运行nginx
    - 任意文件夹执行命令：C:\Users\tiny>docker run --name nginx-test -p 8080:80 -d nginx
    - 得到响应：16442252bbcb287b8a093df596ba54c4c4e5a2cfbd393b52596b4dc68e5540db
    - 命令解析 --name nginx-test 表示创建名为nginx-test容器，-p 8080:80表示将本机的8080端口映射到docker的80端口 -d表示后台运行 
    - 访问本地8080端口，将自动转发到nginx，请求如图：![docker-nginx-sample](../img/docker-nginx.png)
    - 查看本地容器，如图 ![docker-containers](../img/docker-container.png)
    
#### 示例2：搭建MySQL主从
1. 拉取mysql镜像 docker pull mysql:latest
2. 运行2个mysql实例
    - C:\Users\tiny>docker run --name mysql-main -p 3340:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:latest
    - C:\Users\tiny>docker run --name mysql-copy -p 3339:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql
    - 备注：docker的每一个容器都是单独的环境，两个环境使用同一个端口并不冲突；启动时如果本地只有一个镜像，无需指定TAG
    - 如果有2个名为mysql的镜像，启动未指定TAG，是否会报错？如果没有报错，默认值是最新版
    - C:\Users\tiny>docker run --name mysql-copy1 -p 3338:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql
    - 启动成功，使用版本为8.0.26，并非5.7.默认值使用最新版本，还是最早拉下来的版本？规则暂不确定(latest)
3. docker exec -it \[container-name|container-id] /bin/bash 进入服务器，修改配置文件，重启
    - server-id=100 同一局域网内注意要唯一
    - log-bin=mysql-bin 开启二进制日志功能，可以随便取（关键）
    - CREATE USER 'slave'@'%' IDENTIFIED BY '123456';
    - GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'slave'@'%';
    
4. 修改slave配置文件，重启
    - change master to master_host='172.17.0.2',master_user='slave',master_password='123456',master_port=3306,master_log_file='mysql-log-000001',master_log_pos=710,master_connect_retry=30;
    - start slave;
    - show slave status;

### 示例3：搭建rocketmq(博客链接：https://cloud.tencent.com/developer/article/1730689)
1. clone代码：git clone https://github.com/apache/rocketmq-docker
2. 生成镜像命令：sh build-image.sh RMQ-VERSION BASE-IMAGE，实际命令：sh build-image 4.7.1 alpine
3. 查看镜像 docker images | grep -i rocketmq
4. 执行脚本生成配置文件：sh stages.sh 4.7.1
5. 启动容器 cd stages/4.7.1, sh ./play-docker.sh alpine
   1. 启动日志：Starting RocketMQ nodes... 
   2. 启动日志：5b4bf1a902cf1cc0d6dcb70f2d22dabdf3d98ac0a8f48cf8e677013d1991dd29 
   3. 启动日志：1f42e74a91e7edebe7c2b9ba05f98869c54554c1a2723ba7ff308cd9a3113ea0
6. 问题：brokerIp无法访问
7. 换另外一个博客https://mp.weixin.qq.com/s/3OR3PrVnpudMKPZEMtTj_A
8. 
