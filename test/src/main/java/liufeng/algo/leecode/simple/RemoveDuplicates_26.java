package liufeng.algo.leecode.simple;

import org.junit.Test;

public class RemoveDuplicates_26 {
    @Test
    public void removeDuplicates() {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
    }

    /**
     * 26. 删除有序数组中的重复项
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     */
    public int removeDuplicates(int[] nums) {
        int remove = 0;
        Integer last = null;
        Integer index = null;
        for (int i = 0; i < nums.length; i++) {
            // 和数组上一个元素比对是否重复，重复则计数加一，不重复就移动数据(也可能不移动)
            if (last == null) {
                last = nums[i];
                index = 0;
            } else {
                if (last == nums[i]) {
                    remove++;
                } else {
                    last = nums[i];
                    nums[index + 1] = last;
                    index++;
                }
            }
        }
        return nums.length - remove;
    }
}
