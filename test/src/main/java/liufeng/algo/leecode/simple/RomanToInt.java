package liufeng.algo.leecode.simple;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转数字
 */
public class RomanToInt {

    @Test
    public void romanToInt() {
        System.out.println(romanToInt("LVIII"));
    }

    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了62.59%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了57.83%的用户
     * 通过测试用例：3999 / 3999
     */
    public int romanToInt(String s) {
        Map<Character, Integer> dict = new HashMap<>();
        dict.put('M', 1000);
        dict.put('D', 500);
        dict.put('C', 100);
        dict.put('L', 50);
        dict.put('X', 10);
        dict.put('V', 5);
        dict.put('I', 1);
        char[] chars = s.toCharArray();
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            char c1 = chars[i];
            int v;
            if (i == chars.length - 1) {
                // 最后一个元素
                v = dict.get(c1);
            } else {
                // 非最后一个元素
                char c2 = chars[i + 1];
                int v1 = dict.get(c1);
                int v2 = dict.get(c2);
                if (v1 < v2) {
                    v = v2 - v1;
                    i++;
                } else {
                    v = v1;
                }
            }
            num += v;
        }
        return num;
    }
}
