package liufeng.jdk.jvm.compile.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteReader {

    private static ByteBuffer byteBuffer8 = ByteBuffer.allocate(8);

    /**
     * 读取class文件，返回16进制字符串
     */
    public static byte[] readToBytes(String path) throws IOException {
        File file = new File(path);
        // 初始化byte数组
        byte[] classBytes = new byte[(int) file.length()];
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            // 去读class文件
            inputStream.read(classBytes);
        }
        System.out.println(Arrays.toString(classBytes));
        return classBytes;
    }

    /**
     * 读取指定的字节数，转换为字符串
     *
     * @param bytes  byte数组
     * @param index  读取的开始位置开始
     * @param offset 读取的接收位置
     * @return 字符串
     */
    public static String readBytesToString(byte[] bytes, int index, int offset) {
        StringBuilder stringBuilder = new StringBuilder();
        // 顺序读取指定字节
        for (int i = index; i < index + offset; i++) {
            stringBuilder.append(bytes[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * 读取byte数组从index开始offset字节，返回byte[]
     */
    private static byte[] readBytes(byte[] bytes, int index, int offset) {
        byte[] result = new byte[offset];
        System.arraycopy(bytes, index, result, 0, offset);
        return result;
    }

    private static void readBytesToBuffer(byte[] bytes, int index, int offset) {
        // 清除位置，从0开始写
        byteBuffer8.clear();
        byte[] bytesDest = readBytes(bytes, index, 8);
        byteBuffer8.put(bytesDest, 0, bytesDest.length);
        // 清除位置，从0开始读
        byteBuffer8.rewind();
    }

    /**
     * byteBuffer 读取8字节
     */
    public static void readBytes8(byte[] bytes, int index) {
        readBytesToBuffer(bytes, index, 8);
    }

    /**
     * byteBuffer 读取4字节
     */
    public static void readBytes4(byte[] bytes, int index) {
        readBytesToBuffer(bytes, index, 4);
    }

    /**
     * 读取指定的字节数，转换为整数
     *
     * @param bytes  byte数组
     * @param index  读取的开始位置开始
     * @param offset 字节数
     * @return 数字
     */
    public static int readBytesToInt(byte[] bytes, int index, int offset) {
        readBytesToBuffer(bytes, index, offset);
        if (offset == 1) {
            return byteBuffer8.get() & 0xff;
        } else if (offset == 2) {
            return byteBuffer8.getShort();
        }
        return byteBuffer8.getInt();
    }

    public static int readBytesToInt(byte[] bytes, int index) {
        readBytes4(bytes, index);
        return byteBuffer8.getInt();
    }

    /**
     * 读取byte数组从index开始offset字节，转换为float
     */
    public static long readBytesToFloat(byte[] bytes, int index) {
        readBytes4(bytes, index);
        return byteBuffer8.getLong();
    }


    /**
     * 读取byte数组从index开始offset字节，转换为字符串
     */
    public static long readBytesToLong(byte[] bytes, int index) {
        readBytes8(bytes, index);
        return byteBuffer8.getLong();
    }

    /**
     * 读取byte数组从index开始offset字节，转换为double
     */
    public static double readBytesToDouble(byte[] bytes, int index) {
        readBytes8(bytes, index);
        return byteBuffer8.getDouble();
    }

    /**
     * 读取byte数组从index开始offset字节，转换为字符串
     */
    public static String readBytesToString2(byte[] bytes, int index, int offset) {
        return new String(readBytes(bytes, index, offset));
    }

    /**
     * 读取指定的字节数，转换为16进制字符串
     *
     * @param bytes  byte数组
     * @param index  读取的开始位置开始
     * @param offset 读取的接收位置
     * @return 字符串
     */
    public static String readBytesToHexString(byte[] bytes, int index, int offset) {
        StringBuilder stringBuilder = new StringBuilder();
        // 顺序读取指定字节
        for (int i = index; i < index + offset; i++) {
            String value = Integer.toHexString(bytes[i] & 0xff);
            stringBuilder.append(value);
        }
        return stringBuilder.toString();
    }

    /**
     * 将整数转换为长度为16的二进制字符串，不足16位，高位以0补齐
     */
    public static String toBinaryString(int value) {
        String binaryString = Integer.toBinaryString(value);
        // 不足16位，则高位以0填充
        if (binaryString.length() < 16) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 16 - binaryString.length(); i++) {
                sb.append(0);
            }
            binaryString = sb + binaryString;
        }
        return binaryString;
    }

}
