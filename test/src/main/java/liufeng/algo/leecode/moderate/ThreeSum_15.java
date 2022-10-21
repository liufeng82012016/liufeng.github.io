package liufeng.algo.leecode.moderate;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 三数之和
 * 和为0的不重复组合
 */
public class ThreeSum_15 {
    @Test
    public void three() {
        int[] nums = {1, 1, 1};
        System.out.println(threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        Set<String> items = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    // 等于0，满足条件
                    String itemStr = String.valueOf(nums[i]) + nums[left] + nums[right];
                    if (items.contains(itemStr)) {
                    } else {
                        items.add(itemStr);
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    }
                    // 计算偏移量，移动偏移量小的
                    int rightDiff = nums[right] - nums[right - 1];
                    int leftDiff = nums[left + 1] - nums[left];
                    if (leftDiff < rightDiff) {
                        right--;
                    } else {
                        left++;
                    }
                } else if (sum > 0) {
                    while (true) {
                        if (left < right && nums[right] == nums[right - 1]) {
                            // 如果是重复的值，移动
                            right--;
                        } else {
                            right--;
                            break;
                        }
                    }
                } else {
                    while (true) {
                        if (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        } else {
                            left++;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
}
