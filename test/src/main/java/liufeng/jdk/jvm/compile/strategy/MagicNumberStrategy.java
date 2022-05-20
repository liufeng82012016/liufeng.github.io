package liufeng.jdk.jvm.compile.strategy;

import liufeng.jdk.jvm.compile.constants.Constants;
import liufeng.jdk.jvm.compile.util.ByteReader;

/**
 * 处理魔数
 */
public class MagicNumberStrategy extends Strategy {
    @Override
    public int compile(byte[] bytes, int index) {
        String magicNumber = ByteReader.readBytesToHexString(bytes, index, this.classForm.getLength());
        if (!Constants.MAGIC_NUMBER.equals(magicNumber)) {
            throw new RuntimeException(this.classForm + "{" + magicNumber + "}不匹配");
        } else if (this.classForm.isPrint()) {
            System.out.println(this.classForm + ":" + magicNumber + "校验通过");
        }
        return this.classForm.getLength();
    }

}
