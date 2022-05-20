package liufeng.jdk.jvm.compile.strategy;

import liufeng.jdk.jvm.compile.enums.ClassForm;
import liufeng.jdk.jvm.compile.constants.JdkVersion;

/**
 * 处理版本号
 */
public class VersionStrategy extends Strategy {
    @Override
    public int compile(byte[] bytes, int index) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = index; i < index + this.classForm.getLength(); i++) {
            stringBuilder.append(bytes[i]);
        }
        String magicNumber = stringBuilder.toString();
        if (this.classForm.isPrint()) {
            System.out.println(this.classForm + ":" + getVersionStr(magicNumber));
        }
        return this.classForm.getLength();
    }


    /**
     * 将字符串转为对应的版本号
     */
    private String getVersionStr(String magicNumber) {
        if (classForm == ClassForm.MAJOR_VERSION) {
            return JdkVersion.getVersion(magicNumber);
        } else {
            return String.valueOf(Integer.valueOf(magicNumber));
        }
    }
}
