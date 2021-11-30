package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.sortEssentials.huskySort.PureHuskySort;
import edu.neu.coe.info6205.sortEssentials.linearithmic.QuickSort_DualPivot;
import edu.neu.coe.info6205.sortWithOutConfig.TimSort;
import edu.neu.coe.info6205.sortWithOutConfig.huskySort.QuickHuskySort;
import edu.neu.coe.info6205.sortWithOutConfig.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class Driver {

    public static void main(String[] args) {

        String[] descs = new String[]{"Time Sort", "Quick Sort Dual-Pivot", "QuickHusky Sort" , "LSD String Sort" , "MSD String Sort"};
        List<Consumer<String[]>> classList = new ArrayList<>();
        populateClassList(classList);
        String[] array = getDataToSort();
        List<String> result = new ArrayList<String>();
        for(int i = 0 ; i < descs.length ; i++ ){
            int pos = i;
            result.add(benchMarkAnySort(descs[i], array,classList.get(i)));
        }
        writeDataToSort(result);
    }

    private static void writeDataToSort(List<String> lines) {
        FileUtil fileUtil = new FileUtil("src/main/RandomString/English/EnglishResults.txt");
        fileUtil.writeAsCsv(lines);
    }
    private static String[] getDataToSort() {
        FileUtil fileUtil = new FileUtil("src/main/RandomString/English/EnglishRandomNames100.txt");
        String[] array = fileUtil.read();
        return array;
    }

    private static void populateClassList(List<Consumer<String[]>> classList) {
        classList.add((String[] arr) -> {
            try {
                new TimSort<String>().sort(arr, 0, arr.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        classList.add((String[] arr) -> {
            new QuickSort_DualPivot<String>(arr.length).sort(arr,0,arr.length,0);
        });
        classList.add((String[] arr) -> {
            try {
                QuickHuskySort qhs= new QuickHuskySort<String>(HuskyCoderFactory.englishCoder);
                qhs.preSort(arr,false);
                qhs.sort(arr,0,arr.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        classList.add((String[] arr) -> {
            new LSDStringSort().sort(arr);
        });
        classList.add((String[] arr) -> {
            new MSDStringSort().sort(arr);
        });
    }

    public static  String benchMarkAnySort(String desc, String[] array, Consumer<String[]> sortFunction){


        Benchmark<String []> bm = new Benchmark<String []>(desc, sortFunction);
        String csvLine = desc + ",";
        int[] attempts = new int[]{100000,200000,300000,400000};
        for(int trials : attempts){
            csvLine+= bm.run(array,trials) + ",";
        }
        System.out.println(csvLine);
        System.out.println();
        return csvLine;
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
