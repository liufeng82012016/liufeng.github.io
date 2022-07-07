### 内存分配测试
1. 对象优先在Eden分配
2. 大对象直接进入老年代
3. 长期存活的对象将进入老年代
4. 动态年龄判断：如果HotSpot Survivor空间种低于或等于某年龄的所有对象大小总和大于Survivor空间的一般，那么年龄大于等于该年龄的对象可以直接进入到老年代
5. 空间分配担保
    - 在发生Minor GC之前，虚拟机必须检查老年代最大可用的连续空间是否大于新生代所有对象总空间
    - 如果大于，那么Major GC是安全的(流程结束)；
    - 如果小于，虚拟机将查看-XX:HandlerPromotionFailure参数的设置值是否允许担保失败
    - 如果不允许，会先进行一次Full GC（流程结束？？）；
    - 如果允许，将检查老年代最大可用的连续空间是否大于历史晋升到老年代对象的平均大小
    - 如果大于，将进行Major GC(有风险)；否则先进行一次Full GC
    - 备注：-XX:HandlerPromotionFailure在JDK 6 Update 24之后不再使用，只要老年代的连续空间大于新生代对象总大小或历次晋升的平均大小，就经行Minor GC，否则进行Full GC


### 方法执行测试
1. 局部变量表Slot占用对GC的影响：liufeng.jdk.jvm.ma.LocalVariableTest
2. 静态分派（重载）：
3. 动态分派（重写）：
4. 单分派与多分派

### 类加载和执行
1. 自定义类加载器
2. 将java.lang.System替换为自定义的HackSystem，它直接修改符合Class文件格式的byte[]常量池部分，将常量池指定内容的CONSTANT_Utf8_info替换为新的字符串