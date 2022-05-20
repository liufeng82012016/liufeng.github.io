package liufeng.jdk.jvm.compile.strategy.cp;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 双精度读取
 */
public class DoubleStrategy extends CpStrategy {
    @Override
    public int compile(byte[] bytes, int index) {
        int offset = 8;
        double value = ByteReader.readBytesToDouble(bytes, index);
        ConstantsForm.add(this.constantsForm, value, offset);
        return offset;
    }
}
