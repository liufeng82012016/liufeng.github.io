## GraalVM（官网：https://www.graalvm.org/）

### 文档链接
1. 掘金中文文档：https://juejin.cn/post/6844903928266489870
2. 

### 学习

### 实际使用

### mac 安装
1. 打开 https://github.com/graalvm/graalvm-ce-builds/releases
2. 下载对应版本 ，解压，修改环境变量，刷新配置文件即可
```text
export JAVA_HOME=/Users/liufeng/install/graalvm-ce-java17-22.3.0/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
```

#### docker安装
1. docker pull centos:latest
2. docker run --name centos -itd -v /Users/liufeng/IdeaProjects/liufeng82012016.github.io/images:/var/local/images --privileged  centos:latest  