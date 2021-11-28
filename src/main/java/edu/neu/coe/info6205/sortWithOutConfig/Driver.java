package edu.neu.coe.info6205.sortWithOutConfig;

import edu.neu.coe.info6205.util.Benchmark;

import java.io.IOException;
import java.util.Random;

public class Driver {
    public static void main(String[] args) {
        benchmarkQuickSort();
        benchmarkTimSort();
    }

    public static void benchmarkQuickSort(){
        Benchmark<String[]> bm = new Benchmark<String[]>("benchmarking quick sort",
                (String[] array) -> {
                    try {
                        new QuickSort_DualPivot<String>("Quick Sort",array.length).sort(array,0, array.length, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        String[] array = new String[1000];
        Random random = new Random();

        for(int j=0;j<array.length;j++){
            array[j] =String.valueOf(random.nextInt());
        }
        System.out.println( bm.run(array,100));
    }

    public static void benchmarkTimSort() {
        Benchmark<String[]> bm = new Benchmark<String[]>("benchmarking tim sort",
                (String[] array) -> {
                    try {
                        new TimSort<String>().sort(array, 0, array.length);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        String[] array = new String[1000];
        Random random = new Random();

        for (int j = 0; j < array.length; j++) {
            array[j] = String.valueOf(random.nextInt());
        }
        System.out.println(bm.run(array, 100));
    }

}
