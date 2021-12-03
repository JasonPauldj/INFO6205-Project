package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;

public class LSDStringSortCopyTest {

    @Test
    public void sort1() {
        Random random = new Random();
        String[] input = new String[20];
        for( int i = 0 ; i < 20 ; i++){
            int val = random.nextInt(300);
            input[i] = String.valueOf(val) + "a";
        }

        System.out.println("Input" + Arrays.toString(input));
        String[] expected = Arrays.copyOf(input,input.length);


        Arrays.sort(expected);
        System.out.println("Expected" + Arrays.toString(expected));


        new LSDStringSortCopy().sort(input, Collator.getInstance(Locale.ENGLISH));
        System.out.println("After Sort" + Arrays.toString(input));
        assertArrayEquals(expected, input);
    }

    @Test
    public void sort2() {

        String[] input =  "阿兵 阿安 阿冰冰 阿斌 阿滨 阿冰 阿彬".split(" ");
        String[] expected = "阿安 阿彬 阿斌 阿滨 阿兵 阿冰 阿冰冰".split(" ");

        System.out.println(Arrays.toString(input));
        new LSDStringSortCopy().sort(input, Collator.getInstance(Locale.CHINESE));
        System.out.println("Sorted Array " + Arrays.toString(input));
        System.out.println("Expected Array " + Arrays.toString(expected));
        assertArrayEquals(expected, input);
    }

    @Test
    public void sort5(){
        FileUtil fu = new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] in = fu.read();
        FileUtil fo = new FileUtil("src/main/SortedString/Chinese/sortedChinese.txt");
        String[] out = fo.read();
//        String[] input = "阿兵 阿安 阿冰冰 阿斌 阿滨 阿冰 阿彬".split(" ");
//        String[] expected = "阿安 阿彬 阿斌 阿滨 阿兵 阿冰 阿冰冰".split(" ");
//        Arrays.sort(in, Collator.getInstance(Locale.CHINA));
        new LSDStringSortCopy().sort(in,Collator.getInstance(Locale.CHINA));
        assertArrayEquals(in, out);
    }

}