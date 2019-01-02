package main.mergesort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public Integer[] sort(Integer[] sourceArray) {
        Integer[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        if (arr.length < 2)
            return arr;

        int middle = (int) Math.floor(arr.length / 2);

        Integer[] left = Arrays.copyOfRange(arr, 0, middle);
        Integer[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort(left), sort(right));
    }

    private Integer[] merge(Integer[] left, Integer[] right) {
        Integer[] result = new Integer[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }
        return result;
    }
}
