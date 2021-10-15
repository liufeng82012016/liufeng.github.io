###代码源于书籍《深入理解JVM虚拟机》


#### 堆内存溢出
- 代码实现：设置Xms,Xmx，无限循环实例化指定对象
#### 栈溢出测试
- 原话：HotSpot并不区分本机方法栈和虚拟机栈，所有-Xoss(设置本地方法栈大小)无效，只能设定-Xss；Java虚拟机规范定义了2种异常
    - 如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverFlowError
    - 如果虚拟机的栈内存允许的动态扩展，当无法申请到足够的内存时，将抛出OutOfMemoryError异常(HotSpot不支持扩展)
- 测试用例
    - 使用Xss参数减少栈内存容量。预期结果：StackOverFlowError
    - 定义大量的本地变化，增大此方法栈桢种本地变量表的长度。预期结果：StackOverFlowError
    - 多线程场景
    
#### 常量池溢出
- 无限新增字符串常量
#### 直接内存溢出
- 利用unsafe类分配直接内存
#### 方法区溢出
- 动态代理生成类（未完成）