package main.bucketsort;

import javax.sound.midi.Soundbank;

public class BucketMain {

    public static void main(String[] args) {

        Bucket bucket=new Bucket();

        Double[] arr=new Double[]{0.84,0.01,1.75,5.47,10.1,20.55,3.04};

        Double[] sorted=bucket.sort(arr);

        System.out.println(sorted.toString());
    }
}
