package main.mergesort;

public class MergeMain {

    public static void main(String[] args) {
        MergeSort mergesort=new MergeSort();
        Integer[] arr=new Integer[]{20,1,50,60,13,90,2,5,55};

        Integer[] result=mergesort.sort(arr);
        System.out.println(result);
    }

}
