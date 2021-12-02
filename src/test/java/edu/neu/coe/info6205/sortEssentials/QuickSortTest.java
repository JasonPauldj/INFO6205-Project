package edu.neu.coe.info6205.sortEssentials;


import edu.neu.coe.info6205.sortEssentials.linearithmic.QuickSort;
import edu.neu.coe.info6205.sortEssentials.linearithmic.QuickSort_DualPivot;
import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;

public class QuickSortTest {

    @Test
    public void testSort() throws Exception {
        Integer[] xs = new Integer[4];
        xs[0] = 3;
        xs[1] = 4;
        xs[2] = 2;
        xs[3] = 1;
        QuickSort<Integer> s = new QuickSort_DualPivot<>(xs.length);
        Integer[] ys = s.sort(xs,false);
        assertEquals(Integer.valueOf(1), ys[0]);
        assertEquals(Integer.valueOf(2), ys[1]);
        assertEquals(Integer.valueOf(3), ys[2]);
        assertEquals(Integer.valueOf(4), ys[3]);
    }

    @Test
    public void testSort1() throws Exception {
        String[] input = new String[10];
        Random random = new Random();
        QuickSort_DualPivot<String> s = new QuickSort_DualPivot<String>("QuickSort", 10);
        for( int i = 0 ; i < 10 ; i++){
            int val = random.nextInt(300);
            input[i] = String.valueOf(val);
        }
        String[] expected = Arrays.copyOf(input,input.length);
        String[] ans = s.sort(input,false);
        Arrays.sort(expected);
        assertArrayEquals (expected, ans);
    }

    @Test
    public  void testSort3(){
        String[] input = "阿兵 阿安 阿冰冰 阿斌 阿滨 阿冰 阿彬".split(" ");
        String[] expected = "阿安 阿彬 阿斌 阿滨 阿兵 阿冰 阿冰冰".split(" ");
        QuickSort_DualPivot<String> s = new QuickSort_DualPivot<String>("QuickSort", 10);
         s.sortBuiltInCollator(input,0,input.length,Collator.getInstance(Locale.CHINA));
        Arrays.sort(input, Collator.getInstance(Locale.CHINA));
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSort4() throws IOException {
        FileUtil fu= new FileUtil("/Users/jasonpauldarivemula/Desktop/INFO6205-Project/src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] input = fu.read();
        FileUtil fo = new FileUtil("/Users/jasonpauldarivemula/Desktop/INFO6205-Project/src/main/RandomString/Chinese/sortedChinese.txt");
        String[] expected = fo.read();
        QuickSort_DualPivot<String> s = new QuickSort_DualPivot<>("QuickSort", input.length);
//        String[] sortedInBuilt = s.sort(input,true, Collator.getInstance(Locale.CHINA));
        String[] sortedIBMColl = s.sort(input,true, com.ibm.icu.text.Collator.getInstance(Locale.CHINA));
//        FileUtil fw = new FileUtil();
//        fw.writeAsCsv(Arrays.asList(sortedIBMColl));
        String filepath = "/Users/jasonpauldarivemula/Desktop/INFO6205-Project/src/main/RandomString/Chinese/IBMsortedChinese.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(Arrays.toString(sortedIBMColl));
        writer.close();
//        assertArrayEquals(expected,sortedInBuilt);
       assertArrayEquals(expected, sortedIBMColl);
// 阿安, 阿彬, 阿斌, 阿滨, 阿冰, 阿冰冰, 阿兵, 阿婵, 阿超( IBM Collator)
// 阿安, 阿彬, 阿斌, 阿滨, 阿兵, 阿冰, 阿冰冰 (Local.China)


    }

}