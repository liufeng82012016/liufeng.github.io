package liufeng.jdk.collection;

import org.junit.Test;

import java.util.Stack;

/**
 * 栈
 */
public class StackTest {

    @Test
    public void reverseOrderOutput() {
        int value = 13;
        Stack<Integer> stack = new Stack<>();
        while (value > 0) {
            stack.push(value % 2);
            value /= 2;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }


}
