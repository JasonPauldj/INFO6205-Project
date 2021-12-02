package edu.neu.coe.info6205.sortEssentials;

import com.ibm.icu.util.ULocale;
import edu.neu.coe.info6205.sortEssentials.huskySortUtils.ChineseCoder;
import edu.neu.coe.info6205.sortEssentials.linearithmic.QuickSort_DualPivot;
import edu.neu.coe.info6205.sortEssentials.huskySort.QuickHuskySort;
import edu.neu.coe.info6205.sortEssentials.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.FileUtil;
import edu.neu.coe.info6205.util.Language;

import java.io.File;
import java.io.IOException;
import java.text.CollationKey;
import java.text.Collator;
import java.util.*;
import java.util.function.Consumer;

public class Driver {

    static com.ibm.icu.text.Collator chineseIBMCollator = com.ibm.icu.text.Collator.getInstance(Locale.CHINESE);
    static Collator chineseInBuiltCollator = Collator.getInstance(Locale.CHINESE);
    static ChineseCoder chineseIBMCoder = new ChineseCoder(chineseIBMCollator);
    static ChineseCoder chineseInBuiltCoder = new ChineseCoder(chineseInBuiltCollator);

    public static void main(String[] args) {

        String[] descs = new String[]{"Tim Sort", "Quick Sort Dual-Pivot", "QuickHusky Sort", "LSD String Sort", "MSD String Sort"};
        List<Consumer<String[]>> sortFuncs = new ArrayList<>();

        //QUICK SORT ENGLISH
        benchMarkAnySort("Quick Sort",  (String[] array) -> {
            try {
                QuickSort_DualPivot qs= new QuickSort_DualPivot<String>("Quick Sort", array.length);
                array =(String[])qs.sort(array, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Language.ENGLISH);

        //TIM SORT ENGLISH
        benchMarkAnySort("Tim Sort",  (String[] array) -> {
            try {
                new TimSort<String>().sort(array,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Language.ENGLISH);


        //HUSKY SORT ENGLISH
        benchMarkAnySort("Husky Sort",  (String[] array) -> {
            try {
                QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort",HuskyCoderFactory.englishCoder,(String[] arr) ->{
                    Arrays.sort(arr);
                });
                qhs.sort(array, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Language.ENGLISH);


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
            FileUtil fileUtil = new FileUtil("src/main/RandomString/English/EnglistRandomString1M.txt");
            array = fileUtil.read();
        } else if (i == 2) {
            FileUtil fileUtil = new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
            array = fileUtil.read();
        }
        return array;
    }

    private static String[] getDataToSort(Language lang, int cntOfWords){
        String[] array=null;
        StringBuilder filePath= new StringBuilder("src/main/RandomString/");
        switch(lang){
            case ENGLISH:
                filePath.append("English/");
                filePath.append("randomstrings.txt");
                break;
            case CHINESE:
                filePath.append("Chinese/");
                filePath.append(String.valueOf(cntOfWords));
                break;
        }

        FileUtil fileUtil = new FileUtil("src/main/RandomString/English/randomstrings.txt");
        array = fileUtil.read(cntOfWords);
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
                new QuickSort_DualPivot<String>(arr.length).sort(arr, false);
        });
        classList.add((String[] arr) -> {
            try {
                QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort",chineseIBMCoder,(String[] array) ->{
                    Arrays.sort(arr, chineseIBMCollator);
                });
                qhs.sort(arr, true);
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

    public static void benchMarkAnySort(String desc, Consumer<String[]> sortFunction, Language lang) {

        Benchmark<String[]> bm = new Benchmark<String[]>(desc, sortFunction);

        String csvLine = desc + ",";
        int[] cntOfWords = new int[]{10000, 20000, 40000, 80000};
        for (int arraySize: cntOfWords) {
            String[] array = getDataToSort(lang, arraySize);
            double avgTime = bm.run(array, 100);
            System.out.println(desc + " took " + avgTime + " time to sort " + arraySize + " strings");
        }
        System.out.println();
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
                        new TimSort<String>().sort(array,true, chineseIBMCollator);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        String[] array = getDataToSort(2);

        double d = bm.run(array,10);
        System.out.println(d);
//        FileUtil fileUtil = new FileUtil("src/main/RandomString/Chinese/sortedChinese.txt");
//        String[] in = fileUtil.read();
//        System.out.println(Arrays.equals(array,in));
    }

    public static void benchmarkHuskySort() {
        Benchmark<String[]> bm = new Benchmark<String[]>("benchmarking tim sort",
                (String[] array) -> {
                    try {
                        QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort",chineseIBMCoder,(String[] arr) ->{
                            Arrays.sort(arr, chineseIBMCollator);
                        });
                        qhs.sort(array, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        String[] array = getDataToSort(2);

        double d = bm.run(array,10);
        System.out.println(d);
    }

}
