## Kubernetes

### 学习(官网：https://kubernetes.io/docs/home/)

#### 博客
1. https://www.cnblogs.com/chiangchou/p/k8s-1.html

#### yaml语法(https://www.cnblogs.com/liugp/archive/2022/06/09/16361487.html)

```yaml
#  v1： Kubernetes API的稳定版本，包含很多核心对象：pod、service等。
#  apps/v1： 包含一些通用的应用层的api组合，如：Deployments, RollingUpdates, and ReplicaSets。
#  batch/v1： 包含与批处理和类似作业的任务相关的对象，如：job、cronjob。
#  autoscaling/v1： 允许根据不同的资源使用指标自动调整容器。
#  networking.k8s.io/v1： 用于Ingress。
#  rbac.authorization.k8s.io/v1：用于RBAC。
apiVersion: apps/v1
#  pod、deployment、statefulset、job、cronjob 不同的类型，spec配置不一样
kind: Deployment
#  配置其显示的名字与归属的命名空间
metadata:
  name: hot
  labels:
    name: hot
#  Deployment 配置示例
spec:
  #  副本数量
  replicas: 1
  #  定义标签选择器
  selector:
    matchLabels:
      name: hot
  # pod的定义
  template:
    metadata:
      labels:
        name: hot
    # 指定资源内容
    spec:
      # 容器名字和镜像地址
      containers:
        - name: hot
          image: hot:1.0.0
          imagePullPolicy: Never
          # 容器对外的端口
          ports:
            - containerPort: 38083



```

### 运维部署

#### docker-desktop安装，见docker.md

