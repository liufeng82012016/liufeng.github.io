package liufeng.algo.leecode.simple;

import org.junit.Test;

/**
 * 求数组元素的最长公共前缀
 */
public class LongCommonPrefix_14 {
    @Test
    public void longestCommonPrefix() {
        String[] strings = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strings));
    }

    public String longestCommonPrefix(String[] strs) {
        String prefix = null;
        for (String str : strs) {
            if (str == null || str.length() == 0) {
                return "";
            }
            if (prefix == null) {
                prefix = str;
            } else {
                int min = Math.min(prefix.length(), str.length());
                for (int i = 0; i < min; i++) {
                    if (prefix.charAt(i) == str.charAt(i)) {
                        // 字符比较，
                    } else {
                        if (i == 0) {
                            return "";
                        } else {
                            prefix = prefix.substring(0, i);
                            break;
                        }
                    }
                }
                if (prefix.length() > min) {
                    prefix = prefix.substring(0, min);
                }
            }
        }
        return prefix;
    }

}
