package main.countingsort;

public class Main {

    public static void main(String[] args) {

        Integer[] arr=new Integer[]{1,20,3,12,40,90,22,44,92,50};
        Integer[] sorted=Sort.countingSort(arr);
        System.out.println(sorted);
    }
}
