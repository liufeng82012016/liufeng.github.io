package liufeng.algo.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序
 * 步骤：将数据分为2部分分别排序，然后合并2个排好序的子序列
 */
public class MergeSort implements SortAlgo {
    @Override
    public void sort(int[] arr) {
        mergeSort(arr, new int[arr.length], 0, arr.length);
    }

    private void mergeSort(int[] arr, int[] tempArr, int low, int high) {
        //  左闭右开区间  [)
        if (low < high - 1) {
            int middle = (low + high) / 2;
//            // System.out.println(String.format("low:%s high:%s middle:%s", low, high, middle));
            mergeSort(arr, tempArr, low, middle);
            mergeSort(arr, tempArr, middle, high);
            merge(arr, tempArr, low, middle, high);
        }
    }

    private void merge(int[] arr, int[] tempArr, int low, int middle, int high) {
        int rightStartIndex = middle;
        int leftStartIndex = low;
        int writeIndex = leftStartIndex;
        while (true) {
            if (leftStartIndex >= middle) {
                if (rightStartIndex >= high) {
                    break;
                }
                // 左侧元素已经全部归位，将右侧元素依次加入tempArr
                tempArr[writeIndex] = arr[rightStartIndex];
                rightStartIndex++;
            } else if (rightStartIndex >= high) {
                // 右侧元素元素全部归位，将左侧元素依次加入tempArr
                tempArr[writeIndex] = arr[leftStartIndex];
                leftStartIndex++;
            } else {
                // 两个数组都有元素，比较，加入较小值
                if (arr[leftStartIndex] < arr[rightStartIndex]) {
                    tempArr[writeIndex] = arr[leftStartIndex];
                    leftStartIndex++;
                } else {
                    tempArr[writeIndex] = arr[rightStartIndex];
                    rightStartIndex++;
                }
            }
            writeIndex++;
        }
        // System.out.println(String.format("low:%s high:%s middle:%s", low, high, middle));
        // System.out.println(Arrays.toString(tempArr));
        // System.out.println(Arrays.toString(arr));
        // 将tempArr的low-high的数据，复制到arr
        System.arraycopy(tempArr, low, arr, low, high - low);
        // System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test() {
        Random random = new Random();
        int[] arr = new int[1200];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000);
        }
        // System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
        boolean result = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                result = false;
                break;
            }
        }
        System.out.println("result: " + result);
    }

}
