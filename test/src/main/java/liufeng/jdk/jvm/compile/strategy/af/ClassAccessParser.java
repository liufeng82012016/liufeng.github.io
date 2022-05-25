package liufeng.jdk.jvm.compile.strategy.af;


/**
 * 访问标识 u2，最多32种标识
 */
public class ClassAccessParser extends AccessFlagsParser {


    @Override
    boolean select(int location) {
        return (location & 0x1) == 1;
    }

}
