package com.my.liufeng.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * @author liufeng
 */
public class PerfMonAgent {

    private static Instrumentation inst = null;

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("PerfMonAgent premain was called.");
        PerfMonAgent.inst = inst;
        ClassFileTransformer trans = new PerfMonXformer();
        System.out.println("Adding a PerfMonXformer instance to the jvm");
        // JVM启动时在应用加载前会调用PerfMonAgent.premain，然后定制了ClassFileTransformer，这是的应用中的类加载时，PerfMonXformer.transform都会被调用
        PerfMonAgent.inst.addTransformer(trans);
    }
}
