# Spring 学习

### Spring 源码阅读环境搭建
1. 登录github，找到Spring项目，fork
2. 打开IDE，从github拉取代码，自动编译（IDEA2020会自动下载gradle包）
3. 定位到Spring模块，点开test文件，选择对应的Test类运行


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