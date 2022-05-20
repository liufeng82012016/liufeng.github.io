package liufeng.jdk.jvm.compile.strategy.cp;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 单精度读取
 */
public class FloatStrategy extends CpStrategy {
    @Override
    public int compile(byte[] bytes, int index) {
        int offset = 4;
        float value = ByteReader.readBytesToFloat(bytes, index);
        ConstantsForm.add(this.constantsForm, value, offset);
        return offset;
    }
}
