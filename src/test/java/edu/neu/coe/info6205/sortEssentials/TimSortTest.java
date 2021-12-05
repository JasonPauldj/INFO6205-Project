package edu.neu.coe.info6205.sortEssentials;

import com.ibm.icu.text.Collator;
import edu.neu.coe.info6205.sortEssentials.huskySort.QuickHuskySort;
import edu.neu.coe.info6205.sortEssentials.huskySortUtils.ChineseCoder;
import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;

public class TimSortTest {


    @Test
    public void testSort(){
        FileUtil fu= new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        Random random = new Random();

        String[] input = new String[100];

        for( int i = 0 ; i < 100 ; i++){
            int val = random.nextInt(300);
            input[i] = String.valueOf(val);
        }

        String[] expected = Arrays.copyOf(input, input.length);

        Arrays.sort(expected);

        try {
            new TimSort<String>().sort(input,false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertArrayEquals(expected,input);
    }

    @Test
    public void testSorInBuiltCollator(){
        FileUtil fu= new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] input = fu.read(100);

        String[] expected = Arrays.copyOf(input,input.length);
        Arrays.sort(expected,java.text.Collator.getInstance(Locale.CHINESE));

        try {
            new TimSort<String>().sort(input,false, java.text.Collator.getInstance(Locale.CHINESE));
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertArrayEquals(expected,input);
    }

    @Test
    public void testSortIBMCollator(){
        FileUtil fu= new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] input = fu.read(100);
        String[] expected = Arrays.copyOf(input,input.length);
        Arrays.sort(expected,Collator.getInstance(Locale.CHINESE));
        try {
            new TimSort<String>().sort(input,false, Collator.getInstance(Locale.CHINESE));
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertArrayEquals(expected,input);
    }
}