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
//    private static void writeDataToSort(List<String> lines, int i) {
//        if (i == 1) {
//            FileUtil fileUtil = new FileUtil("src/main/RandomString/English/EnglishResults.txt");
//            fileUtil.writeAsCsv(lines);
//        } else if (i == 2) {
//            FileUtil fileUtil = new FileUtil("src/main/RandomString/Chinese/newSortedChinese.txt");
//            fileUtil.writeAsCsv(lines);
//        }
//    }

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
                new QuickSort_DualPivot<String>(arr.length).sort(arr, false, cl);
            else
                new QuickSort_DualPivot<String>(arr.length).sort(arr, false);
        });
        classList.add((String[] arr) -> {
            try {
                QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort", chineseIBMCoder, (String[] array) -> {
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
    }

    public static void benchMarkAnySort(String desc, Consumer<String[]> sortFunction, Language lang) {

        Benchmark<String[]> bm = new Benchmark<String[]>(desc, sortFunction);
        FileUtil fileUtil = new FileUtil("src/main/Results/Results.txt");

        int[] cntOfWords = new int[9];
        cntOfWords[0] = 15625;
        for (int i = 1; i < cntOfWords.length; i++) {
            cntOfWords[i] = cntOfWords[i - 1] * 2;
        }

        for (int arraySize : cntOfWords) {
            String[] array = getDataToSort(lang, arraySize);
            double avgTime = bm.run(array, 5);
            fileUtil.writeToFile(desc + " took " + avgTime + " ms to sort " + arraySize + " " + lang.getLanguage()+ " strings");
            System.out.println(desc + " took " + avgTime + " time to sort " + arraySize + " " + lang.getLanguage()+ " strings");
        }
        System.out.println();
    }

    public static void benchmarkQuickSort() {
        Benchmark<String[]> bm = new Benchmark<String[]>("benchmarking quick sort",
                (String[] array) -> {
                    try {
                        QuickSort_DualPivot qs = new QuickSort_DualPivot<String>("Quick Sort", array.length);
                        array = (String[]) qs.sort(array, true, com.ibm.icu.text.Collator.getInstance(Locale.CHINA));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        String[] array = getDataToSort(2);

        double d = bm.run(array, 1);
        System.out.println(d);
        FileUtil fileUtil = new FileUtil("src/main/RandomString/Chinese/sortedChinese.txt");
        String[] in = fileUtil.read();
//        String[] res = qs.sort()
        System.out.println(Arrays.equals(array, in));

    }

    public static void benchmarkTimSort() {
        Benchmark<String[]> bm = new Benchmark<String[]>("benchmarking tim sort",
                (String[] array) -> {
                    try {
                        new TimSort<String>().sort(array, true, chineseIBMCollator);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        String[] array = getDataToSort(2);

        double d = bm.run(array, 10);
        System.out.println(d);
//        FileUtil fileUtil = new FileUtil("src/main/RandomString/Chinese/sortedChinese.txt");
//        String[] in = fileUtil.read();
//        System.out.println(Arrays.equals(array,in));
    }

    public static void benchmarkHuskySort() {
        Benchmark<String[]> bm = new Benchmark<String[]>("benchmarking tim sort",
                (String[] array) -> {
                    try {
                        QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort", chineseIBMCoder, (String[] arr) -> {
                            Arrays.sort(arr, chineseIBMCollator);
                        });
                        qhs.sort(array, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        String[] array = getDataToSort(2);

        double d = bm.run(array, 10);
        System.out.println(d);
    }

}
