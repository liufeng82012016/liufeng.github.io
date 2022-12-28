## Spring

#### 搭建源码环境
1. 打开Spring github官网，点击fork
2. 克隆代码到本地 git clone

#### 面试题
1. Bean生命周期(直接在源码，BeanFactory类写得很清楚)
   1. 利用构造器生成普通对象 
   2. 递归注入属性
   3. 如果实现了BeanNameAware接口，调用setBeanName()
   4. 如果实现了BeanFactoryAware接口，调用setBeanFactory()
   5. 如果实现了ApplicationContextAware接口，调用setApplicationContext()
   6. 调用BeanPostProcessor的postProcessBeforeInitialization()方法
   7. 执行初始化前方法（@PostConstruct()方法，如果存在）
   8. 执行初始化方法
      1. afterPropertiesSet()方法，如果存在
      2. init-method()，如果存在
   9. 调用BeanPostProcessor的postProcessAfterInitialization()方法
   10. 初始化后（AOP）
   11. 代理对象（没有再次依赖注入，属性值为空；代理对象有一个属性指向普通对象，普通对象有属性）
   12. 放入Map<String,Object)
   13. 返回Bean对象
2. Bean实例化和初始化的区别
   1. 实例化：使用构造器生成对象
   2. 初始化：执行初始化方法