package liufeng.algo.leecode.difficult;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 接雨水
 */
public class Trap_42 {
    @Test
    public void trap() {
        int[] height = {4, 9, 4, 5, 3, 2};
//        int[] height = {4, 2, 0, 3, 2, 5};
        System.out.println(trap(height));
    }

    public int trap(int[] height) {
        int maxHeight = 0, occupy = 0;
        // 遍历，获取最大值
        for (int i = 0; i < height.length; i++) {
            if (height[i] > maxHeight) {
                maxHeight = height[i];
            }
            if (height[i] > 0) {
                // 计算夯实的空间
                occupy += height[i];
            }
        }
        // 遍历，获取升序列表和降序列表
        LinkedList<Pair> upList = new LinkedList<>();
        for (int i = 0; i < height.length; i++) {
            int latestHeight = upList.isEmpty() ? 0 : upList.getLast().value;
            if (latestHeight < height[i] && height[i] <= maxHeight) {
                upList.add(new Pair(height[i], i));
            }
            // 上升列表达到顶点
            if (height[i] == maxHeight) {
                break;
            }
        }
        // 降序列表，从结尾开始升序
        LinkedList<Pair> downList = new LinkedList<>();
        for (int i = height.length - 1; i >= 0; i--) {
            int latestHeight = downList.isEmpty() ? 0 : downList.getLast().value;
            if (latestHeight < height[i] && height[i] <= maxHeight) {
                downList.add( new Pair(height[i], height.length - i - 1));
            }
            // 上升列表达到顶点
            if (height[i] == maxHeight) {
                break;
            }
        }
        int maxArea = maxHeight * height.length;
        // 计算左右两侧空白
        int upListEmpty = computeEmptyAreaUp(upList);
        int downListEmpty = computeEmptyAreaUp(downList);
        // System.out.println(maxArea + " " + occupy + " " + upListEmpty + " " + downListEmpty);
        return maxArea - occupy - upListEmpty - downListEmpty;
    }

    private int computeEmptyAreaUp(LinkedList<Pair> list) {
        // System.out.println(list);
        int emptyArea = 0;
        Pair latest = new Pair(0, 0);
        for (Pair pair : list) {
            if (pair.index > 0) {
                emptyArea += pair.index * (pair.value - latest.value);
            }
            latest = pair;
        }
        return emptyArea;
    }


    class Pair {
        int value;
        int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "value=" + value +
                    ", index=" + index +
                    '}';
        }
    }
}
