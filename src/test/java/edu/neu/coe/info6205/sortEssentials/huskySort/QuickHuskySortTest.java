package edu.neu.coe.info6205.sortEssentials.huskySort;

import edu.neu.coe.info6205.sortEssentials.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.Assert.*;

public class QuickHuskySortTest {

    @Test
    public void testSort(){
        FileUtil fu= new FileUtil("/Users/jasonpauldarivemula/Desktop/INFO6205-Project/src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] input = fu.read();
        FileUtil fo = new FileUtil("/Users/jasonpauldarivemula/Desktop/INFO6205-Project/src/main/RandomString/Chinese/sortedChinese.txt");
        String[] expected = fo.read();

       try {
            QuickHuskySort qhs = new QuickHuskySort<String>("Quick Husky Sort", HuskyCoderFactory.chineseCoder,(String[] arr) ->{
                Arrays.sort(arr,  Collator.getInstance(Locale.CHINA));
            });
            qhs.huskySort(input, false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Arrays.sort(input);

        for(int i=0;i<10;i++)
            System.out.println(input[i]);

        assertArrayEquals(expected,input);
    }
}