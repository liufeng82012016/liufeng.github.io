package liufeng.jdk.jvm.compile.strategy;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.enums.FieldDescriptor;
import liufeng.jdk.jvm.compile.strategy.af.AccessFlagsParser;
import liufeng.jdk.jvm.compile.strategy.af.MethodAccessParser;
import liufeng.jdk.jvm.compile.table.FieldInfo;
import liufeng.jdk.jvm.compile.util.ByteReader;

import java.util.ArrayList;
import java.util.List;

public class MethodStrategy extends Strategy {
    /**
     * 修饰符解析器
     */
    private AccessFlagsParser accessFlagsParser = new MethodAccessParser();

    @Override
    public int compile(byte[] bytes, int index) {
        int length = 0;
        List<FieldInfo> value = new ArrayList<>();
        for (int i = 0; i < classForm.pre().getCounter(); i++) {
            FieldInfo fieldTable = new FieldInfo();
            fieldTable.setFlag(ByteReader.readBytesToInt(bytes, index, 2));
            fieldTable.setNameIndex(ByteReader.readBytesToInt(bytes, index + 2, 2));
            fieldTable.setDescriptorIndex(ByteReader.readBytesToInt(bytes, index + 4, 2));
            fieldTable.setCount(ByteReader.readBytesToInt(bytes, index + 6, 2));
            for (int j = 0; j < fieldTable.getCount(); j++) {
                // todo 解析attributes
            }
            if (classForm.isPrint()) {
                System.out.println(fieldTable);
                System.out.println(String.format("%s %s %s;", accessFlagsParser.getClassModifier(fieldTable.getFlag()),
                        FieldDescriptor.getByValue((String) ConstantsForm.getConstantItem(fieldTable.getDescriptorIndex()).getValue()),
                        ConstantsForm.getConstantItem(fieldTable.getNameIndex()).getValue()));
            }
        }
        classForm.setValue(value);
        return length;
    }
}
