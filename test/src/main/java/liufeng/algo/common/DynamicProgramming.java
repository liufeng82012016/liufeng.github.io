package liufeng.algo.common;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划学习
 */
public class DynamicProgramming {
    /**
     * 1. 爬梯子
     * 假设有10步楼梯
     * 假设青蛙可以走1步，或者2步
     * 一共多少种走法
     *
     * @param rest 梯子总数
     * @return 可选步骤总数
     */
    public int climb(int rest) {
        return doClimb(rest, new HashMap<>(rest));
    }

    private int doClimb(int rest, Map<Integer, Integer> cacheMap) {
        if (rest < 1) {
            return 0;
        } else if (rest == 1) {
            return 1;
        } else if (rest == 2) {
            return 2;
        } else {
            Integer value = cacheMap.get(rest);
            if (value == null) {
                // 因为青蛙只能走1步或者2步，那么最后一步有2中情况
                value = doClimb(rest - 1, cacheMap) + doClimb(rest - 2, cacheMap);
                // map记录太多了，可以根据情况记录几种就可以了
                cacheMap.put(rest, value);
            }
            return value;
        }
    }

    @Test
    public void testClimb() {
        int i = 10000;
        long startTime = System.currentTimeMillis();
        climb(i);
        System.out.println(String.format("%s : %s", i, (System.currentTimeMillis() - startTime)));
    }

    /**
     * 给定一个数组，求其中最长递增公共子序列长度
     * 最长递增公共子序列：上一个元素小于下一个元素（非连续）
     * 从最后一个元素向前看
     * int length;
     * if(last>pre){
     *
     * }
     *
     * @param arr 数组
     * @return 子序列长度
     */
    public int longestIncrementSequence(int[] arr) {
        return 0;
    }


}
