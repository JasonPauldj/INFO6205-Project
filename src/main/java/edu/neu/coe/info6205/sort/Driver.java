package edu.neu.coe.info6205.sort;

import com.sun.xml.internal.rngom.parse.host.Base;
import edu.neu.coe.info6205.sort.QuickSort_DualPivot;
import edu.neu.coe.info6205.sortWithOutConfig.TimSort;
import edu.neu.coe.info6205.util.Benchmark;

import java.util.Random;

public class Driver {
    public static void main(String[] args){
        benchMarkTimSort();

    }

    public static void benchMarkQuickSortDualPivot(){
        Benchmark<String[]> bm_quicksort = new Benchmark<String[]>("benchmarking insertion sort",
                (String[] array) -> {
                    try {
                        new QuickSort_DualPivot<String>(new BaseHelper<>("base helper",null)).sort(array,0, array.length, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        String[] array = new String[1000];
        Random random = new Random();

        for(int j=0;j<array.length;j++){
            array[j] =String.valueOf(random.nextInt());
        }
        System.out.println( bm_quicksort.run(array,100));
    }

    public static void benchMarkTimSort(){
        Benchmark<String[]> bm_timsort= new Benchmark<String[]>("benchmarking insertion sort",
                (String[] array) -> {
                    try {
                        new TimSort<String>().sort(array,0, array.length);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        String[] array = new String[1000];
        Random random = new Random();

        for(int j=0;j<array.length;j++){
            array[j] =String.valueOf(random.nextInt());
        }
        System.out.println( bm_timsort.run(array,100));
    }
}
