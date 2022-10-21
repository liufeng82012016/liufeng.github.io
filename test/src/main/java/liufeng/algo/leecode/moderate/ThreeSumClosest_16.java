package liufeng.algo.leecode.moderate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
 * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 最接近的三数之和
 */
public class ThreeSumClosest_16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        Map<Integer, Integer> cacheMap = new HashMap<>();
        for (int i : nums) {
            Integer count = cacheMap.computeIfAbsent(i, k -> 0);
            cacheMap.put(i, count + 1);
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {

        }
        return 0;
    }

}
