package liufeng.algo.leecode.moderate;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 括号生成
 */
public class GenerateParenthesis_22 {
    @Test
    public void generateParenthesis() {
        System.out.println(generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> results = new LinkedList<>();
        char leftChar = '(', rightChar = ')';
        if (n == 1) {
            results.add(String.valueOf(leftChar) + rightChar);
            return results;
        }
        Map<StringBuilder, Pair> cache = new HashMap<>();
        // 开始
        for (int i = 0; i < 2 * n; i++) {
            if (cache.isEmpty()) {
                cache.put(new StringBuilder().append(leftChar), new Pair(1, 0));
            } else {
                Map<StringBuilder, Pair> cache2 = new HashMap<>();
                for (Map.Entry<StringBuilder, Pair> entry : cache.entrySet()) {
                    Pair pair = entry.getValue();
                    if (pair.value() > 0) {
                        // 大于0，可以开，可以闭
                        if (pair.left < n) {
                            cache2.put(new StringBuilder().append(entry.getKey().toString()).append(leftChar), new Pair(pair, true));
                        }
                        cache2.put(new StringBuilder().append(entry.getKey().toString()).append(rightChar), new Pair(pair, false));
                    } else {
                        if (pair.left < n) {
                            // 等于0(不可能小于0)，已经配对了，下一个必须是(
                            cache2.put(new StringBuilder().append(entry.getKey().toString()).append(leftChar), new Pair(pair, true));
                        } else {
                            break;
                        }
                    }
                }
                cache = cache2;
            }
        }
        return cache.keySet().stream().map(StringBuilder::toString).collect(Collectors.toList());
    }

    class Pair {
        int left;
        int right;

        public Pair() {
        }

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public Pair(Pair pair, boolean left) {
            this.left = pair.left;
            this.right = pair.right;
            if (left) {
                this.left++;
            } else {
                this.right++;
            }
        }

        public int value() {
            return left - right;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
