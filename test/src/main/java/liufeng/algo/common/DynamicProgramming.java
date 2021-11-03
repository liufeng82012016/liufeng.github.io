package liufeng.algo.common;

import org.junit.Test;

import java.util.*;

/**
 * 动态规划学习
 */
public class DynamicProgramming {
    /**
     * 1. 爬梯子
     * 假设有10步楼梯
     * 假设青蛙可以走1步,或者2步
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
                // 因为青蛙只能走1步或者2步,那么最后一步有2中情况
                value = doClimb(rest - 1, cacheMap) + doClimb(rest - 2, cacheMap);
                // map记录太多了,可以根据情况记录几种就可以了
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
     * 简称 LIS
     * 给定一个数组,求其中最长递增公共子序列长度
     * 最长递增子序列：上一个元素小于下一个元素（非连续）
     * 关键点：因为要求子序列升序，所以同一位置的值越小，其后续位置值增加的可能性变大
     * nlogn实现
     *
     * @param arr 数组
     * @return 子序列长度
     */
    public int longestIncrementSequence(int[] arr) {
        int[] temp = new int[arr.length];
        int tempIndex = 0;
        for (int j : arr) {
            // 没有元素，或者新元素大于数组最大值时，2种情况行为一致，所以这里将他们合并到一个判断
            if (tempIndex == 0 || j > temp[tempIndex - 1]) {
                temp[tempIndex] = j;
                tempIndex++;
            } else {
                // 二分查找,替换
                int insertIndex = binarySearch(temp, 0, tempIndex, j);
                temp[insertIndex] = j;
            }
        }
        return tempIndex;
    }

    /**
     * 2分查找
     * 查找大于等于value的最小index
     */
    private int binarySearch(int[] arr, int minIndex, int maxIndex, int value) {
        if (maxIndex == minIndex) {
            return minIndex;
        }
        int middleIndex = (minIndex + maxIndex) / 2;
        if (arr[middleIndex] < value) {
            return binarySearch(arr, middleIndex + 1, maxIndex, value);
        } else if (arr[middleIndex] == value) {
            return middleIndex;
        } else {
            return binarySearch(arr, minIndex, middleIndex, value);
        }
    }

    /**
     * LIS：动态规划实现
     * n平方
     * 思路：假设元素下标为i
     * 有2种情况：
     * 1. 序列包含当前元素（包含时，子序列长度加1）
     * 2. 序列不包含当前元素
     * 什么时候不包含？
     * 1. 当前元素arr[i]<arr[j]
     * 2. 下标j的最长子序列不包含元素arr[j]
     */
    public int longestIncrementSequenceDP(int[] arr) {
        int[] len = new int[arr.length];
        Arrays.fill(len, 1);
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // 不用else，因为数组元素用1填充，已经兜底了
                if (arr[i] > arr[j]) {
                    // 如果当前元素比上一个元素大， 可以加到序列末尾
                    len[i] = Math.max(len[i], len[j] + 1);
                }
            }
        }
        int result = 0;
        for (int j : len) {
            // 遍历比较最大值
            result = Math.max(result, j);
        }
        return result;
    }

    @Test
    public void longestIncrementSequence() {
        int[] arr = new int[]{4, 8, 9, 5, 6, 7, 2, 7};
        int i = longestIncrementSequence(arr);
        System.out.println("i:" + i);
        i = longestIncrementSequenceDP(arr);
        System.out.println("i:" + i);
    }

    /**
     * LCS 最长公共子序列
     */
    public int longestCommonSequence(String s1, String s2) {
        char[] s1Chars = s1.toCharArray();
        Map<Character, List<Integer>> charIndexMap = new HashMap<>();
        for (int i = 0; i < s1Chars.length; i++) {
            List<Integer> charIndexList = charIndexMap.computeIfAbsent(s1Chars[i], k -> new LinkedList<>());
            charIndexList.add(i);
        }
        List<Integer> charIndexes = new LinkedList<>();
        for (int i = 0; i < s2.length(); i++) {
            List<Integer> charIndexList = charIndexMap.get(s2.charAt(i));
            if (charIndexList != null) {
                charIndexes.addAll(charIndexList);
            }
        }
        int[] indexArr = new int[charIndexes.size()];
        int addIndex = 0;
        for (Integer index : charIndexes) {
            indexArr[addIndex] = index;
        }
        return longestIncrementSequence(indexArr);
    }

    public void longestCommonSequenceDP(String s1, String s2) {
        // 待实现
        return;
    }


}
