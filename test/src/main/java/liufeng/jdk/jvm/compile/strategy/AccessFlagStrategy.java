package liufeng.jdk.jvm.compile.strategy;

import liufeng.jdk.jvm.compile.strategy.af.AccessFlagsParser;
import liufeng.jdk.jvm.compile.strategy.af.ClassAccessParser;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 类访问标识解析 u2
 */
public class AccessFlagStrategy extends Strategy {
    private AccessFlagsParser accessFlagsParser = new ClassAccessParser();

    //    private
    @Override
    public int compile(byte[] bytes, int index) {
        int offset = 2;
        int flag = ByteReader.readBytesToInt(bytes, index, offset);
//        System.out.println(String.format("index: %s flag:%s", index,flag));
        classForm.setValue(flag);
        if (classForm.isPrint()) {
            System.out.println(this.classForm + ":" + accessFlagsParser.getClassModifier(flag));
        }
        return offset;
    }
}
