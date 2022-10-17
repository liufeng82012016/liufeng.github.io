## Tomcat

### 面试  
1. 有哪几种Connector运行模式
   1. BIO
   2. NIO
   3. ARP（Apache Portable Runtime），异步非阻塞IO
2. 什么时候会创建Servlet
   1. 容器启动时扫描web.xml，通过返回的方式示例话
   2. 如果servlet注册时加上1（哪个配置项？）为正数，一开始就示例化，否则第一次请求时示例化
3. 如何优化
   1. 去掉对web.xml监控，jsp提前编译成servlet
   2. 服务器
   3. 缓存和压缩
   4. 集群
   5. 线程数，maxThreads,-XMS
4. 目录结构
   1. /bin：存放用于启动和暂停Tomcat的脚本 
   2. /conf：存放Tomcat的配置文件 
   3. /lib：存放Tomcat服务器需要的各种jar包 
   4. /logs：存放Tomcat的日志文件 
   5. /temp：Tomcat运行时用于存放临时文件 
   6. /webapps：web应用的发布目录 
   7. /work：Tomcat把有jsp生成Servlet防御此目录下
5. Tomcat 架构
   1. Server服务器：代表整个服务器，可以部署一个或多个Service
   2. Service：控制tomcat服务的生命周期，包含Connector（一个或多个）和Container（一个）
      1. Connector用于处理连接相关的事情，并提供Socket与Request请求和Response响应相关的转化
         1. ProtocolHandler
            1. Endpoint处理底层的Socket网络连接（TCP/IP），主要包含2个内部类和1个方法
               1. 内部类Acceptor用于监听请求
               2. 内部类AsyncTimeout用于检查异步Request的超时
               3. 方法Handler用于处理接收到的Socket，在内部调用Processor进行处理
            2. Processor将Endpoint接收到的Socket封装成Request（HTTP）
            3. Adapter将请求适配到Servlet容器进行具体的处理
      2. Container用于封装和管理Servlet，以及具体处理Request请求，主要包含4个子容器
         1. Engine：引擎，用来管理多个站点，一个Service最多只能有一个Engine
         2. Host：代表一个站点，也可以叫虚拟主机，通过配置Host就可以添加站点
         3. Context：代表一个应用程序，对应着平时开发的一套程序，或者一个WEB-INF目录以及下面的web.xml文件
         4. Wrapper：每一Wrapper封装着一个Servlet
         5. 处理请求 Pipeline-value(责任链模式，上诉4个容器都有相应的责任链，且各有一个默认实现的节点在责任链尾部)


