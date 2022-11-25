package liufeng.algo.leecode.moderate;

import org.junit.Test;

import java.util.LinkedList;

public class Reverse {
    @Test
    public void reverse() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE - 1);
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
        System.out.println(reverse(0));
        System.out.println(reverse(Integer.parseInt("2147483647")));
        System.out.println(reverse(Integer.parseInt("1534236469")));
        System.out.println(reverse(Integer.parseInt("-2147483648")));
    }

    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) {
            return 0;
        }
        boolean ne = x < 0;
        x = Math.abs(x);
        LinkedList<Integer> stock = new LinkedList<>();
        while (true) {
            if (x < 10) {
                stock.add(x);
                break;
            }
            int mod = x % 10;
            stock.add(mod);
            x /= 10;
        }
        // System.out.println(stock);
        int num = stock.removeFirst();
        while (!stock.isEmpty()) {
            Integer first = stock.removeFirst();
            if (num == 214748364 && stock.isEmpty()) {
                if ((first > 8 && ne) || (!ne && first > 7)) {
                    return 0;
                } else if (first == 8 && ne) {
                    return Integer.MIN_VALUE;
                } else if (first == 7 && !ne) {
                    return Integer.MAX_VALUE;
                }
            } else if (num > 214748364) {
                return 0;
            }
            // System.out.println(num + " " + first + " " + (num * 10 + first) + " " + (num * 10));
            num = num * 10 + first;
            // System.out.println(num + " " + stock);
        }
        return ne ? num * -1 : num;
    }
}
