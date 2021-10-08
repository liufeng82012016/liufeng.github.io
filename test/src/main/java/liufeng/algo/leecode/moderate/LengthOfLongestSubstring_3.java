package liufeng.algo.leecode.moderate;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LengthOfLongestSubstring_3 {

    @Test
    public void lengthOfLongestSubstring_3() {
        String s = "bpfbhmipx";
        int length = lengthOfLongestSubstring_3(s);
        System.out.println(length);
    }

    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了82.79%的用户内存消耗：39.1 MB, 在所有 Java 提交中击败了10.34%的用户
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int beginIndex = 0;
        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer index = map.get(c);
            // 出现重复元素，移除上一个重复元素之前的字符
            if (index != null) {
                max = Math.max(max, map.size());
                for (int j = beginIndex; j <= index; j++) {
                    map.remove(s.charAt(j));
                }
                beginIndex = index + 1;
            }
            map.put(c, i);
        }
        return Math.max(max, map.size());
    }
}
