package liufeng.algo.sort;

import org.junit.Test;

import java.util.*;

/**
 * 桶排序
 * 感觉思路属于分治
 */
public class BucketSort extends AbstractSortAlgo {
    @Override
    public void sort(int[] arr) {
        Map<Integer, List<Integer>> container = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            // 分桶策略要根据不同场景来设定，理想情况是各个桶数量分布均匀。。。
            int value = arr[i] / 100;
            List<Integer> list = container.computeIfAbsent(value, k -> new LinkedList<>());
            list.add(arr[i]);
        }
        // 分别排序 -- 这里其实可以指定排序算法，使用api是一种偷懒行为。。
        container.values().forEach(Collections::sort);
        ArrayList<Integer> keysSorted = new ArrayList<>(container.keySet());
        Collections.sort(keysSorted);
        int index = 0;
        // 合并结果
        for (Integer key : keysSorted) {
            for (Integer value : container.get(key)) {
                arr[index] = value;
                index++;
            }
        }
    }

    @Test
    public void t() {
        for (int i = 0; i < 1; i++) {
            checkSortedResult(100000);
        }
    }
}
