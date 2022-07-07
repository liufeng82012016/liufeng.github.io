package liufeng.jdk.jvm.load;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.util.ByteReader;

public class ClassModifier {
    /**
     * constant pool count 在class文件中索引
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;
    /**
     * utf8在常量池中的tag
     */
    private static final int CONSTANT_Utf8_info = ConstantsForm.Utf8_info.getTag();

    private static final int u1 = 1;
    private static final int u2 = 2;

    private byte[] classByte;

    public ClassModifier(byte[] classByte) {
        this.classByte = classByte;
    }

    /**
     * 修改常量池中Utf8_info常量的内容
     *
     * @param oldStr 修改前的字符串
     * @param newStr 修改后的字符串
     * @return 修改结果
     */
    public byte[] modifyUTF8Constant(String oldStr, String newStr) {
        int cpc = getConstantPoolCount();
        // 上一步读取了2字节
        int offset = CONSTANT_Utf8_info + u2;
        for (int i = 0; i < cpc; i++) {
            int tag = ByteReader.readBytesToInt(classByte, offset, u1);
            if (tag == CONSTANT_Utf8_info) {
                offset += u1;
                int utf8StrLength = ByteReader.readBytesToInt(classByte, offset, u2);
                offset += u2;
                String str = ByteReader.readBytesToString(classByte, offset, utf8StrLength);
                if (str.equalsIgnoreCase(oldStr)) {
                    byte[] strBytes = ByteReader.stringToByte(newStr);
                    byte[] strLen = ByteReader.intToBytes(strBytes.length, u2);
                    classByte = ByteReader.replaceByte(classByte, offset - u2, u2, strLen);
                    classByte = ByteReader.replaceByte(classByte, offset, utf8StrLength, strBytes);
                    // 因为utf8常量不会重复，只会替换一次
                    return classByte;
                } else {
                    offset += utf8StrLength;
                }
            } else {
                offset += ConstantsForm.getByTag(tag).getLength();
            }
        }
        return classByte;
    }

    /**
     * 获取常量池中常量的数量
     *
     * @return 常量池数量
     */
    private int getConstantPoolCount() {
        return ByteReader.readBytesToInt(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
    }
}
