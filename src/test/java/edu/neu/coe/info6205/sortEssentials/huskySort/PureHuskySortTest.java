package edu.neu.coe.info6205.sortEssentials.huskySort;

import edu.neu.coe.info6205.sortEssentials.huskySortUtils.ChineseCoder;
import edu.neu.coe.info6205.sortEssentials.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.Assert.*;

public class PureHuskySortTest {

    @Test
    public void testSortEnglish() {

        FileUtil fu= new FileUtil("src/main/RandomString/English/randomstrings.txt");
        String[] input = fu.read(100);
        String[] expected = Arrays.copyOf(input,input.length);
        PureHuskySort<String> phs = new PureHuskySort<>(HuskyCoderFactory.englishCoder,false,false);
        phs.sort(input);
        Arrays.sort(expected);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSortChineseInBuiltCollator(){
        FileUtil fu= new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] input = fu.read(100);
        String[] expected = Arrays.copyOf(input,input.length);
        Arrays.sort(expected,Collator.getInstance(Locale.CHINESE));
        PureHuskySort<String> phs = new PureHuskySort<>(new ChineseCoder(Collator.getInstance(Locale.CHINESE)),false,false);
        phs.sort(input);
        assertArrayEquals(expected,input);
    }

    @Test
    public void testSortChineseIBMCollator(){
        FileUtil fu= new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] input = fu.read(100);
        String[] expected = Arrays.copyOf(input,input.length);
        Arrays.sort(expected,com.ibm.icu.text.Collator.getInstance(Locale.CHINESE));

        PureHuskySort<String> phs = new PureHuskySort<>(new ChineseCoder(com.ibm.icu.text.Collator.getInstance(Locale.CHINESE)),false,false);
        phs.sort(input);
        assertArrayEquals(expected,input);
    }

}