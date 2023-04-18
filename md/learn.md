---
layout: page
title: "学习"
---
记录学到的小知识，相对比较零散。

##### 知识点 
1. [springboot] springboot提供了很多后置处理器，可以当做是web服务的拦截器。在服务初始化后处理一些自定义逻辑，比如EnvironmentPostProcessor，BeanPostProcessor。
EnvironmentPostProcessor需要再META-INF指定（未指定仍然会按照规则实例化，但是不会执行自定义逻辑） org.springframework.boot.env.EnvironmentPostProcessor=class，其他类待尝试
2. [jdk]java 创建对象的过程
   1. 申请内存空间
   2. 设置默认值，对象为null
   3. 赋值为用户期望的值
   4. 将用户变量指向为新创建对象
3. [jdk]对象在内存中存储布局
   1. markword
   2. 类型指针class pointer
   3. 实例数据instance data
   4. 8字节对齐padding：对象占据的空间应该是8字节的整数倍(liufeng.jdk.jvm.ma.LayoutTest)
4. [jdk] Object obj = new Object();占据多少个字节
   1. 16字节
   2. 12字节header
   3. 4字节padding
5. [jdk]对象头包含哪些信息
   1. 锁
   2. 垃圾回收标记
   3. 分代年龄
6. [jdk]对象怎么分配
   1. 栈上分配（作用域仅限于方法内，操作系统在方法结束后自动清理，不需要GC参与，但是空间较小；需要打开逃逸分析功能、标量替换）
   2. 堆
      1. 如果对象较大，分配到老年代
      2. 如果对象较小，分配到Eden区
         1. TLAB：每个线程分配一块私有空间，减少竞争
         2. 指针碰撞
7. [jdk]hotspot为什么不实用C++代替Java对象
   1. C++有虚函数表，比java对象更大
8. [jdk]Class实例在Method Area还是Heap
   1. jdk1.7实现称为永久代，jdk1.8实现称为元空间
   2. Hotspot使用OOP-KLASS模型来表示java对象
   3. OOP 英文全程是Ordinary Object Pointe，即普通对象指针，看起来像个指针实际上是藏在指针里的对象，表示对象的实例信息
   4. Klass 元数据和方法信息，用来描述 Java。是Java类的在C++中的表示形式，用来描述Java类的信息
   5. 当加载一个Class时，会创建一个InstanceKlass对象，实例化的对象则对应InstanceOopDesc，instanceOopDesc继承自oopDesc，用于表示普通的Java对象，每次new一个Java对象就会创建一个新的instanceOopDesc实例，其中InstanceKlass存放在元空间，InstanceOopDesc存放在堆中
9. [jdk]Double Check Lock需要使用volatile吗
   1. 需要
   2. 指令重排可能导致，对象已实例化，但没有初始化完成的情况