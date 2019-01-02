package main.countingsort;

/**
 * 计数排序，桶排序的一种特例
 *
 * 时间复杂度:O(n+m)
 * n为原始数组长度,m为桶长度
 *
 *
 * 什么时候最快:
 * 适用于一定范围的整数排序。在取值范围不是很大的情况下，性能甚至快过O(n log n)
 * 的排序算法
 */
public class Sort {

    public static Integer[] countingSort(Integer[] arr) {
        if (arr.length <= 1)
            return arr;

        int maxValue = getMaxValue(arr);
        maxValue=maxValue+1;
        int[] bucket = new int[maxValue];
        for (int value : arr) {
            bucket[value]++;
        }

        Integer[] sorted = new Integer[arr.length];
        int sortedIndex = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                sorted[sortedIndex++] = i;
                bucket[i]--;
            }
        }
        return sorted;
    }

    private static int getMaxValue(Integer[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }
}
