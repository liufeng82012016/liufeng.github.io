package liufeng.util;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {
    private static final Random RANDOM = new Random();

    private static final char ALPHA[] = "abcdefghjkmnpqrstuvwxyz123456789".toCharArray();
    private static final char SEGMA[] = "abcdefghjkmnpqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM123456789".toCharArray();
    private static final char BETA[] = "0123456789".toCharArray();

    private static int DEFAULT_LEN = 6;

    public static String getRandomCode() {
        return getRandomCode(DEFAULT_LEN);
    }

    public static String getRandomCode(int len) {
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < len; i++) {
            code.append(ALPHA[RANDOM.nextInt(ALPHA.length)]);
        }
        return code.toString();
    }

    public static String getBigRandomCode(int len) {
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < len; i++) {
            code.append(SEGMA[RANDOM.nextInt(SEGMA.length)]);
        }
        return code.toString();
    }

    public static String getRandomCodeInt(int len) {
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < len; i++) {
            code.append(BETA[RANDOM.nextInt(BETA.length)]);
        }
        return code.toString();
    }

    public static int randomInt(int start, int end) {
        return start + RANDOM.nextInt(end - start + 1);
    }

    public static double randomDouble(int start, int end) {
        return start + (end - start) * RANDOM.nextDouble();
    }

    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }

}
