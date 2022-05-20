package liufeng.jdk.jvm.compile.strategy.cp;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 字符串读取，指向一个utf8引用
 */
public class StringStrategy extends CpStrategy {
    @Override
    public int compile(byte[] bytes, int index) {
        int offset = 2;
        int value = ByteReader.readBytesToInt(bytes, index, offset);
        ConstantsForm.add(this.constantsForm, value, offset);
        return offset;
    }
}
