## SpringBoot 日志配置

### SpringBoot 实现
1. 依赖层级
   1. spring-boot-starter-web依赖于spring-boot-starter
   2. spring-boot-starter依赖于spring-boot-starter-logging
   3. spring-boot-starter-logging
      1. logback-classic（不同的依赖可能会有不同的实现，SpringBoot默认使用logback）
      2. log4j-to-slf4j
      3. jul-to-slf4j
2. 配置文件优先级：logback.xml >> application.properties >> logback-spring.xml（推荐）
3. yml配置项
   1. logging.file: *** 将日志输出到文件
   2. logging.level:package: logLevel 针对某个包设置日志级别
   3. logging.level.root: logLevel 全局设置日志级别
4. logback-spring.xml配置（https://blog.csdn.net/hansome_hong/article/details/124434864）
   1. 1个父标签：configuration
      1. scan：此属性为true时，配置文件如果发生改变，将会重新加载，默认值为true
   2. 2种属性
      1. 