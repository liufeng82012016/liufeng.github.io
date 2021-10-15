package liufeng.algo.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SortTest {
    @Test
    public void test() {
        int length = 80000;
        Random random = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10000000);
        }
        SortAlgo[] algos = new SortAlgo[]{
                new BubbleSort(), new SelectSort(), new QuickSort(), new MergeSort()
        };
        Arrays.stream(algos).forEach(algo -> {
            int[] copyArr = new int[length];
            System.arraycopy(arr, 0, copyArr, 0, length);
//            System.out.println(Arrays.toString(copyArr));
            long startTime = System.currentTimeMillis();
            algo.sort(copyArr);
//            System.out.println(Arrays.toString(copyArr));
            System.out.println(algo.getClass().getSimpleName() + " : " + (System.currentTimeMillis() - startTime));
        });
    }
}
