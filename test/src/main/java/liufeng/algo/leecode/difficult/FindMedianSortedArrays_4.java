package liufeng.algo.leecode.difficult;

import org.junit.Test;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 */
public class FindMedianSortedArrays_4 {

    @Test
    public void findMedianSortedArrays() {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了55.75%的用户内存消耗：39.7 MB, 在所有 Java 提交中击败了53.87%的用户
     *
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 先合并，再取中位数
        return getMedian(merge(nums1, nums2));
    }

    public int[] merge(int[] nums1, int[] nums2) {
        int[] mergedArray = new int[nums1.length + nums2.length];
        int index1 = 0, index2 = 0, index = 0;
        while (index < mergedArray.length) {
            Integer val1 = index1 < nums1.length ? nums1[index1] : null;
            Integer val2 = index2 < nums2.length ? nums2[index2] : null;
            int val;
            if (val1 == null && val2 == null) {
                break;
            } else if (val1 != null && val2 != null) {
                if (val1 < val2) {
                    val2 = null;
                } else {
                    val1 = null;
                }
            }
            if (val1 != null) {
                val = val1;
                index1++;
            } else {
                val = val2;
                index2++;
            }
            mergedArray[index] = val;
            index++;
        }
        return mergedArray;
    }

    /**
     * 取中位数
     * 偶数个元素，取中间两个值相加除以2
     * 奇数个元素，取中间元素
     * @param nums
     * @return
     */
    public double getMedian(int[] nums) {
        int i = nums.length % 2;
        if (i == 0) {
            // 偶数个元素
            int index = nums.length / 2;
            return ((double) (nums[index - 1] + nums[index])) / 2;
        } else {
            // 奇数个元素
            return nums[nums.length / 2];
        }
    }


    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // 先合并，再取中位数
        return getMedian(merge(nums1, nums2));
    }

}
