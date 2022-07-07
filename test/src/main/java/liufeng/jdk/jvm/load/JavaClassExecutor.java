package liufeng.jdk.jvm.load;

import java.lang.reflect.Method;

/**
 * 为外部调用提供的入口，调用前面几个支持类组装逻辑，完成类加载工作
 * 功能：用输入的符合Class文件格式的byte[]数组替换掉java.lang.System的符号引用后，使用HotSwapClassLoader加载生成一个Class对象
 * 由于同一类可以实现重复加载，然后反射调用这个Class对象的main()方法。如果期间出现任何异常，将异常打印到HackSystem.out中，最后把缓冲区中的信息作为方法的结果来返回
 */
public class JavaClassExecutor {

    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        cm.modifyUTF8Constant("java/lang/System", "liufeng/jdk/jvm/load/HackSystem");
        // 每次都新建一个加载器
        HotSwapClassLoader loader = new HotSwapClassLoader();

        Class clazz = loader.loadByte(classByte);
        try {
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}
