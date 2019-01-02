package main.bucketsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 桶排序
 * ArrayList为存放桶的容器,LinkedList为桶
 * 元素分配到桶的映射规则(arr[i] - minValue) * (bucketCount - 1) / d
 * d=maxValue-minValue
 */
public class Bucket {

    public Double[] sort(Double[] sourceArray) {
        Double[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        return bucketsort(arr);
    }

    private Double[] bucketsort(Double[] arr) {
        if (arr.length == 0)
            return arr;

        int bucketCount = arr.length;//桶的个数

        //获取数组中的最大值和最小值
        double minValue = arr[0];
        double maxValue = arr[0];
        for (double value : arr) {
            if (value < minValue)
                minValue = value;
            else if (value > maxValue)
                maxValue = value;
        }

        double d = maxValue - minValue;
        //初始化桶,ArrrayList为装桶的容器，LinkedList为桶
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<LinkedList<Double>>(bucketCount);

        for (int i = 0; i < bucketCount; i++) {

            bucketList.add(new LinkedList<Double>());

        }


        //遍历原始数组，将每个元素放入桶中
        //利用映射函数将数据分配到各个桶中
        for (int i = 0; i < arr.length; i++) {
            int num = (int) ((arr[i] - minValue) * (bucketCount - 1) / d);
            bucketList.get(num).add(arr[i]);
        }

        for (int i = 0; i < bucketList.size(); i++) {

            //JDK底层采用了归并排序或归并的优化版本
            Collections.sort(bucketList.get(i));
        }

        Double[] sortedArray = new Double[arr.length];
        int index = 0;
        for (LinkedList<Double> list : bucketList) {
            for (double element : list) {
                sortedArray[index++] = element;
            }
        }

        return sortedArray;
    }


}
