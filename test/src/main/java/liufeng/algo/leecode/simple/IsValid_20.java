package liufeng.algo.leecode.simple;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * leecode 有效的括号（简单）
 */
public class IsValid_20 {
    @Test
    public void testIsValid() {
        boolean valid = isValid("(");
        System.out.println("valid: " + valid);
    }

    /**
     * leecode 有效的括号（简单）
     * 栈的基本运用
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] chars = s.toCharArray();
        Set<Character> symbolsLeft = new HashSet<>();
        Set<Character> symbolsRight = new HashSet<>();
        symbolsLeft.add('{');
        symbolsLeft.add('(');
        symbolsLeft.add('[');
        symbolsRight.add(']');
        symbolsRight.add('}');
        symbolsRight.add(')');
        char[] cache = new char[chars.length];
        int index = 0;
        for (char c : chars) {
            if (symbolsLeft.contains(c)) {
                cache[index] = c;
                index++;
            } else if (symbolsRight.contains(c)) {
                int lastIndex = index - 1;
                if (lastIndex < 0) {
                    return false;
                }
                char last = cache[lastIndex];
                if (c == '}' && last != '{') {
                    return false;
                }
                if (c == ']' && last != '[') {
                    return false;
                }
                if (c == ')' && last != '(') {
                    return false;
                }
                index--;
            }
        }
        return index == 0;
    }
}
