### react

#### 基础知识
    - react:用于构建用户界面的Javascript库，是一个将数据渲染成Html视图的JavaScript库
    - FaceBook 开源
    - 和原生js的比较
        1. Document-API操作繁琐，效率低(重绘重排)
        2. 没有组件化的编码方案，代码复用率低
    - 特点
        1. 组件化模式，声明式编码，提高开发效率和组件复用率
        2. React-Native可以开发移动端
        3. 虚拟Dom和diffing算法，减少dom交互，提升性能
    - 前提
        1. this指向
        2. class类
        3. ES6
        4. npm
        5. 原型，原型链
        6. 数组常用方法
        7. 模块化
    - js依赖
        1. babel.min.js： ES6转ES5/jsx转js
        2. react.development.js： react核心库
        3. react-dom.development.js：react扩展
    - 虚拟dom
        1. 本质上是Object类型的对象
        2. 虚拟dom比较轻(属性少，只要react够用即可),真实dom比较重
        3. 虚拟dom最终会被react转换为真实dom，渲染在页面上
    - jsx
        1. JavaScript XML：react定义的类似xml格式的js
        2. 
        