package liufeng.jdk.jvm.compile.strategy.cp;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.util.ByteReader;

public class Utf8Strategy extends CpStrategy {
    @Override
    public int compile(byte[] bytes, int index) {
        // 字符串长度占用2个字节
        int strLengthOffset = 2;
        int strLength = ByteReader.readBytesToInt(bytes, index, 2);
        String utf8Str = ByteReader.readBytesToString2(bytes, index + strLengthOffset, strLength);
        ConstantsForm.add(this.constantsForm, utf8Str, strLength + strLengthOffset);
        return strLength + strLengthOffset;
    }
}
