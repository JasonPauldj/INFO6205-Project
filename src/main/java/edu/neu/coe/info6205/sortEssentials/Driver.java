package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.sortEssentials.linearithmic.QuickSort_DualPivot;
import edu.neu.coe.info6205.sortWithOutConfig.TimSort;
import edu.neu.coe.info6205.sortWithOutConfig.huskySort.QuickHuskySort;
import edu.neu.coe.info6205.sortWithOutConfig.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.FileUtil;

import java.io.IOException;
import java.util.Random;
import java.util.function.Consumer;

public class Driver {
    public static void main(String[] args) {


//        benchMarkAnySort("QuickSort", (array) -> {
//            new QuickSort_DualPivot<String>("Quick Sort",array.length).sort(array,0, array.length, 0);
//        });
//        benchMarkAnySort("TimSort", (String[] array) -> {
//            try {
//                new TimSort<String>().sort(array, 0, array.length);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//
//        benchMarkAnySort("Husky Sort", (String[] array) -> {
//            try {
//                QuickHuskySort qhs= new QuickHuskySort<String>(HuskyCoderFactory.englishCoder);
//                qhs.preSort(array,false);
//                qhs.sort(array,0,array.length);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        benchMarkAnySort("LSD Radix Sort", (String[] array) -> {
//            new LSDStringSort().sort(array);
//        });

        benchMarkAnySort("MSD Radix Sort", (String[] array) -> {
            new MSDStringSort().sort(array);
        });
    }

    public static  void benchMarkAnySort(String desc, Consumer<String []> sortFunction){
        //src/main/RandomString/English/EnglishRandomNames100.txt
        FileUtil fileUtil = new FileUtil("src/main/RandomString/English/EnglishRandomNames100.txt");
        String[] array = fileUtil.read();
        Benchmark<String []> bm = new Benchmark<String []>(desc, sortFunction);
//        String[] array = new String[25];
//        Random random = new Random();
//
//        for(int j=0;j<array.length;j++){
//            array[j] =String.valueOf(random.nextInt(100));
//        }
        System.out.println( bm.run(array,100));
        for (String st:
            array ) {
            System.out.println(st);
        }

        System.out.println();
        System.out.println();
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

    public static void benchmarkHuskySort() {
        Benchmark<String[]> bm = new Benchmark<String[]>("benchmarking tim sort",
                (String[] array) -> {
                    try {
                        QuickHuskySort qhs= new QuickHuskySort<String>(HuskyCoderFactory.englishCoder);
                        qhs.preSort(array,false);
                        qhs.sort(array,0,array.length);
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
