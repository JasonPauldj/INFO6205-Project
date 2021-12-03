package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.sortEssentials.linearithmic.QuickSort_DualPivot;
import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;

public class MSDStringSortCollatorTest {

//    String[] input = "she sells seashells by the seashore the shells she sells are surely seashells".split(" ");
//    String[] expected = "are by seashells seashells seashore sells sells she she shells surely the the".split(" ");


    @Test
    public void sortRandom() {
        Random random = new Random();
        String[] input = new String[20];
        for( int i = 0 ; i < 20 ; i++){
            int val = random.nextInt(300);
            input[i] = val + "a";
        }
        System.out.println("Input " + Arrays.toString(input));
        String[] expected = Arrays.copyOf(input,input.length);
        Arrays.sort(expected);
        System.out.println("Expected " + Arrays.toString(expected));
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
        String[] in = fu.read();
        FileUtil fo = new FileUtil("src/main/SortedString/Chinese/IBMsortedChinese.txt");
        String[] out = fo.read();

        MSDStringSortCollator msd = new MSDStringSortCollator();
        msd.sort(in, com.ibm.icu.text.Collator.getInstance(Locale.CHINESE));
        assertArrayEquals(in, out);
    }


    @Test
    public void testSortChineseInBuiltCollator(){
        FileUtil fu= new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] input = fu.read();
        FileUtil fo = new FileUtil("src/main/SortedString/Chinese/sortedChinese.txt");
        String[] expected = fo.read();

        MSDStringSortCollator msd = new MSDStringSortCollator();
        msd.sort(input,Collator.getInstance(Locale.CHINESE));

        assertArrayEquals(expected,input);
    }
}