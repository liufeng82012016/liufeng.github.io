package liufeng.algo.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SortTest {
    @Test
    public void test() {
        int length = 10000;
        Random random = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10000000);
        }
        // 忽略计数排序，它的时间复杂度低，但是对使用场景要求比较苛刻
        SortAlgo[] algos = new SortAlgo[]{
                new BubbleSort(), new SelectSort(), new QuickSort(), new MergeSort(), new InsertSort(),
                new BucketSort(), new RadixSort(), new ShellSort()
        };
        Arrays.stream(algos).forEach(algo -> {
            int[] copyArr = new int[length];
            System.arraycopy(arr, 0, copyArr, 0, length);
            long startTime = System.currentTimeMillis();
            algo.sort(copyArr);
            System.out.println(algo.getClass().getSimpleName() + " : " + (System.currentTimeMillis() - startTime));
            boolean checkResult = algo.checkSortedResult(copyArr);
            if (!checkResult) {
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(copyArr));
            }
        });
    }
}
