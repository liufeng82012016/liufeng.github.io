package liufeng.algo.leecode.difficult;

import org.junit.Test;

/**
 * https://leetcode.cn/problems/nth-magical-number/
 * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
 * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案 对 109 + 7 取模 后的值。
 */
public class NthMagicalNumber_878 {

    @Test
    public void nthMagicalNumber() {
        int n = 3, a = 6, b = 4;
        System.out.println(nthMagicalNumber(n, a, b));
    }

    public int nthMagicalNumber(int n, int a, int b) {
        int temp2 = Math.max(a, b);
        a = Math.min(a, b);
        b = temp2;
        if (a == b || b % a == 0) {
            return mod((long) a * n);
        }

        long i = 1, p;

        while ((p = a * i) % b != 0) {
            i++;
        }
        return compute(0, a * (long) n, n, a, b, p);
    }

    private int compute(long low, long high, int n, int a, int b, long p) {
        System.out.println(low + " " + high + " " + n + " " + a + " " + b + " " + p);
        // low和high是a的倍数，middle 一定是a的倍数
        long middle = (low + high) / 2;
        long count = middle / a + middle / b - middle / p;
        if (count == n) {
            // 遍历，比较是否有较小值
            for (long i = Math.max(middle - b, 0); i <= middle; i += 1) {
                if (i % a == 0 || i % b == 0) {
                    if (i / a + i / b - i / p == n) {
                        return mod(i);
                    }
                }
            }
            return mod(middle);
        } else if (count > n) {
            // 太大了
            return compute(low, middle, n, a, b, p);
        } else {
            // 防止死循环，因为除法会自动舍去
            if (middle >= high - 1) {
                middle = high;
            }
            return compute(middle, high, n, a, b, p);
        }
    }

    private int mod(long num) {
        long v = (long) Math.pow(10, 9) + 7;
        return (int) (num % v);
    }

}
