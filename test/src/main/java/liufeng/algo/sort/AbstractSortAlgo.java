package liufeng.algo.sort;

import java.util.*;

public abstract class AbstractSortAlgo implements SortAlgo {

    @Override
    public boolean checkSortedResult() {
        return checkSortedResult(10000);
    }

    protected boolean checkSortedResult(int length) {
        Random random = new Random();
        int[] arr = new int[length];
        // map 计数器，保证元素和出现的次数在排序前后相同
        Map<Integer, Integer> beforeElements = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10000000);
            // 计数递增
            Integer count = beforeElements.computeIfAbsent(arr[i], k -> 0);
            beforeElements.put(arr[i], count + 1);
        }
        sort(arr);
        // 计数递减
        for (int j : arr) {
            Integer count = beforeElements.get(j);
            if (count == null) {
                throw new RuntimeException(this.getClass().getSimpleName() + " result: false");
            }
            beforeElements.put(j, count - 1);

        }
        // 判断是否归0
        for (Integer count : beforeElements.values()) {
            if (count != 0) {
                throw new RuntimeException(this.getClass().getSimpleName() + " result: false");
            }
        }
        return checkSortedResult(arr);
    }

    @Override
    public boolean checkSortedResult(int[] arr) {
        boolean result = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                result = false;
                break;
            }
        }
        System.out.println(this.getClass().getSimpleName() + " result: " + result);
        return result;
    }

}
