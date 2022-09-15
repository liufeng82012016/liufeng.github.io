package com.my.liufeng.agent;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 利用java.lang.instrument 实现AOP
 * javaassist:javaassist:3.8.0.GA
 *
 * @author liufeng
 */
public class PerfMonXformer implements ClassFileTransformer {
    /**
     * 这里可以任意修改class文件内容，最后返回即可
     * 本示例使用JBoss的javassist包，当然也可以使用其他框架或自定义修改
     *
     * @param loader
     * @param className
     * @param classBeingRedefined
     * @param protectionDomain
     * @param classfileBuffer     类文件格式的输入字节缓冲区
     * @return
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        System.out.println("transforming " + className);
        ClassPool pool = ClassPool.getDefault();
        CtClass c1 = null;
        try {
            c1 = pool.makeClass(new java.io.ByteArrayInputStream(classfileBuffer));
            if (!c1.isInterface()) {
                CtBehavior[] methods = c1.getDeclaredBehaviors();
                for (int i = 0; i < methods.length; i++) {
                    if (!methods[i].isEmpty()) {
                        // 修改method字节码
                        doMethods(methods[i]);
                    }
                }
                transformed = c1.toBytecode();
            }
        } catch (Exception e) {
            System.err.println("Could not instrument " + className + ", exception: " + e.getMessage());
        } finally {
            if (c1 != null) {
                c1.detach();
            }
        }

        return transformed;
    }

    private void doMethods(CtBehavior method) throws CannotCompileException {
        method.insertBefore("long stime = System.nanoTime();");
        method.insertAfter("System.out.println(\"leave \"+method.getName()+\" and time: \"+(System.nanoTime()-stime));");
    }
}
