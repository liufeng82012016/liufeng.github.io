package com.my.liufeng.tool.google;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 谷歌认证器，有的地方又叫做动态口令验证码
 * <!-- https://mvnrepository.com/artifact/commons-codec/commons-codec -->
 * <dependency>
 *     <groupId>commons-codec</groupId>
 *     <artifactId>commons-codec</artifactId>
 *     <version>1.15</version>
 * </dependency>
 */
public class GoogleAuthenticator {
    /**
     * 生成密钥的长度
     */
    private static final int SECRET_SIZE = 10;
    /**
     * 随机算法的seed，java的random是伪随机，相同seed的random().next()返回值相同？
     */
    private static final String SEED = "g8GjEvTbW5oVSV7avLBdwIHqGlUYNzKFI7izOF8GwLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx";

    private static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";
    /**
     * default 3 - max 17 (from google docs)最多可偏移的时间
     * 这是一个容差范围，因为客户端和服务端可能有一定的时间差，所有校验时最好兼容
     */
    static int window_size = 3;

    public static void setWindowSize(int s) {
        if (s >= 1 && s <= 17)
            window_size = s;
    }

    /**
     * 校验code
     *
     * @param code        doe
     * @param savedSecret secret
     * @return 是否通过
     */
    private static Boolean authcode(Long code, String savedSecret) {
        return checkCode(savedSecret, code, System.currentTimeMillis());
    }

    /**
     * 生成密钥
     *
     * @return secret
     */
    private static String generateSecretKey() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
            sr.setSeed(Base64.decodeBase64(SEED));
            byte[] buffer = sr.generateSeed(SECRET_SIZE);
            Base32 codec = new Base32();
            byte[] bEncodedKey = codec.encode(buffer);
            return new String(bEncodedKey);
        } catch (NoSuchAlgorithmException e) {
            // should never occur... configuration error
        }
        return null;
    }


    /**
     * 生成密钥并拼接user@host，前端通过对应地址生成二维码
     * user@host 主要是用于标记用户身份，相当于备注，大部分识别该二维码的应用会将该附加信息标记展示。
     * 如果用户在多个网站使用了动态二维码，可以用它做辨别。
     *
     * @param user   通常是username/mobile/email
     * @param host   serverHost
     * @param secret 密钥
     * @return url
     */
    public static String getQRBarcodeURL(String user, String host, String secret) {
        String format = "otpauth://totp/%s@%s?secret=%s";
        return String.format(format, user, host, secret);
    }

    /**
     * 校验验证码
     *
     * @param secret   secret
     * @param code     code
     * @param timeMsec app time
     * @return success
     */
    public static boolean checkCode(String secret, long code, long timeMsec) {
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
        // convert unix msec time into a 30 second "window"
        // this is per the TOTP spec (see the RFC for details)
        long t = (timeMsec / 1000L) / 30L;
        // Window is used to check codes generated in the near past.
        // You can use this value to tune how far you're willing to go.
        for (int i = -window_size; i <= window_size; ++i) {
            long hash;
            try {
                hash = computeHash(decodedKey, t + i);
            } catch (Exception e) {
                // Yes, this is bad form - but
                // the exceptions thrown would be rare and a static configuration problem
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
                //return false;
            }
            System.out.println("hash: " + hash);
            if (hash == code) {
                return true;
            }
        }
        // The validation code is invalid.
        return false;
    }

    /**
     * 生成对应window的hash
     *
     * @param key key
     * @param t   windows（30s为一个窗口）
     * @return hash
     */
    private static int computeHash(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;
        // We're using a long because Java hasn't got unsigned int.
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            // We are dealing with signed bytes:
            // we just keep the first byte.
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }

    /**
     * 测试校验动态验证码
     */
    @Test
    public void testCheck() {
        String secret = "*********";
        boolean checkSuccess = checkCode(secret, 28333L, System.currentTimeMillis());
        System.out.println(checkSuccess);
    }


    /**
     * 测试生成动态验证码
     */
    @Test
    public void testGenCOde() throws Exception {
        String secret = "***********";
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
        long t = (System.currentTimeMillis() / 1000L) / 30L;
        // 30s 一个窗口，这里直接使用t
        long hash = computeHash(decodedKey, t);
        // 生成的hash就是动态验证码。有的网站要求填入6位，long不足6位，前置用0补齐；
        // 如果这里的动态验证码不通过（很小概率，可能因为客户端服务端时间差），个人习惯
        System.out.println("hash: " + hash);
    }


}
