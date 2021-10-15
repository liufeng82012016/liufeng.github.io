package liufeng.algo.sort;

import org.junit.Test;

/**
 * 希尔排序
 */
public class ShellSort extends AbstractSortAlgo {
    @Override
    public void sort(int[] arr) {
        // 初始化间隔为数组长度的一半 -- 插入排序是每次遍历减1
        int initInterval = arr.length / 2;
        while (initInterval > 0) {
            // 遍历
            for (int i = 0; i < initInterval; i++) {
                for (int j = initInterval; j < arr.length; j++) {
                    int lastEleIndex = j - initInterval;
                    if (arr[j] < arr[lastEleIndex]) {
                        // 右侧小于左侧，需要插入
                        int temp = arr[j];
                        int moveIndex = lastEleIndex;
                        boolean less0 = false;
                        while (temp < arr[moveIndex]) {
                            // 元素移动
                            arr[moveIndex + initInterval] = arr[moveIndex];
                            if (moveIndex >= initInterval) {
                                moveIndex -= initInterval;
                            } else {
                                less0 = true;
                                break;
                            }
                        }
                        // while循环结束有2中情况，1-当前元素小于该组最小元素，排最左；2-当前元素剧中，因为循环index减去了一个间隔，这里需要补偿
                        if (!less0) {
                            moveIndex += initInterval;
                        }
                        // 移动完成，插入
                        arr[moveIndex] = temp;
                    }
                }
            }
            // 增量减小
            initInterval /= 2;
        }
    }

    @Test
    public void t() {
        for (int i = 0; i < 10; i++) {
            checkSortedResult(1000);
        }
    }
}
