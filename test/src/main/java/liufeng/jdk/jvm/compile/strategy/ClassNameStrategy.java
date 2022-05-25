package liufeng.jdk.jvm.compile.strategy;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 类名解析，指向一个常量池的class引用 u2
 */
public class ClassNameStrategy extends Strategy {
    @Override
    public int compile(byte[] bytes, int index) {
        int offset = 2;
        int constantsPoolIndex = ByteReader.readBytesToInt(bytes, index, 2);
        ConstantsForm.ConstantsItem constantItem = ConstantsForm.getConstantItem(constantsPoolIndex);
        if (classForm.isPrint()) {
            String className = (String) ConstantsForm.getConstantItem((Integer) constantItem.getValue()).getValue();
            System.out.println(String.format("%s index is %s,class name is %s", classForm, constantsPoolIndex, className));
        }
        return offset;
    }
}
