package liufeng.algo.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 * 原理：
 * 1. 假定数组有元素P
 * 2. 数组被元素P分成2部分，左侧元素都比元素p小，右侧元素都比p大
 * 3. 递归调用2的排序操作，直至全部完成排序
 * 缺点：
 * 1. 递归深度不可控制
 * 2. 最坏情况下，时间复杂度为O(n²)
 * 优化方案
 * 1. 元素P取范围内随机值，不取第一个元素
 * 视频链接：https://www.bilibili.com/video/BV1UK41137q7
 */
public class QuickSort extends AbstractSortAlgo {
    private int partation(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= temp) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= temp) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = temp;
        return left;
    }

    /**
     * 快速排序实现
     *
     * @param nums
     * @param left
     * @param right
     */
    private void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partation = partation(nums, left, right);
            quickSort(nums, left, partation - 1);
            quickSort(nums, partation + 1, right);
        }
    }

    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    @Test
    public void quickSort() {
        Random random = new Random();
        int[] arr = new int[120];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000);
        }
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
