### 六大原则
1. 单一职责。There should never be more than one reason for a class to change.
2. 里氏替换原则。
    1. 说明
        1. if for each object o1 of types S there is an object o2 of Type T such that
for all programs P defined in terms of T,the behavior of P is unchanged where o1 is substituted for 
o2 then S is a subType of T.(如果对每一个类型为S的对象o1，都有类型为T的对象o2，使得以T定义的所有程序在P所在对象的o1
都替换为o2时，程序P的行为没有发生变化，那么类型S是类型T的子类型)
        2. Functions that use pointers of references to base classes must be able to user objects of derived class without known it.
        (所有引用积累的地方必须能透明的使用子类的对象)
    2. 引申含义
        1. 子类必须完全重写父类的方法
        2. 子类可以扩展自己的行为（通常会保持一致，否则部分场景难以通用）
        3. 子类的参数范围必须大于等于父类
        4. 子类的返回值范围必须小于等于父类
3. 依赖倒置原则
    1. 说明 High level modules should not depend upon low level modules.Both should be depend upon abstractions.Abstractions should
    not depend in details.Details show be depend abstractions.(高层模块不应该依赖于底层模块，两者都应该依赖于抽象。抽象不应该依赖于细节。细节应该依赖于抽象。)
    2. 三种写法
        1. 构造器注入依赖
        2. setter方法注意依赖
        3. 接口参数注入依赖
    3. 代码规范
        1. 每个类尽量都有接口或者抽象类，或者两者都具备(必须有抽象才能依赖倒置)
        2. 变量的表面类型尽量抽象，如果要初始化一个OrderService，写法：IOrderService orderService=...;(Utils不需要)
        3. 任何类都不应该从具体类派生。如class xxx extends IOrderServiceImpl{}。基本上不能超过2次继承，如果是维护老代码除外。
4. 接口隔离原则
5. 最少知道原则（迪米特原则）
6. 开闭原则（对扩展开放，对修改关闭）