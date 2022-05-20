package liufeng.jdk.jvm.compile.strategy;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.strategy.cp.CpStrategy;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 常量池解析
 */
public class ConstantsStrategy extends Strategy {
    @Override
    public int compile(byte[] bytes, int index) {
        int length = 0;
        // 遍历解析常量池的结构
        for (int i = 0; i < this.classForm.pre().getCounter() - 1; i++) {
            // 常量池表项第一个字节值为tag，对应1-12
            int tag = ByteReader.readBytesToInt(bytes, index, CpStrategy.TAG_LENGTH);
            ConstantsForm constantsForm = ConstantsForm.getByTag(tag);
            if (constantsForm.getCpStrategy() == null) {
                throw new RuntimeException(constantsForm + " 未配置策略");
            }
            int len = constantsForm.getCpStrategy().compile(bytes, index, constantsForm);
            index += len;
//            System.out.println("index: " + index + ",value:" + bytes[index]);
        }
        return length;
    }
}
