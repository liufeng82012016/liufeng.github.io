package liufeng.jdk.jvm.compile.strategy;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.util.ByteReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 解析class文件实现的类 可变长度，长度=2*interfaceCount
 */
public class InterfaceStrategy extends Strategy {
    @Override
    public int compile(byte[] bytes, int index) {
        int length = 0;
        List<ConstantsForm.ConstantsItem> value = new ArrayList<>();
        for (int i = 0; i < this.classForm.pre().getCounter(); i++) {
            // 解析
            int offset = 2;
            int constantPoolIndex = ByteReader.readBytesToInt(bytes, index, offset);
            value.add(ConstantsForm.getConstantItem(constantPoolIndex));
            length += offset;
            index += offset;
        }
        classForm.setValue(value);
        if (classForm.isPrint()) {
            String interfaceList = value.stream()
                    .map(constantsItem -> (String) ConstantsForm.getConstantItem((Integer) constantsItem.getValue()).getValue())
                    .collect(Collectors.toList()).toString();
            System.out.println(String.format("实现接口：%s", interfaceList));
        }
        return length;
    }
}
