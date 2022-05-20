package liufeng.jdk.jvm.compile.strategy;

import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 解析count计数型
 */
public class CountStrategy extends Strategy {
    @Override
    public int compile(byte[] bytes, int index) {
        int count = ByteReader.readBytesToInt(bytes, index, classForm.getLength());
        if (classForm.isPrint()) {
            System.out.println(this.classForm + ":" + count);
        }
        // 后面的count会覆盖前面的count，因为class的顺序是严格固定的，所以数量不会出错
        classForm.setCounter(count);
        return classForm.getLength();
    }
}
