package liufeng.jdk.jvm.compile.strategy;

import liufeng.jdk.jvm.compile.strategy.af.AccessFlagsParser;
import liufeng.jdk.jvm.compile.strategy.af.FieldAccessParser;
import liufeng.jdk.jvm.compile.table.FieldTable;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 解析class字段
 * Field表结构
 * access flag u2
 * name index u2
 * descriptor index u2
 * attributes count u2
 * attributes 若干
 */
public class FieldStrategy extends Strategy {
    private AccessFlagsParser accessFlagsParser = new FieldAccessParser();

    @Override
    public int compile(byte[] bytes, int index) {
        FieldTable fieldTable = new FieldTable();
        fieldTable.setFlag(ByteReader.readBytesToInt(bytes, index, 2));
        fieldTable.setNameIndex(ByteReader.readBytesToInt(bytes, index + 2, 2));
        fieldTable.setCount(ByteReader.readBytesToInt(bytes, index + 4, 2));
        for (int i = 0; i < fieldTable.getCount(); i++) {
            // 解析attributes
        }
        classForm.setValue(fieldTable);
        return 0;
    }
}
