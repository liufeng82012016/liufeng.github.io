package liufeng.jdk.jvm.compile;

import liufeng.jdk.jvm.compile.enums.ClassForm;
import liufeng.jdk.jvm.compile.util.ByteReader;
import org.junit.Test;

public class CompileTest {
    @Test
    public void read() throws Exception {
        String classpath = "/Users/liufeng/IdeaProjects/liufeng82012016.github.io/test/src/main/java/liufeng/jdk/jvm/compile/Test2Class.class";
        doCompile(ByteReader.readToBytes(classpath));
    }

    public void doCompile(byte[] bytes) {
        int index = 0;
        ClassForm classForm = ClassForm.first();
        ClassForm next;
        while ((next = classForm.next()) != null) {
            if (classForm.getStrategy() == null) {
                System.out.println(String.format("%s 策略丢失,index:%d", classForm, index));
                return;
            }
            int length = classForm.getStrategy().compile(bytes, index, classForm);
            index += length;
            classForm = next;
        }
    }

    @Test
    public void byteTest() {
        byte b = -54;
        // 54  00110110
        // -54 11001001+1=11001010
        // 0xff           11111111
        // & 11001010 128+64+8+2=202=16*12+10=ca
        System.out.println(b & 0xff);
//        436c  0100 0011 0110 1100
    }

}
