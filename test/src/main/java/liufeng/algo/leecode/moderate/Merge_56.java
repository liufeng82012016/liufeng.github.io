package liufeng.algo.leecode.moderate;

import org.junit.Test;

import java.util.Arrays;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 解法：以第一个元素升序，从前到后遍历合并
 */
public class Merge_56 {

    @Test
    public void convertInputs() {
        String input = "[[1,4],[0,4]]";
        System.out.println(input
                .replaceAll("\\[", "{")
                .replaceAll("]", "}"));
    }


    private void print(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i][0] + "\t" + ints[i][1]);
        }
    }

    @Test
    public void merge() {
        int[][] intervals = {{1, 4}, {0, 4}};
        print(intervals);
        print(merge(intervals));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(o1,o2)->{
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        int pre = 0, next = 1;
        int count = intervals.length;
        for (; next < intervals.length; next++) {
            if (intervals[pre][1] >= intervals[next][0]) {
                intervals[pre][1] = Math.max(intervals[pre][1], intervals[next][1]);
                count--;
                intervals[next][0] = -1;
            } else {
                pre = next;
            }
        }
        int[][] result = new int[count][2];
        int index = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] != -1) {
                result[index][0] = intervals[i][0];
                result[index][1] = intervals[i][1];
                index++;
            }
        }
        return result;
    }
}
