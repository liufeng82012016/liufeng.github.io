package liufeng.jdk.jvm.compile.table;

/**
 * 属性表 有9个类型，使用位置和含义如下
 * Code 方法表，Java代码编译成的字节码指令
 * ConstantValue 字段表，final关键字定义的常量值
 * Deprecated 类、方法表、字段表，被声明为deprecated的方法和字段
 * Exceptions 方法表，方法被抛出的异常
 * InnerClasses 类文件 内部类列表
 * LineNumberTable Code属性，Java源码的行号与字节码指令的对应关系
 * LocalVariableTable Code属性，方法的局部变量描述
 * SourceFile 类文件，源文件名称
 * Synthetic 类、方法表、字段表，表示方法或字段为编译器自动生成的
 * 对于每个属性，它的名称需要从常量池引用一个CONSTANT_Utf8_info类型的常量，而属性值的结构则是完全自定义的，只需要说明属性值所占用的位数长度即可
 * 每个属性结构包含
 * u2 attribute_name_index(1)
 * u2 attribute_length(1)
 * u1 info(数量由attribute_length决定)
 */
public class AttributeInfo {
}
