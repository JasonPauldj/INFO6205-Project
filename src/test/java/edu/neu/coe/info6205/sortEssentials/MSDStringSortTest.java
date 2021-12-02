package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.sortEssentials.MSDStringSort;
import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;

public class MSDStringSortTest {

    String[] input = "she sells seashells by the seashore the shells she sells are surely seashells".split(" ");
    String[] expected = "are by seashells seashells seashore sells sells she she shells surely the the".split(" ");

    @Test
    public void sort() {
        MSDStringSort.sort(input,null);
        System.out.println(Arrays.toString(input));
        assertArrayEquals(expected, input);
    }

    @Test
    public void sort1() {
        Random random = new Random();
        String[] input = new String[20];
        for( int i = 0 ; i < 20 ; i++){
            int val = random.nextInt(300);
            input[i] = String.valueOf(val) + "a";
        }
        String[] expected = Arrays.copyOf(input,input.length);
        Arrays.sort(expected);
        MSDStringSort.sort(input);
        assertArrayEquals(expected, input);
    }

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
        MSDStringSort.sort(input, Collator.getInstance(Locale.CHINESE));
        System.out.println("Sorted Array " + Arrays.toString(input));
        System.out.println("Expected Array " + Arrays.toString(expected));
        assertArrayEquals(expected, input);
    }

    @Test
    public void sort3() {
//        Random random = new Random();
//        String[] input = new String[10];
//        for( int i = 0 ; i < 10 ; i++){
//            int val = random.nextInt(300);
//            input[i] = String.valueOf(val);
//        }
        String[] input =  "jason paul is trying to solve this issue".split(" ");
        String[] expected = "is issue jason paul solve this to trying".split(" ");
//        String[] expected = Arrays.copyOf(input,input.length);
//        Arrays.sort(expected);
       // System.out.println(Arrays.toString(input));
//        InsertionSortMSD.sort(input, 0, input.length, 1,Collator.getInstance(Locale.CHINA));
        MSDStringSort.sort(input, Collator.getInstance(Locale.ENGLISH));
        System.out.println("Sorted Array " + Arrays.toString(input));
        System.out.println("Expected Array " + Arrays.toString(expected));
        assertArrayEquals(expected, input);
    }



    @Test
    public void sort5(){
        FileUtil fu = new FileUtil("/Users/ebby/Desktop/Grad School/PSA/INFO6205-Project/src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] in = fu.read();
        FileUtil fo = new FileUtil("/Users/ebby/Desktop/Grad School/PSA/INFO6205-Project/src/main/RandomString/Chinese/sortedChinese.txt");
        String[] out = fo.read();
//        String[] input = "阿兵 阿安 阿冰冰 阿斌 阿滨 阿冰 阿彬".split(" ");
//        String[] expected = "阿安 阿彬 阿斌 阿滨 阿兵 阿冰 阿冰冰".split(" ");
//        Arrays.sort(in, Collator.getInstance(Locale.CHINA));
        MSDStringSort.sort(in,Collator.getInstance(Locale.CHINA));
        assertArrayEquals(in, out);
    }
}