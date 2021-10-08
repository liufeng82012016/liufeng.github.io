package liufeng.algo.leecode.simple;

import java.util.HashMap;
import java.util.Map;

public class TwoSum_1 {
    /**
     * 求2数之和
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int diff = target - x;
            // target减去当前数字，然后从map判断是否有值，有就返回，没有就添加到map，一直向后遍历
            if (cache.get(diff) != null) {
                int[] result = new int[2];
                result[0] = i;
                result[1] = cache.get(diff);
                return result;
            } else {
                cache.put(x, i);
            }
        }
        return null;
    }
}
