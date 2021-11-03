package liufeng.algo.leecode.difficult;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class LongestValidParentheses_32 {
    @Test
    public void longestValidParentheses() {
        System.out.println(longestValidParentheses("()()"));
    }

    /**
     * 执行用时：* 1 ms* , 在所有 Java 提交中击败了* 100.00%* 的用户
     * 内存消耗：* 38.4 MB* , 在所有 Java 提交中击败了* 66.38%* 的用户
     * 通过测试用例：* 231 / 231
     */
    public int longestValidParentheses(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int max = 0, sum = 0;
        char[] chars = s.toCharArray();
        int[] maxesRecord = new int[chars.length];
        Arrays.fill(maxesRecord, -1);
        for (int i = 0; i < chars.length; i++) {
            if ('(' == chars[i]) {
                sum++;
            } else {
                sum--;
                if (sum < 0) {
                    // 从新开始计数
                    sum = 0;
                } else {
                    boolean first = true;
                    for (int j = i - 1; j >= 0; j--) {
                        // 向前遍历
                        // 为什么break的时候，maxesRecord[i] = i - j;不需要+1
                        // 因为闭区间，2个数字之间数字个数，包含开头结尾；break的时候，只包含结尾，不包含开头
                        if (chars[j] == ')') {
                            if (maxesRecord[j] == -1) {
                                maxesRecord[i] = i - j;
                                max = Math.max(max, maxesRecord[i]);
                                break;
                            }
                            j -= (maxesRecord[j] - 1);
                            if (j == 0) {
                                maxesRecord[i] = i - j + 1;
                                max = Math.max(max, maxesRecord[i]);
                            }
                        } else {
                            if (first) {
                                first = false;
                            } else {
                                maxesRecord[i] = i - j;
                                max = Math.max(max, maxesRecord[i]);
                                break;
                            }
                            maxesRecord[i] = i - j + 1;
                            max = Math.max(max, maxesRecord[i]);
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(chars));
        System.out.println(Arrays.toString(maxesRecord));
        return max;
    }
}
