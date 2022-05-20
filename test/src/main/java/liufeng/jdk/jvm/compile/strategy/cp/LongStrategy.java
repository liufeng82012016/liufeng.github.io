package liufeng.jdk.jvm.compile.strategy.cp;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 长整数读取
 */
public class LongStrategy extends CpStrategy {
    @Override
    public int compile(byte[] bytes, int index) {
        int offset = 8;
        long value = ByteReader.readBytesToLong(bytes, index);
        ConstantsForm.add(this.constantsForm, value, offset);
        return offset;
    }
}
