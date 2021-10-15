package liufeng.algo.sort;

/**
 * 计数排序
 * 对于arr数值稀疏，且max值很大的数组，空间浪费太大了。。
 * 不适合有负数的排序
 */
public class CountSort extends AbstractSortAlgo {
    @Override
    public void sort(int[] arr) {
        // 找出最大值 -- 优化：找出最大最小值，用差作为缓存数组的长度
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        // 创建一个max+1长度的缓存数组，将原数组的元素加入到缓存数组，原数组的值就是新数组的下标
        int[] tempArr = new int[max + 1];
        for (int value : arr) {
            tempArr[value] = tempArr[value] + 1;
        }
        // 将缓存数组的值依次填入新数组
        int index = 0;
        for (int i = 0; i < tempArr.length; i++) {
            int value = tempArr[i];
            while (value > 0) {
                arr[index] = i;
                index++;
                value--;
            }
        }
    }


}
