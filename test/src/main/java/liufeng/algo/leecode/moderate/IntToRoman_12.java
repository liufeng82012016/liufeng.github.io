package liufeng.algo.leecode.moderate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 罗马数字转整数
 */
public class IntToRoman_12 {

    @Test
    public void intToRoman() {
        String s = intToRoman(58);
        System.out.println(s);
        s = intToRoman2(58);
        System.out.println(s);
    }

    /**
     * 执行用时：4ms,在所有Java提交中击败了99.81%的用户
     * 内存消耗：37.9MB,在所有Java提交中击败了60.94%的用户
     * 通过测试用例：3999 / 3999
     * 思路：取余数进行判断
     */
    public String intToRoman(int num) {
        int one = 1, five = 5, ten = 10, multiple = 1;
        StringBuilder str = new StringBuilder();
        while (num >= one) {
            // 每次取余数进行操作
            int current = num % 10;
            if (current == ten - 1) {
                // 临界值9判断
                str.append(getByValue(ten * multiple));
                str.append(getByValue(one * multiple));
            } else if (current == five - 1) {
                // 临界值4判断
                str.append(getByValue(five * multiple));
                str.append(getByValue(one * multiple));
            } else {
                // 非临界值，拆分为5和余数
                int i = current % five;
                while (i > 0) {
                    str.append(getByValue(one * multiple));
                    i--;
                }
                int i1 = (current - i) / five;
                while (i1 > 0) {
                    str.append(getByValue(five * multiple));
                    i1--;
                }
            }
            num /= 10;
            multiple *= 10;
        }
        // 倒序拼接
        return str.reverse().toString();
    }

    private char getByValue(int value) {
        switch (value) {
            case 1:
                return 'I';
            case 5:
                return 'V';
            case 10:
                return 'X';
            case 50:
                return 'L';
            case 100:
                return 'C';
            case 500:
                return 'D';
            case 1000:
                return 'M';
            default:
                throw new RuntimeException("unknown value" + value);
        }
    }

    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了15.56%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了7.25%的用户
     * 通过测试用例：3999 / 3999
     */
    public String intToRoman2(int num) {
        Map<Integer, String> dict = new HashMap<>();
        List<Integer> values = new ArrayList<>();
        dict.put(1000, "M");
        dict.put(900, "CM");
        dict.put(500, "D");
        dict.put(400, "CD");
        dict.put(100, "C");
        dict.put(90, "XC");
        dict.put(50, "L");
        dict.put(40, "XL");
        dict.put(10, "X");
        dict.put(9, "IX");
        dict.put(5, "V");
        dict.put(4, "IV");
        dict.put(1, "I");
        values.add(1000);
        values.add(900);
        values.add(500);
        values.add(400);
        values.add(100);
        values.add(90);
        values.add(50);
        values.add(40);
        values.add(10);
        values.add(9);
        values.add(5);
        values.add(4);
        values.add(1);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            Integer value = values.get(i);
            while (num >= value) {
                str.append(dict.get(value));
                num -= value;
            }
        }
        return str.toString();
    }
}
