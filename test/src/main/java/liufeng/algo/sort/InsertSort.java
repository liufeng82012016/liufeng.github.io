package liufeng.algo.sort;

/**
 * 插入排序
 * 从第二个元素开始遍历，与它之前的元素相比较，然后插入到合适的位置
 */
public class InsertSort extends AbstractSortAlgo {
    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    // 向后移动j以及j-i之间的元素，然后将arr[i]写入j
                    int temp = arr[i];
                    int index = i;
                    while (index > j) {
                        arr[index] = arr[index - 1];
                        index--;
                    }
                    // 对应位置写入元素
                    arr[j] = temp;
                }
            }
        }
    }


}
