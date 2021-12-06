package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.sortEssentials.linearithmic.QuickSort_DualPivot;
import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;

public class MSDStringSortCollatorTest {


    @Test
    public void sortRandom() {
        Random random = new Random();
        String[] input = new String[20];
        for( int i = 0 ; i < 20 ; i++){
            int val = random.nextInt(300);
            input[i] = val + "a";
        }
        String[] expected = Arrays.copyOf(input,input.length);
        Arrays.sort(expected);
        new MSDStringSortCollator().sort(input,Collator.getInstance(Locale.ENGLISH));
        assertArrayEquals(expected, input);
    }


    @Test
    public void testSortInBuiltCollatorEnglish(){
        FileUtil fu= new FileUtil("src/main/RandomString/English/randomstrings.txt");
        String[] input = fu.read(100);

        String[] expected = Arrays.copyOf(input,input.length);
        new MSDStringSortCollator().sort(input,Collator.getInstance(Locale.ENGLISH));
        Arrays.sort(expected);
        assertArrayEquals(expected, input);

    }


    @Test
    public void testSortChineseIBMCollator(){
        FileUtil fu = new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] in = fu.read(100);
        String[] out = Arrays.copyOf(in,in.length);
        Arrays.sort(out,com.ibm.icu.text.Collator.getInstance(Locale.CHINESE));

        MSDStringSortCollator msd = new MSDStringSortCollator();
        msd.sort(in, com.ibm.icu.text.Collator.getInstance(Locale.CHINESE));
        assertArrayEquals(out, in);
    }


    @Test
    public void testSortChineseInBuiltCollator(){
        FileUtil fu= new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] input = fu.read(200);
        String[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected,Collator.getInstance(Locale.CHINESE));

        MSDStringSortCollator msd = new MSDStringSortCollator();
        msd.sort(input,Collator.getInstance(Locale.CHINESE));

        assertArrayEquals(expected,input);
    }

//    @Ignore
//    @Test
//    public void testSortError(){
//       // FileUtil fu= new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
//        String[] input = {"陈崇","陈重庆"};
//       // FileUtil fo = new FileUtil("src/main/SortedString/Chinese/sortedChinese.txt");
//        String[] expected = {"陈重庆","陈崇"};
//
//        MSDStringSortCollator msd = new MSDStringSortCollator();
//        msd.sort(input, com.ibm.icu.text.Collator.getInstance(Locale.CHINESE));
//
//        assertArrayEquals(expected,input);
//    }

//    @Ignore
//    @Test
//    public void testSortError(){
//        com.ibm.icu.text.Collator col = com.ibm.icu.text.Collator.getInstance(Locale.CHINESE);
//        assertEquals(col.compare("陈崇","陈重庆"),1);
//        assertEquals(col.compare("陈","陈"),0);
//        // for some reason the IBM Collator returns -1 instead of 1 while comparing individual characters
//        assertEquals(col.compare("崇","重"),1);
//    }
}