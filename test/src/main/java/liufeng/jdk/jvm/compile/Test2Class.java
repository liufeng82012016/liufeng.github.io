package liufeng.jdk.jvm.compile;

import java.io.Serializable;

public class Test2Class implements Serializable, Comparable {
    private int m;

    private double l = 3000d;

    public int inc() {
        return m + 1;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
