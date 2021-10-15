package liufeng.algo.sort;

/**
 * 冒泡排序
 * 步骤：从数组第一个元素开始比较，如果左侧元素小于右侧元素，就将2个元素交换；每一次遍历后，多一个元素变得有序，所以需要n-1次遍历
 */
public class BubbleSort extends AbstractSortAlgo  {
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // sorted是一个小优化，可以节省掉最后一段有序子序列的遍历时间
            boolean sorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                int next = j + 1;
                if (arr[j] > arr[next]) {
                    // 如果左侧元素大于右侧元素，交换
                    int temp = arr[j];
                    arr[j] = arr[next];
                    arr[next] = temp;
                    sorted = false;
                    // arr[j]+=arr[next];arr[next]=arr[j]-arr[next];arr[j]-=arr[next]; 可以节省一个int(4byte)的空间
                }
            }
            if (sorted) {
                break;
            }
        }
    }

}
