package liufeng.jdk.jvm.compile.strategy.cp;

import com.alibaba.fastjson.JSONObject;
import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * field
 * tag u1
 * 指向classInfo的index u2
 * 指向NameAndType的index u2
 */
public class FieldStrategy extends CpStrategy {
    @Override
    public int compile(byte[] bytes, int index) {
        // name_index占用2个字节
        int offset = 2;
        int classIndex = ByteReader.readBytesToInt(bytes, index, offset);
        int nameTypeIndex = ByteReader.readBytesToInt(bytes, index + 2, offset);
        int totalOffset = offset * 2;
        ConstantsForm.add(this.constantsForm, new JSONObject()
                .fluentPut("fIndex", classIndex)
                .fluentPut("sIndex", nameTypeIndex), totalOffset);
        // tag 1字节，name_index 2字节
        return totalOffset;
    }
}
