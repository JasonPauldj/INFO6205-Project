package edu.neu.coe.info6205.sortEssentials.huskySort;

import edu.neu.coe.info6205.sortEssentials.huskySortUtils.ChineseCoder;
import edu.neu.coe.info6205.sortEssentials.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.Assert.*;

public class QuickHuskySortTest {


    @Test
    public void testSortEnglish() {

        FileUtil fu= new FileUtil("src/main/RandomString/English/randomstrings.txt");
        String[] input = fu.read(100);
        String[] expected = Arrays.copyOf(input,input.length);

        QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort",HuskyCoderFactory.englishCoder,(String[] arr) ->{
            Arrays.sort(arr);
        });
        qhs.sort(input,false);

        Arrays.sort(expected);
        assertArrayEquals(expected, input);
    }


    @Test
    public void testSortChineseInBuiltCollator(){
        FileUtil fu= new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] input = fu.read(100);
        String[] expected = Arrays.copyOf(input,input.length);


           QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort",new ChineseCoder(Collator.getInstance(Locale.CHINESE)),(String[] arr) ->{
               Arrays.sort(arr,  Collator.getInstance(Locale.CHINESE));
           });
           qhs.sort(input, false);


        Arrays.sort(expected,Collator.getInstance(Locale.CHINESE));
        assertArrayEquals(expected,input);
    }

    @Test
    public void testSortChineseIBMCollator(){
        FileUtil fu= new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] input = fu.read(100);
        String[] expected = Arrays.copyOf(input,input.length);


            QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort",new ChineseCoder(com.ibm.icu.text.Collator.getInstance(Locale.CHINESE)),(String[] arr) ->{
                Arrays.sort(arr,  com.ibm.icu.text.Collator.getInstance(Locale.CHINESE));
            });
        qhs.sort(input, false);

        Arrays.sort(expected,com.ibm.icu.text.Collator.getInstance(Locale.CHINESE));
        assertArrayEquals(expected,input);
    }
}