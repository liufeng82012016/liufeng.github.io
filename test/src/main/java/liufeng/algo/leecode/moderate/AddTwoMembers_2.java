package liufeng.algo.leecode.moderate;

import org.junit.Test;

/**
 * 两数相加
 */
public class AddTwoMembers_2 {
    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * <p>
     * 执行结果:通过
     * 执行用时：2 ms, 在所有 Java 提交中击败了96.70%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了41.72%的用户
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode next1 = l1;
        ListNode next2 = l2;
        ListNode next = null;
        boolean addOne = false;
        while (true) {
            int next1Val = next1 == null ? 0 : next1.val;
            int next2Val = next2 == null ? 0 : next2.val;
            int value = next1Val + next2Val;
            if (addOne) {
                value++;
            }
            addOne = value >= 10;
            if (value >= 10) {
                value -= 10;
            }
            if (result == null) {
                result = new ListNode();
                result.val = value;
            } else if (next != null) {
                next.val = value;
            } else {
                throw new RuntimeException();
            }
            next1 = next1 == null ? null : next1.next;
            next2 = next2 == null ? null : next2.next;
            if (next1 == null && next2 == null && !addOne) {
                break;
            }
            ListNode node = new ListNode();
            if (result.next == null) {
                result.next = node;
            } else {
                next.next = node;
            }
            next = node;
        }
        return result;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        ListNode(int[] values) {
            this.val = values[0];
            ListNode next = this;
            for (int i = 1; i < values.length; i++) {
                ListNode node = new ListNode(values[i]);
                next.next = node;
                next = node;
            }
        }

        void print() {
            System.out.print("[");
            System.out.print(val);
            ListNode next = this.next;
            while (next != null) {
                System.out.print("," + next.val);
                next = next.next;
            }
            System.out.println("]");
        }
    }

    @Test
    public void testPrint() {
        new ListNode(new int[]{2, 4, 3}).print();
    }

    @Test
    public void addTwoNumbers() {
        ListNode l1 = new ListNode(new int[]{9, 9, 9, 9});
        ListNode l2 = new ListNode(new int[]{9, 9, 9, 9, 9, 9, 9});
        ListNode result = addTwoNumbers(l1, l2);
        result.print();
    }

}
