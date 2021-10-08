package liufeng;

import cn.hutool.core.text.StrBuilder;
import org.junit.Test;

import java.util.*;

public class ThreeNumTotal {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums.length < 3) {
            return result;
        }
        Set<Integer> set = new HashSet<>();
        for (int v : nums) {
            set.add(v);
        }
        Set<String> container = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> value = new LinkedList<>();
                        value.add(nums[i]);
                        value.add(nums[k]);
                        value.add(nums[j]);
                        Collections.sort(value);
                        if (container.contains(value.toString())) {
                            continue;
                        }
                        container.add(value.toString());
                        result.add(value);
                    }
                }
            }
        }
        return result;
    }






    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l2 == null && l1 == null) {
            return new ListNode();
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode reuslt = new ListNode();
        ListNode l1Next = l1;
        ListNode l2Next = l2;
//        while (true) {
//            int val = (l1Next != null && l2Next != null) ? Math.min(l1Next.val, l2Next.val)
//                    : (l1Next == null ? l2Next.val : l1Next.val);
//            reuslt.val = val;
////            if (l)
//        }
        return reuslt;
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

    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        long count = 0;
        while (true) {
            count++;
            if (System.currentTimeMillis() - l >= 1000) {
                break;
            }
        }
        System.out.println("count: " + count);
    }

    private int haitone(int target){
        int count = 0;
        while (target > 1) {
            if (target % 2 == 1) {
                target = (3 * target) + 1;
            } else {
                target /= 2;
            }
            count++;
        }
        return count;
    }

    @Test
    public void t(){
        for (int i = 0; i <1000 ; i++) {
            System.out.println(i+"  "+haitone(i));
        }
    }
}
