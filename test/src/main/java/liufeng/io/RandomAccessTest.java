package liufeng.io;

import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * 随机访问文件测试
 *
 * @author liufeng
 */
public class RandomAccessTest {
    public static void main(String[] args) throws Exception {
        String path = "/Users/liufeng/IdeaProjects/duibatest/test/src/main/java/test/common/a.json";
        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
        Employee e1 = new Employee("zhangsan", 23);
        Employee e2 = new Employee("lisi", 24);
        Employee e3 = new Employee("wangwu", 25);
        for (int i = 0; i < 100; i++) {
            randomAccessFile.write(e1.name.getBytes(StandardCharsets.UTF_8));
            randomAccessFile.writeInt(e1.age);
            randomAccessFile.write(e2.name.getBytes(StandardCharsets.UTF_8));
            randomAccessFile.writeInt(e2.age);
            randomAccessFile.write(e3.name.getBytes(StandardCharsets.UTF_8));
            randomAccessFile.writeInt(e3.age);
        }
        randomAccessFile.close();

        RandomAccessFile randomAccessFile2 = new RandomAccessFile(path, "r");
        randomAccessFile2.skipBytes(12);
        System.out.println("第二个员工信息");
        String str = "";
        for (int i = 0; i < Employee.LEN; i++) {
            str += (char) randomAccessFile2.readByte();
        }
        System.out.println("name:" + str);
        System.out.println("age:" + randomAccessFile2.readInt());
        System.out.println("第一个员工信息");
        randomAccessFile2.seek(0);
        str = "";
        for (int i = 0; i < Employee.LEN; i++) {
            str += (char) randomAccessFile2.readByte();
        }
        System.out.println("name:" + str);
        System.out.println("age:" + randomAccessFile2.readInt());
        randomAccessFile2.skipBytes(12);
        str = "";
        for (int i = 0; i < Employee.LEN; i++) {
            str += (char) randomAccessFile2.readByte();
        }
        System.out.println("第三个员工信息");
        System.out.println("name:" + str);
        System.out.println("age:" + randomAccessFile2.readInt());
        randomAccessFile2.close();
    }

    static class Employee {
        String name;
        int age;
        final static int LEN = 8;

        public Employee(String name, int age) {
            if (name.length() > LEN) {
                name = name.substring(0, 8);
            } else {
                while (name.length() < LEN) {
                    name += "\u0000";
                }
            }
            this.name = name;
            this.age = age;
        }
    }
}
