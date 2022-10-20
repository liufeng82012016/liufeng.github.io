# Spring 学习

### Spring 源码阅读环境搭建
1. 登录github，找到Spring项目，fork
2. 打开IDE，从github拉取代码，自动编译（IDEA2020会自动下载gradle包）
3. 定位到Spring模块，点开test文件，选择对应的Test类运行

### 面试题
1. 特性
   1. 控制反转
   2. 面向切面
   3. 容器
   4. MVC框架
   5. 事务管理
   6. 异常处理
2. Spring AOP和AspectJ的区别
   1. AOP属于运行时增强，AspectJ属于编译时增强
   2. AOP基于代理，AspectJ基于字节码操作
3. Bean生命周期
   1. 实例化Bean
   2. 设置对象属性
   3. 如果实现了Aware接口，自动注入
   4. BeanPostProcessor.postProcessBeforeInitialization()（如有）
   5. 如果实现了InitializingBean，先调用afterPropertySet()，再调用init-method()（如有）
   6. BeanPostProcessor.postProcessAfterInitialization()（如有）
   7. 如果实现了DisposableBean，注册destroy()方法，在对象被注销时使用
4. Spring 事务3要素
   1. 数据源，获取连接
   2. 事务管理器，控制打开、提交、回滚
   3. 事务应用和属性配置


### 从普通XML加载BEAN
1. 初始化Registry
2. XMLReader解析xml文件，返回document类
3. 将doc作为参数，注册bean，返回注册bean的数量
   1. org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader.registerBeanDefinitions
   2. org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader.doRegisterBeanDefinitions
      1. 处理profile
      2. preProcessXml(root); // DefaultBeanDefinitionDocumentReader默认什么也没做
      3. parseBeanDefinitions(root, this.delegate); // 解析参数，初始化Bean，将Bean和别名注册到响应的map
            1. 编译NodeList，遍历，
               1. Spring命名空间，分别处理import/alias/bean/beans标签，org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader.parseDefaultElement
                  1. importBeanDefinitionResource
                  2. processAliasRegistration
                  3. processBeanDefinition
                     1. 封装Bean信息
                     2. 将Bean注册到Registry
                     3. 发布注册事件
                  4. doRegisterBeanDefinitions
               2. 自定义命名空间
      4. postProcessXml(root); // DefaultBeanDefinitionDocumentReader默认什么也没做

### 博客链接
1. Spring AOP:https://mp.weixin.qq.com/s/7vb5NWbLUUjpxU-emzNZuw