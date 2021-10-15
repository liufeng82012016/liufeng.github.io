package liufeng.algo.sort;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 基数排序
 */
public class RadixSort extends AbstractSortAlgo {
    @Override
    public void sort(int[] arr) {
        // 先进先出，这里使用队列
        Map<Integer, List<Integer>> containers = new HashMap<>();
        int base = 1;
        while (true) {
            boolean end = true;
            // 循环n次
            for (int j : arr) {
                // 计算落到那个基数里面
                int hash = hash(j, base);
                if (hash > 0 && end) {
                    end = false;
                }
                List<Integer> container = containers.computeIfAbsent(hash, k -> new LinkedList<>());
                container.add(j);
            }
            // 循环n次
            int index = 0;
            for (List<Integer> list : containers.values()) {
                for (Integer value : list) {
                    arr[index] = value;
                    index++;
                }
                list.clear();
            }
            if (end) {
                break;
            }
            // 每次循环向前移动一位，个位/十位/百位 这个顺序
            base *= 10;
        }
    }

    private int hash(int value, int base) {
        if (value < base) {
            return 0;
        }
        return value / base % 10;
    }
}
