---
layout: page
title: "学习"
---
记录学到的小知识，相对比较零散。

##### 知识点 
1. [springboot] springboot提供了很多后置处理器，可以当做是web服务的拦截器。在服务初始化后处理一些自定义逻辑，比如EnvironmentPostProcessor，BeanPostProcessor。
EnvironmentPostProcessor需要再META-INF指定（未指定仍然会按照规则实例化，但是不会执行自定义逻辑） org.springframework.boot.env.EnvironmentPostProcessor=class，其他类待尝试
