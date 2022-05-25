package liufeng.jdk.jvm.compile.strategy;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;
import liufeng.jdk.jvm.compile.strategy.cp.CpStrategy;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 常量池解析
 * 偶尔会出现常量池数量对不上的问题，原因：Long和Double在常量池占据2个条目
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
            if (constantsForm == null && ConstantsForm.checkIfEnd(this.classForm.pre().getCounter())) {
                // Long/Double 会占用2个槽位，可能会出现遍历未结束，但实际上解析完成的情况
                break;
            }
            if (constantsForm == null || constantsForm.getCpStrategy() == null) {
                throw new RuntimeException(String.format("%s 未配置策略,index:%s tag=%s", constantsForm, index, tag));
            }
            int len = constantsForm.getCpStrategy().compile(bytes, index, constantsForm);
            // 总长度+
            length += len;
            // 下标移动+
            index += len;
        }
        return length;
    }
}
