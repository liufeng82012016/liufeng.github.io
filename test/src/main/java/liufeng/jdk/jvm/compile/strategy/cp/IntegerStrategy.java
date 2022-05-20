package liufeng.jdk.jvm.compile.strategy.cp;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 整数读取
 */
public class IntegerStrategy extends CpStrategy {
    @Override
    public int compile(byte[] bytes, int index) {
        int offset = 4;
        int value = ByteReader.readBytesToInt(bytes, index);
        ConstantsForm.add(this.constantsForm, value, offset);
        // 整数一共4字节
        return offset;
    }
}
