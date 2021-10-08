package liufeng.jdk.jvm.ma;

public class AgeTest {
    public static void main(String[] args) {
        int _1MB = 1024 * 1024;
        byte[] alloctiona1, alloctiona2, alloctiona3, alloctiona4;
        alloctiona1 = new byte[_1MB / 4];
        alloctiona2 = new byte[4 * _1MB];
        alloctiona3 = new byte[4 * _1MB];
        alloctiona3 = null;
        alloctiona4 = new byte[4 * _1MB];
    }
}
