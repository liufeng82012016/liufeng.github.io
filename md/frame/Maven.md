##Maven

### 介绍
1. Maven是apache维护的一款为java项目提供构建和依赖管理支持的工具
2. 命令（执行maven命令必须要pom.xml目录进行）
   1. mvn clean
   2. mvn compile
   3. mvn test-compile
   4. mvn test
   5. mvn package
   6. mvn install
3. 解决的问题
   1. 添加第三方jar包
   2. jar包的依赖关系
   3. 获取第三方jar包
   4. 将项目拆分为多个工程模块
4. 坐标
   1. groupId
   2. artifactId
   3. version
5. 生命周期
   1. validate：验证项目是否正确且所有必须信息是可用的
   2. compile
   3. test
   4. package
   5. verify：对集成测试的结果进行检查
   6. install
   7. deploy：部署到远程仓库
6. 依赖原则
   1. 最短路径原则（依赖传递越短越优先）
   2. pom文件申明顺序优先（路径相等，先申明的优先）
   3. 覆写原则（当前pom申明的内容覆盖父工程传过来的）

### 安装
1. 安装JDK
2. 下载安装包（src版本，https://maven.apache.org/download.cgi）并解压
3. 配置环境变量（M2_HOME、PATH）
4. 输入mvn -v验证是否成功
5. maven目录
   1. bin
   2. boot
   3. conf
   4. lib