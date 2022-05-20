package liufeng.jdk.jvm.compile.strategy.cp;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.util.ByteReader;

public class ClassInfoStrategy extends CpStrategy {
    @Override
    public int compile(byte[] bytes, int index) {
        // name_index占用2个字节
        int offset = 2;
        int refIndex = ByteReader.readBytesToInt(bytes, index, offset);
        ConstantsForm.add(this.constantsForm, refIndex, offset);
        // tag 1字节，name_index 2字节
        return offset;
    }
}
