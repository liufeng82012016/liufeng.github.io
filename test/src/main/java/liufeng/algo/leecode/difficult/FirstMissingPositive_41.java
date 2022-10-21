package liufeng.algo.leecode.difficult;

import org.junit.Test;

import java.util.Arrays;

public class FirstMissingPositive_41 {
    @Test
    public void firstMissingPositive() {
        int[] ints = {3,4};
        System.out.println(firstMissingPositive(ints));
    }

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 去除负数和超过上限的数字
            // 因为要求寻找第一个缺失的正整数，将数据放上对应的数字-1的位置
            if (num <= 0 || num > nums.length) {
                nums[i] = -1;
                continue;
            }
            nums[i] = -1;
            while (true) {
                // 无限循环，替换位置
                int origin = nums[num - 1];
                if (origin == num) {
                    break;
                } else if (origin <= 0 || origin > nums.length) {
                    nums[num - 1] = num;
                    break;
                } else {
                    int temp = nums[num - 1];
                    nums[num - 1] = num;
                    num = temp;
                    if (num <= 0 || num > nums.length) {
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
