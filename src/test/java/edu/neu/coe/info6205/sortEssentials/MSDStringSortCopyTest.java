package edu.neu.coe.info6205.sortEssentials;

import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.Assert.*;

public class MSDStringSortCopyTest {

    @Test
    public void sort2() {
//        Random random = new Random();
//        String[] input = new String[10];
//        for( int i = 0 ; i < 10 ; i++){
//            int val = random.nextInt(300);
//            input[i] = String.valueOf(val);
//        }
        String[] input =  "阿兵 阿安 阿冰冰 阿斌 阿滨 阿冰 阿彬".split(" ");
        String[] expected = "阿安 阿彬 阿斌 阿滨 阿兵 阿冰 阿冰冰".split(" ");
//        String[] expected = Arrays.copyOf(input,input.length);
//        Arrays.sort(expected);
        System.out.println(Arrays.toString(input));
//        InsertionSortMSD.sort(input, 0, input.length, 1,Collator.getInstance(Locale.CHINA));
        MSDStringSortCopy.sort(input, Collator.getInstance(Locale.CHINESE));
        System.out.println("Sorted Array " + Arrays.toString(input));
        System.out.println("Expected Array " + Arrays.toString(expected));
        assertArrayEquals(expected, input);
    }

}