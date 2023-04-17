package liufeng.jdk.jvm.tuning;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * java -Xms200M -Xmx200M -XX:+PrintGC liufeng.jdk.jvm.tuning.CardTuning
 * jps
 * 51 CardTuning
 * 121 Jps
 *
 * [GC (Allocation Failure)  51712K->1144K(196608K), 0.0027873 secs]
 * [GC (Allocation Failure)  52856K->21046K(196608K), 0.0202917 secs]
 * [GC (Allocation Failure)  72758K->49488K(196608K), 0.0359423 secs]
 * [GC (Allocation Failure)  101200K->74304K(196608K), 0.0328360 secs]
 * [GC (Allocation Failure)  126016K->103812K(195584K), 0.0413762 secs]
 * [Full GC (Ergonomics)  103812K->102818K(195584K), 0.5437661 secs]
 * [Full GC (Ergonomics)  153506K->129948K(195584K), 0.2629920 secs]
 * [Full GC (Ergonomics)  180636K->159380K(195584K), 0.3040043 secs]
 * [Full GC (Ergonomics)  186978K->175373K(195584K), 0.2998774 secs]
 * [Full GC (Ergonomics)  186978K->181318K(195584K), 0.2821865 secs]
 * [Full GC (Ergonomics)  186978K->184365K(195584K), 0.2823041 secs]
 * [Full GC (Ergonomics)  186978K->186014K(195584K), 0.2978123 secs]
 * [Full GC (Ergonomics)  186978K->186459K(195584K), 0.2825639 secs]
 * [Full GC (Ergonomics)  186885K->186558K(195584K), 0.2630487 secs]
 * [Full GC (Allocation Failure)  186558K->186558K(195584K), 0.2754507 secs]
 * [Full GC (Ergonomics)  186648K->186556K(195584K), 0.2962015 secs]
 * [Full GC (Allocation Failure)  186556K->186556K(195584K), 0.2878966 secs]
 * [Full GC (Ergonomics)  186560K->186557K(195584K), 0.3252627 secs]
 * [Full GC (Allocation Failure)  186557K->186557K(195584K), 0.2870958 secs]
 * [Full GC (Ergonomics)  186592K->186557K(195584K), 0.3136941 secs]
 * [Full GC (Allocation Failure)  186557K->186557K(195584K), 0.3184951 secs]
 * Exception in thread "main" Exception in thread "pool-1-thread-20" Exception in thread "pool-1-thread-29" Exception in thread "pool-1-thread-43" java.lang.OutOfMemoryError: Java heap space
 * 	at java.lang.reflect.Array.newArray(Native Method)
 * 	at java.lang.reflect.Array.newInstance(Array.java:75)
 * 	at java.util.Arrays.copyOf(Arrays.java:3212)
 * 	at java.util.Arrays.copyOf(Arrays.java:3181)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.grow(ScheduledThreadPoolExecutor.java:921)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.offer(ScheduledThreadPoolExecutor.java:1014)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.add(ScheduledThreadPoolExecutor.java:1037)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.add(ScheduledThreadPoolExecutor.java:809)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor.reExecutePeriodic(ScheduledThreadPoolExecutor.java:346)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:296)
 * 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
 * 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 * 	at java.lang.Thread.run(Thread.java:748)
 * java.lang.OutOfMemoryError: Java heap space
 * java.lang.OutOfMemoryError: Java heap space
 * 	at java.lang.reflect.Array.newArray(Native Method)
 * 	at java.lang.reflect.Array.newInstance(Array.java:75)
 * 	at java.util.Arrays.copyOf(Arrays.java:3212)
 * 	at java.util.Arrays.copyOf(Arrays.java:3181)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.grow(ScheduledThreadPoolExecutor.java:921)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.offer(ScheduledThreadPoolExecutor.java:1014)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.add(ScheduledThreadPoolExecutor.java:1037)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.add(ScheduledThreadPoolExecutor.java:809)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor.delayedExecute(ScheduledThreadPoolExecutor.java:328)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor.scheduleWithFixedDelay(ScheduledThreadPoolExecutor.java:597)
 * 	at liufeng.jdk.jvm.tuning.CardTuning.lambda$modelfit$1(CardTuning.java:38)
 * [Full GC (Ergonomics)  186795K->186560K(195584K), 0.4474438 secs]
 * [Full GC (Allocation Failure)  186560K->186560K(195584K), 0.3094609 secs]
 * 	at liufeng.jdk.jvm.tuning.CardTuning$$Lambda$1/135721597.accept(Unknown Source)
 * [Full GC (Ergonomics)  186602K->186560K(195584K), 0.3049950 secs]
 * [Full GC (Allocation Failure)  186560K->186560K(195584K), 0.2739947 secs]
 * 	at java.util.ArrayList.forEach(ArrayList.java:1259)
 * 	at liufeng.jdk.jvm.tuning.CardTuning.modelfit(CardTuning.java:37)
 * 	at liufeng.jdk.jvm.tuning.CardTuning.main(CardTuning.java:29)[Full GC (Ergonomics)  186591K->186560K(195584K), 0.3474580 secs]
 * [Full GC (Allocation Failure)  186560K->186560K(195584K), 0.3014573 secs]
 * [Full GC (Ergonomics)  186822K->186561K(195584K), 0.3768198 secs]
 * [Full GC (Allocation Failure)  186561K->186561K(195584K), 0.3051527 secs]
 *
 * [Full GC (Ergonomics)  186708K->186561K(195584K), 0.3249589 secs]
 * [Full GC (Allocation Failure)  186561K->186561K(195584K), 0.3861813 secs]
 * java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-38" Exception in thread "pool-1-thread-16" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-1" Exception in thread "pool-1-thread-27" java.lang.OutOfMemoryError: Java heap space[Full GC (Ergonomics)  186974K->186557K(195584K), 0.3604746 secs]
 * [Full GC (Allocation Failure)  186557K->186557K(195584K), 0.2944119 secs]
 *
 * [Full GC (Ergonomics)  186764K->186558K(195584K), 0.2911411 secs]
 * [Full GC (Allocation Failure)  186558K->186558K(195584K), 0.2688719 secs]
 * [Full GC (Ergonomics)  186765K->186557K(195584K), 0.3194508 secs]
 * [Full GC (Allocation Failure)  186557K->186557K(195584K), 0.3231591 secs]
 * Exception in thread "pool-1-thread-10" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-19" [Full GC (Ergonomics)  186907K->186557K(195584K), 0.3112632 secs]
 * [Full GC (Allocation Failure)  186557K->186557K(195584K), 0.2891143 secs]
 * [Full GC (Ergonomics)  186903K->186558K(195584K), 0.3340137 secs]
 * [Full GC (Allocation Failure)  186558K->186558K(195584K), 0.3861979 secs]
 * java.lang.OutOfMemoryError: Java heap space[Full GC (Ergonomics)  186561K->186557K(195584K), 0.3900227 secs]
 * [Full GC (Allocation Failure)  186557K->186557K(195584K), 0.3110169 secs]
 *
 * java.lang.OutOfMemoryError: Java heap space
 * java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-14" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-11" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-24" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-28" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-44" [Full GC (Ergonomics)  186969K->186555K(195584K), 0.4254468 secs]
 * [Full GC (Allocation Failure)  186555K->186555K(195584K), 0.3580492 secs]
 * Exception in thread "pool-1-thread-9" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186967K->186553K(195584K), 0.3322384 secs]
 * [Full GC (Allocation Failure)  186553K->186553K(195584K), 0.2923701 secs]
 * java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-13" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186593K->186552K(195584K), 0.3040362 secs]
 * [Full GC (Allocation Failure)  186552K->186552K(195584K), 0.2703331 secs]
 * Exception in thread "pool-1-thread-34" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186759K->186552K(195584K), 0.2896936 secs]
 * [Full GC (Allocation Failure)  186552K->186552K(195584K), 0.2665594 secs]
 * Exception in thread "pool-1-thread-25" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186964K->186552K(195584K), 0.2872563 secs]
 * [Full GC (Ergonomics)  186963K->186552K(195584K), 0.2701190 secs]
 * [Full GC (Ergonomics)  186962K->186552K(195584K), 0.2708461 secs]
 * [Full GC (Ergonomics)  186962K->186552K(195584K), 0.4208525 secs]
 * [Full GC (Ergonomics)  186962K->186552K(195584K), 0.2769071 secs]
 * [Full GC (Ergonomics)  186962K->186552K(195584K), 0.2791404 secs]
 * [Full GC (Ergonomics)  186962K->186552K(195584K), 0.2802849 secs]
 * [Full GC (Ergonomics)  186962K->186552K(195584K), 0.2854217 secs]
 * [Full GC (Ergonomics)  186962K->186552K(195584K), 0.2759878 secs]
 * [Full GC (Ergonomics)  186962K->186552K(195584K), 0.2794000 secs]
 * [Full GC (Ergonomics)  186962K->186552K(195584K), 0.2689143 secs]
 * [Full GC (Ergonomics)  186961K->186552K(195584K), 0.2616817 secs]
 * [Full GC (Allocation Failure)  186552K->186552K(195584K), 0.3108818 secs]
 * Exception in thread "pool-1-thread-17" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186610K->186552K(195584K), 0.3162800 secs]
 * [Full GC (Allocation Failure)  186552K->186552K(195584K), 0.2787163 secs]
 * [Full GC (Ergonomics)  186554K->186552K(195584K), 0.3480912 secs]
 * [Full GC (Allocation Failure)  186552K->186552K(195584K), 0.2900403 secs]
 * Exception in thread "pool-1-thread-42" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186620K->186552K(195584K), 0.3564788 secs]
 * [Full GC (Allocation Failure)  186552K->186552K(195584K), 0.2864713 secs]
 * Exception in thread "pool-1-thread-22" [Full GC (Ergonomics)  186620K->186552K(195584K), 0.4099795 secs]
 * [Full GC (Allocation Failure)  186552K->186552K(195584K), 0.2882155 secs]
 * java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-2" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-48" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186773K->186551K(195584K), 0.3544831 secs]
 * [Full GC (Allocation Failure)  186551K->186551K(195584K), 0.2757585 secs]
 * [Full GC (Ergonomics)  186959K->186551K(195584K), 0.3103393 secs]
 * [Full GC (Allocation Failure)  186551K->186551K(195584K), 0.2774254 secs]
 * Exception in thread "pool-1-thread-46" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-52" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186858K->186551K(195584K), 0.3462726 secs]
 * [Full GC (Allocation Failure)  186551K->186551K(195584K), 0.4378532 secs]
 * [Full GC (Ergonomics)  186551K->186550K(195584K), 0.3519090 secs]
 * [Full GC (Allocation Failure)  186550K->186550K(195584K), 0.3881315 secs]
 * [Full GC (Ergonomics)  186588K->186551K(195584K), 0.3507118 secs]
 * [Full GC (Allocation Failure)  186551K->186551K(195584K), 0.2687358 secs]
 * [Full GC (Ergonomics)  186556K->186551K(195584K), 0.3467148 secs]
 * [Full GC (Allocation Failure)  186551K->186551K(195584K), 0.2807722 secs]
 * Exception in thread "pool-1-thread-51" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-31" Exception in thread "pool-1-thread-26" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-68" java.lang.OutOfMemoryError: Java heap space
 * java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186960K->186550K(195584K), 0.3474256 secs]
 * [Full GC (Ergonomics)  186957K->186550K(195584K), 0.4019079 secs]
 * [Full GC (Ergonomics)  186957K->186550K(195584K), 0.3296597 secs]
 * [Full GC (Ergonomics)  186956K->186550K(195584K), 0.3968557 secs]
 * [Full GC (Ergonomics)  186956K->186550K(195584K), 0.2618606 secs]
 * [Full GC (Ergonomics)  186956K->186550K(195584K), 0.3073308 secs]
 * [Full GC (Ergonomics)  186956K->186550K(195584K), 0.3004776 secs]
 * [Full GC (Ergonomics)  186847K->186550K(195584K), 0.2669235 secs]
 * [Full GC (Allocation Failure)  186550K->186550K(195584K), 0.2658707 secs]
 * Exception in thread "pool-1-thread-73" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186580K->186550K(195584K), 0.2663443 secs]
 * [Full GC (Allocation Failure)  186550K->186550K(195584K), 0.2711479 secs]
 * [Full GC (Ergonomics)  186554K->186550K(195584K), 0.2685407 secs]
 * [Full GC (Allocation Failure)  186550K->186550K(195584K), 0.2810727 secs]
 * Exception in thread "pool-1-thread-71" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186591K->186550K(195584K), 0.3350011 secs]
 * [Full GC (Allocation Failure)  186550K->186550K(195584K), 0.2657161 secs]
 * Exception in thread "pool-1-thread-49" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186640K->186550K(195584K), 0.3540108 secs]
 * [Full GC (Allocation Failure)  186550K->186550K(195584K), 0.2715875 secs]
 * [Full GC (Ergonomics)  186553K->186550K(195584K), 0.3888285 secs]
 * [Full GC (Allocation Failure)  186550K->186550K(195584K), 0.2655599 secs]
 * [Full GC (Ergonomics)  186552K->186550K(195584K), 0.2739852 secs]
 * [Full GC (Allocation Failure)  186550K->186550K(195584K), 0.2603416 secs]
 * Exception in thread "pool-1-thread-41" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-4" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-5" java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "pool-1-thread-12" java.lang.OutOfMemoryError: Java heap space
 * [Full GC (Ergonomics)  186957K->186548K(195584K), 0.3567689 secs]
 * [Full GC (Ergonomics)  186954K->186548K(195584K), 0.2822912 secs]
 * [Full GC (Ergonomics)  186899K->186548K(195584K), 0.2756064 secs]
 * [Full GC (Allocation Failure)  186548K->186548K(195584K), 0.2806191 secs]
 * Exception in thread "pool-1-thread-61" java.lang.OutOfMemoryError: Java heap space
 *
 * jinfo 51
 * Attaching to process ID 51, please wait...
 * Debugger attached successfully.
 * Server compiler detected.
 * JVM version is 25.312-b07
 * Java System Properties:
 *
 * java.runtime.name = OpenJDK Runtime Environment
 * java.vm.version = 25.312-b07
 * sun.boot.library.path = /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre/lib/amd64
 * java.vendor.url = https://www.redhat.com/
 * java.vm.vendor = Red Hat, Inc.
 * path.separator = :
 * file.encoding.pkg = sun.io
 * java.vm.name = OpenJDK 64-Bit Server VM
 * sun.os.patch.level = unknown
 * sun.java.launcher = SUN_STANDARD
 * user.country = US
 * user.dir = /var/local/images
 * java.vm.specification.name = Java Virtual Machine Specification
 * java.runtime.version = 1.8.0_312-b07
 * java.awt.graphicsenv = sun.awt.X11GraphicsEnvironment
 * os.arch = amd64
 * java.endorsed.dirs = /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre/lib/endorsed
 * java.io.tmpdir = /tmp
 * line.separator =
 *
 * java.vm.specification.vendor = Oracle Corporation
 * os.name = Linux
 * sun.jnu.encoding = ANSI_X3.4-1968
 * java.library.path = /usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib
 * java.specification.name = Java Platform API Specification
 * java.class.version = 52.0
 * sun.management.compiler = HotSpot 64-Bit Tiered Compilers
 * os.version = 5.15.49-linuxkit
 * user.home = /root
 * user.timezone =
 * java.awt.printerjob = sun.print.PSPrinterJob
 * file.encoding = ANSI_X3.4-1968
 * java.specification.version = 1.8
 * user.name = root
 * java.class.path = .
 * java.vm.specification.version = 1.8
 * sun.arch.data.model = 64
 * sun.java.command = liufeng.jdk.jvm.tuning.CardTuning
 * java.home = /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre
 * user.language = en
 * java.specification.vendor = Oracle Corporation
 * awt.toolkit = sun.awt.X11.XToolkit
 * java.vm.info = mixed mode
 * java.version = 1.8.0_312
 * java.ext.dirs = /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre/lib/ext:/usr/java/packages/lib/ext
 * sun.boot.class.path = /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre/lib/resources.jar:/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre/lib/rt.jar:/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre/lib/sunrsasign.jar:/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre/lib/jsse.jar:/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre/lib/jce.jar:/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre/lib/charsets.jar:/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre/lib/jfr.jar:/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64/jre/classes
 * java.vendor = Red Hat, Inc.
 * file.separator = /
 * java.vendor.url.bug = https://bugzilla.redhat.com/enter_bug.cgi?product=Red%20Hat%20Enterprise%20Linux%208&component=java-1.8.0-openjdk
 * sun.io.unicode.encoding = UnicodeLittle
 * sun.cpu.endian = little
 * sun.cpu.isalist =
 *
 * VM Flags:
 * Non-default VM flags: -XX:CICompilerCount=3 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200 -XX:MaxNewSize=69730304 -XX:MinHeapDeltaBytes=524288 -XX:NewSize=69730304 -XX:OldSize=139984896 -XX:+PrintGC -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseParallelGC
 * Command line:  -Xms200M -Xmx200M -XX:+PrintGC
 *
 *
 * jstat -gc 51 500
 *  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
 * 8192.0 8192.0 8190.3  0.0   51712.0  45212.3   136704.0   12856.0   4864.0 4665.0 512.0  452.4       2    0.023   0      0.000    0.023
 * 8192.0 8192.0 8190.3  0.0   51712.0  45212.3   136704.0   12856.0   4864.0 4665.0 512.0  452.4       2    0.023   0      0.000    0.023
 * 8192.0 8192.0 8190.3  0.0   51712.0  45212.3   136704.0   12856.0   4864.0 4665.0 512.0  452.4       2    0.023   0      0.000    0.023
 * 8192.0 8192.0 8190.3  0.0   51712.0  45212.3   136704.0   12856.0   4864.0 4665.0 512.0  452.4       2    0.023   0      0.000    0.023
 * 8192.0 8192.0 8190.3  0.0   51712.0  45734.8   136704.0   12856.0   4864.0 4665.0 512.0  452.4       2    0.023   0      0.000    0.023
 * 8192.0 8192.0 8190.3  0.0   51712.0  45734.8   136704.0   12856.0   4864.0 4665.0 512.0  452.4       2    0.023   0      0.000    0.023
 * 8192.0 8192.0 8190.3  0.0   51712.0  45734.8   136704.0   12856.0   4864.0 4665.0 512.0  452.4       2    0.023   0      0.000    0.023
 *
 * top
 *    51 root      20   0 3704672 277196  16804 S 106.0   9.9  11:50.03 java
 *
 * top -Hp 51
 *    72 root      20   0 3704672 277196  16804 S   5.6   9.9   0:16.10 pool-1-thread-7
 *   202 root      20   0 3704672 277196  16804 R   4.3   9.9   0:08.85 pool-1-thread-6
 *    68 root      20   0 3704672 277196  16804 S   2.0   9.9   0:16.55 pool-1-thread-3
 *   104 root      20   0 3704672 277196  16804 S   2.0   9.9   0:16.27 pool-1-thread-3
 *   203 root      20   0 3704672 277196  16804 S   2.0   9.9   0:08.27 pool-1-thread-6
 *    71 root      20   0 3704672 277196  16804 S   1.7   9.9   0:16.18 pool-1-thread-6
 *    83 root      20   0 3704672 277196  16804 S   1.7   9.9   0:16.19 pool-1-thread-1
 *    86 root      20   0 3704672 277196  16804 S   1.7   9.9   0:15.94 pool-1-thread-2
 *   105 root      20   0 3704672 277196  16804 S   1.7   9.9   0:16.22 pool-1-thread-4
 *   110 root      20   0 3704672 277196  16804 S   1.7   9.9   0:16.27 pool-1-thread-4
 *   189 root      20   0 3704672 277196  16804 S   1.7   9.9   0:08.21 pool-1-thread-5
 *   190 root      20   0 3704672 277196  16804 S   1.7   9.9   0:08.60 pool-1-thread-5
 *   195 root      20   0 3704672 277196  16804 S   1.7   9.9   0:08.46 pool-1-thread-5
 *   213 root      20   0 3704672 277196  16804 S   1.7   9.9   0:08.73 pool-1-thread-7
 *   214 root      20   0 3704672 277196  16804 S   1.7   9.9   0:08.45 pool-1-thread-7
 *   219 root      20   0 3704672 277196  16804 S   1.7   9.9   0:08.69 pool-1-thread-8
 *   220 root      20   0 3704672 277196  16804 S   1.7   9.9   0:08.52 pool-1-thread-8
 *   223 root      20   0 3704672 277196  16804 S   1.7   9.9   0:08.60 pool-1-thread-8
 *    73 root      20   0 3704672 277196  16804 S   1.3   9.9   0:16.48 pool-1-thread-8
 *    80 root      20   0 3704672 277196  16804 S   1.3   9.9   0:15.97 pool-1-thread-1
 *    88 root      20   0 3704672 277196  16804 S   1.3   9.9   0:16.95 pool-1-thread-2
 *    95 root      20   0 3704672 277196  16804 S   1.3   9.9   0:16.15 pool-1-thread-3
 *
 * jstack 51 |more
 * 2023-04-14 09:06:36
 * Full thread dump OpenJDK 64-Bit Server VM (25.312-b07 mixed mode):
 *
 * "Attach Listener" #97 daemon prio=9 os_prio=0 tid=0x00007f9754001800 nid=0xf8 waiting on c
 * ondition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "pool-1-thread-87" #96 prio=5 os_prio=0 tid=0x00007f9728005000 nid=0xdf waiting on conditi
 * on [0x00007f97697dd000]
 *    java.lang.Thread.State: WAITING (parking)
 * 	at sun.misc.Unsafe.park(Native Method)
 * 	- parking to wait for  <0x00000000f9625b30> (a java.util.concurrent.locks.Abstract
 * QueuedSynchronizer$ConditionObject)
 * 	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
 * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(Abs
 * tractQueuedSynchronizer.java:2039)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(Schedule
 * dThreadPoolExecutor.java:1088)
 * 	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(Schedule
 * dThreadPoolExecutor.java:809)
 * 	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
 * 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
 * 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 * 	at java.lang.Thread.run(Thread.java:748)
 *
 * "pool-1-thread-85" #95 prio=5 os_prio=0 tid=0x00007f9718004800 nid=0xde waiting on conditi
 * on [0x00007f9769ee4000]
 *    java.lang.Thread.State: WAITING (parking)
 *
 *
 * jmap -histo 51
 *
 *  num     #instances         #bytes  class name
 * ----------------------------------------------
 *    1:        898656       64703232  java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask
 *    2:        898682       35947280  java.math.BigDecimal
 *    3:        898656       28756992  liufeng.jdk.jvm.tuning.CardTuning$CardInfo
 *    4:        898656       21567744  java.util.Date
 *    5:        898656       21567744  java.util.concurrent.Executors$RunnableAdapter
 *    6:        898656       14378496  liufeng.jdk.jvm.tuning.CardTuning$$Lambda$2/1406718218
 *    7:             1        3594640  [Ljava.util.concurrent.RunnableScheduledFuture;
 *    8:          1621         133192  [C
 *    9:           333         103752  [I
 *   10:           766          88144  java.lang.Class
 *   11:          1607          38568  java.lang.String
 *   12:           825          37664  [Ljava.lang.Object;
 *   13:            14          25336  [B
 *   14:            57          21432  java.lang.Thread
 *   15:           414          13248  java.util.concurrent.locks.AbstractQueuedSynchronizer$Node
 *   16:           188          10528  java.lang.invoke.MemberName
 *   17:           276           8832  java.util.concurrent.ConcurrentHashMap$Node
 *   18:           185           7400  java.lang.ref.SoftReference
 *   19:           196           6272  java.lang.invoke.LambdaForm$Name
 *   20:           256           6144  java.lang.Long
 *   21:            94           5640  [Ljava.lang.ref.SoftReference;
 *   22:           176           5632  java.lang.invoke.MethodType$ConcurrentWeakInternSet$WeakEntry
 *
 *   jmap -dump:format=b,file=20230414.hprof 51
 *   导出堆文件
 *   jvisualvm装入文件即可，也可以远程链接，但大多时候没有权限
 */
public class CardTuning {

    private static class CardInfo {
        BigDecimal price = new BigDecimal("0.0");
        String name = "张三";
        int age = 5;
        Date birthday = new Date();

        public void m() {

        }
    }

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50, new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);
        for (; ; ) {
            modelfit();
            Thread.sleep(100);
        }

    }

    private static void modelfit() {
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(cardInfo -> {
            executor.scheduleWithFixedDelay(() -> {
                cardInfo.m();
            }, 2, 3, TimeUnit.SECONDS);
        });
    }

    private static List<CardInfo> getAllCardInfo(){
        List<CardInfo> taskList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CardInfo cardInfo = new CardInfo();
            taskList.add(cardInfo);
        }
        return taskList;
    }
}
