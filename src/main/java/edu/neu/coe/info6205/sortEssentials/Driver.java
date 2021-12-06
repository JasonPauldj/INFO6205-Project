package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.sortEssentials.huskySort.PureHuskySort;
import edu.neu.coe.info6205.sortEssentials.huskySortUtils.ChineseCoder;
import edu.neu.coe.info6205.sortEssentials.linearithmic.QuickSort_DualPivot;
import edu.neu.coe.info6205.sortEssentials.huskySort.QuickHuskySort;
import edu.neu.coe.info6205.sortEssentials.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.FileUtil;
import edu.neu.coe.info6205.util.Language;

import java.io.IOException;
import java.text.Collator;
import java.util.*;
import java.util.function.Consumer;

public class Driver {

    static com.ibm.icu.text.Collator chineseIBMCollator = com.ibm.icu.text.Collator.getInstance(Locale.CHINESE);
    static Collator chineseInBuiltCollator = Collator.getInstance(Locale.CHINESE);
    static ChineseCoder chineseIBMCoder = new ChineseCoder(chineseIBMCollator);
    static ChineseCoder chineseInBuiltCoder = new ChineseCoder(chineseInBuiltCollator);

    public static void main(String[] args) {
       benchMarkEnglish();
       benchMarkChinese();
    }
    /**
     * This method currently runs the benchmark for sorting methods on english strings.
     */
    private static void benchMarkEnglish(){
        //QUICK SORT ENGLISH
        benchMarkAnySort("Quick Sort Dual Pivot", (String[] array) -> {

            QuickSort_DualPivot qs = new QuickSort_DualPivot<String>("Quick Sort", array.length);
            qs.sort(array, true);

        }, Language.ENGLISH);

        //TIM SORT ENGLISH
        benchMarkAnySort("Tim Sort", (String[] array) -> {

            try {
                new TimSort<String>().sort(array, true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }, Language.ENGLISH);

        //QUICK HUSKY SORT ENGLISH
        benchMarkAnySort("Quick Husky Sort", (String[] array) -> {

            QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort", HuskyCoderFactory.englishCoder, (String[] arr) -> {
                Arrays.sort(arr);
            });
            qhs.sort(array, true);
        }, Language.ENGLISH);

        //LSD SORT ENGLISH
        benchMarkAnySort("LSD Sort", (String[] array) -> {

            LSDStringSort lsd = new LSDStringSort();
            String[] copy = Arrays.copyOf(array, array.length);
            lsd.sort(copy);

        }, Language.ENGLISH);

        //MSD SORT ENGLISH
        benchMarkAnySort("MSD Sort", (String[] array) -> {

            MSDStringSort msd = new MSDStringSort();
            String[] copy = Arrays.copyOf(array, array.length);
            msd.sort(copy);

        }, Language.ENGLISH);

        //PURE HUSKY SORT
        benchMarkAnySort("Pure Husky Sort", (String[] array) -> {
            PureHuskySort<String> phs = new PureHuskySort<>(HuskyCoderFactory.englishCoder, false, false);
            String[] copy = Arrays.copyOf(array, array.length);
            phs.sort(copy);
        }, Language.ENGLISH);
    }

    /**
     * This method currently runs the benchmark for sorting methods on chinese strings and only with java.text.Collator.
     * Change Collator to com.ibm.icu.text.Collator if you want to obtain benchmarking for IBM Collator.
     */
    private static void benchMarkChinese(){
        //QUICK SORT CHINESE
        benchMarkAnySort("Quick Sort Dual Pivot", (String[] array) -> {

            QuickSort_DualPivot qs = new QuickSort_DualPivot<String>("Quick Sort", array.length);
            qs.sort(array, true, chineseInBuiltCollator);

        }, Language.CHINESE);

        //TIM SORT CHINESE
        benchMarkAnySort("Tim Sort", (String[] array) -> {

            try {
                new TimSort<String>().sort(array, true, chineseInBuiltCollator);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }, Language.CHINESE);

        //  QUICK HUSKY SORT CHINESE
        benchMarkAnySort("Quick Husky Sort", (String[] array) -> {

            QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort", chineseInBuiltCoder, (String[] arr) -> {
                Arrays.sort(arr, chineseInBuiltCollator);
            });
            qhs.sort(array, true);
        }, Language.CHINESE);


        //MSD SORT CHINESE
        benchMarkAnySort("MSD Sort", (String[] array) -> {

            MSDStringSortCollator msd = new MSDStringSortCollator();
            String[] copy = Arrays.copyOf(array, array.length);
            msd.sort(copy,chineseInBuiltCollator);

        }, Language.CHINESE);

        //PURE HUSKY SORT CHINESE
        benchMarkAnySort("Pure Husky Sort", (String[] array) -> {
            PureHuskySort<String> phs = new PureHuskySort<>(chineseInBuiltCoder, false, false);
            String[] copy = Arrays.copyOf(array, array.length);
            phs.sort(copy);
        }, Language.CHINESE);

        //LSD SORT CHINESE
        benchMarkAnySort("LSD Sort", (String[] array) -> {

            LSDStringSortCollator lsd = new LSDStringSortCollator();
            String[] copy = Arrays.copyOf(array, array.length);
            lsd.sort(copy,chineseInBuiltCollator);

        }, Language.CHINESE);

    }

    /**
     * This method reads the input from shuffledChinese.txt or randomstrings.txt for chinese and english respectively.
     * @param lang the language to sort
     * @param cntOfWords the size of the array to be returned. max can be only 4_000_000
     * @return an array of cntOfWords no of strings
     */
    private static String[] getDataToSort(Language lang, int cntOfWords) {
        String[] array = null;
        StringBuilder filePath = new StringBuilder("src/main/RandomString/");
        switch (lang) {
            case ENGLISH:
                filePath.append("English/");
                filePath.append("randomstrings.txt");
                break;
            case CHINESE:
                filePath.append("Chinese/");
                filePath.append("shuffledChinese.txt");
                break;
        }

        FileUtil fileUtil = new FileUtil(filePath.toString());
        array = fileUtil.read(cntOfWords);
        return array;
    }

    /**
     * This method takes a sorting method and times it varying sizes of input and writes the measured times to a file under Results.
     * The benchmarking starts from an array size of 15625 and goes till 4 million.
     * The array size is doubled each time.
     * @param desc a simple description of the sort method being benchmarked
     * @param sortFunction the sorting method that needs to be timed.
     * @param lang the language either Chinese or English.
     */
    public static void benchMarkAnySort(String desc, Consumer<String[]> sortFunction, Language lang) {

        Benchmark<String[]> bm = new Benchmark<String[]>(desc, sortFunction);
        FileUtil fileUtil = new FileUtil("src/main/Results/Results1.txt");

        int[] cntOfWords = new int[9];
        cntOfWords[0] = 15625;
        for (int i = 1; i < cntOfWords.length; i++) {
            cntOfWords[i] = cntOfWords[i - 1] * 2;
        }

        for (int arraySize : cntOfWords) {
            String[] array = getDataToSort(lang, arraySize);
            double avgTime = bm.run(array, 100);
            fileUtil.writeToFile(desc + " took " + avgTime + " ms to sort " + arraySize + " " + lang.getLanguage()+ " strings");
            System.out.println(desc + " took " + avgTime + " time to sort " + arraySize + " " + lang.getLanguage()+ " strings");
        }
        System.out.println();
    }



}
