package edu.neu.coe.info6205.sortEssentials;

import com.ibm.icu.util.ULocale;
import edu.neu.coe.info6205.sortEssentials.linearithmic.QuickSort_DualPivot;
import edu.neu.coe.info6205.sortEssentials.huskySort.QuickHuskySort;
import edu.neu.coe.info6205.sortEssentials.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.FileUtil;

import java.io.IOException;
import java.text.CollationKey;
import java.text.Collator;
import java.util.*;
import java.util.function.Consumer;

public class Driver {

    public static void main(String[] args) {

        String[] descs = new String[]{"Tim Sort", "Quick Sort Dual-Pivot", "QuickHusky Sort", "LSD String Sort", "MSD String Sort"};
        List<Consumer<String[]>> classList = new ArrayList<>();
//        benchmarkTimSort();
        //benchmarkQuickSort();

        Collator c = Collator.getInstance(Locale.ENGLISH);
        CollationKey ck1 = c.getCollationKey("洪文胜");
        CollationKey ck2 = c.getCollationKey("樊辉辉");
        byte[] ck1byte = ck1.toByteArray();
        byte[] ck2byte = ck2.toByteArray();
        System.out.println(new String(ck1.toByteArray()));
        System.out.println(new String(ck2.toByteArray()));
        ck1.compareTo(ck2);
        int a=10;


//        if (args[0].equals("1")) {
//            populateClassList(classList,null);
//            String[] array = getDataToSort(1);
//            List<String> result = new ArrayList<String>();
//            for (int i = 0; i < descs.length; i++) {
//                int pos = i;
//                result.add(benchMarkAnySort(descs[i], array, classList.get(i)));
//            }
//            writeDataToSort(result, 1);
//        }
//        if (args[0].equals("2") || args[1].equals("2")) {
//            populateClassList(classList,Collator.getInstance(Locale.CHINA));
//            String[] array = getDataToSort(2);
//            List<String> result = new ArrayList<String>();
//            for (int i = 0; i < descs.length; i++) {
//                int pos = i;
//                result.add(benchMarkAnySort(descs[i], array, classList.get(i)));
//            }
//            writeDataToSort(result, 2);
//        }
    }

    private static void writeDataToSort(List<String> lines, int i) {
        if (i == 1) {
            FileUtil fileUtil = new FileUtil("src/main/RandomString/English/EnglishResults.txt");
            fileUtil.writeAsCsv(lines);
        } else if (i == 2) {
            FileUtil fileUtil = new FileUtil("src/main/RandomString/Chinese/newSortedChinese.txt");
            fileUtil.writeAsCsv(lines);
        }
    }

    private static String[] getDataToSort(int i) {
        String[] array = null;
        if (i == 1) {
            FileUtil fileUtil = new FileUtil("src/main/RandomString/English/EnglishRandomNames100.txt");
            array = fileUtil.read();
        } else if (i == 2) {
            FileUtil fileUtil = new FileUtil("src/main/RandomString/Chinese/shuffledChinese30.txt");
            array = fileUtil.read();
        }
        return array;
    }

    private static void populateClassList(List<Consumer<String[]>> classList, Collator cl) {
        classList.add((String[] arr) -> {
            try {
                if (cl == null)
                    new TimSort<String>().sort(arr, 0, arr.length);
                else
                    new TimSort<String>().sortBuiltInCollator(arr, 0, arr.length, cl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        classList.add((String[] arr) -> {
            if (cl != null)
                new QuickSort_DualPivot<String>(arr.length).sort(arr,false,cl);
            else
                new QuickSort_DualPivot<String>(arr.length).sort(arr, 0, arr.length, 0);
        });
        classList.add((String[] arr) -> {
            try {
                QuickHuskySort qhs = new QuickHuskySort<String>(HuskyCoderFactory.englishCoder);
                qhs.preSort(arr, false);
                qhs.sort(arr, 0, arr.length);
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

    public static String benchMarkAnySort(String desc, String[] array, Consumer<String[]> sortFunction) {


        Benchmark<String[]> bm = new Benchmark<String[]>(desc, sortFunction);
        String csvLine = desc + ",";
        int[] attempts = new int[]{100000, 200000, 300000, 400000};
        for (int trials : attempts) {
            csvLine += bm.run(array, trials) + ",";
        }
        System.out.println(csvLine);
        System.out.println();
        return csvLine;
    }

    public static void benchmarkQuickSort() {
        Benchmark<String[]> bm = new Benchmark<String[]>("benchmarking quick sort",
                (String[] array) -> {
                    try {
                       QuickSort_DualPivot qs= new QuickSort_DualPivot<String>("Quick Sort", array.length);
                        array =(String[])qs.sort(array, true, com.ibm.icu.text.Collator.getInstance(Locale.CHINA));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        String[] array = getDataToSort(2);

        double d = bm.run(array,1);
        System.out.println(d);
        FileUtil fileUtil = new FileUtil("src/main/RandomString/Chinese/sortedChinese.txt");
        String[] in = fileUtil.read();
//        String[] res = qs.sort()
        System.out.println(Arrays.equals(array,in));

    }

    public static void benchmarkTimSort() {
        Benchmark<String[]> bm = new Benchmark<String[]>("benchmarking tim sort",
                (String[] array) -> {
                    try {
                        new TimSort<String>().sortBuiltInCollator(array, 0, array.length, Collator.getInstance(Locale.CHINA));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        String[] array = getDataToSort(2);

        double d = bm.run(array,100);
        System.out.println(d);
        FileUtil fileUtil = new FileUtil("src/main/RandomString/Chinese/sortedChinese.txt");
        String[] in = fileUtil.read();
        System.out.println(Arrays.equals(array,in));
    }

    public static void benchmarkHuskySort() {
        Benchmark<String[]> bm = new Benchmark<String[]>("benchmarking tim sort",
                (String[] array) -> {
                    try {
                        QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort",HuskyCoderFactory.chineseCoder,(String[] arr) ->{
                            Arrays.sort(arr,  Collator.getInstance(Locale.CHINA));
                        });
                        qhs.preSort(array, false);
                        qhs.sort(array, 0, array.length);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        String[] array = getDataToSort(2);

        double d = bm.run(array,1);
        System.out.println(d);
    }

}
