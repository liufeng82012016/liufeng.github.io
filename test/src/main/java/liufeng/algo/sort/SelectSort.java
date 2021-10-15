package liufeng.algo.sort;

/**
 * 选择排序
 * 步骤：遍历数组，选出最大的元素，将该元素放置到数组最后面；每一次遍历，数组尾部多一个元素加入有序序列，无序序列长度减0；最后为0
 */
public class SelectSort extends AbstractSortAlgo {
    @Override
    public void sort(int[] arr) {
        // 计数，每次循环挑选出一个最大值。所以需要循环n-1次;第n-1次只有2个数，交换后整个序列有序
        for (int i = 0; i < arr.length - 1; i++) {
            // 首先假设最大值是数组的第0个元素，依次往后比较
            int max = arr[0];
            int index = 0;
            // 1次
            // 从第一个元素开始循环，如果i=arr.length-1; j=1;j<i,循环不执行
            for (int j = 1; j < arr.length - i; j++) {
                // 比较，选出较大值
                if (max < arr[j]) {
                    max = arr[j];
                    index = j;
                }
            }
            // 交换
            arr[index] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = max;
        }
    }


}
