package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.sortEssentials.InsertionSortMSD;
import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;

public class InsertionSortMSDTest {

    @Test
    public void sort0() {
        String[] input = "she sells seashells by the seashore the shells she sells are surely seashells".split(" ");
        String[] expected = "are by seashells seashells seashore sells sells she she shells surely the the".split(" ");

        InsertionSortMSD.sort(input, 0, input.length, 0);
        assertArrayEquals(expected, input);
    }

    @Test
    public void sort1() {
        String[] input = "she sells seashells seashore shells she sells surely seashells".split(" ");
        String[] expected = "seashells seashells seashore sells sells she she shells surely".split(" ");

        InsertionSortMSD.sort(input, 0, input.length, 1);
        assertArrayEquals(expected, input);
    }

    @Test
    public void sort1a() {
        Random random = new Random();
        String[] input = new String[10];
        for( int i = 0 ; i < 10 ; i++){
            int val = random.nextInt(300);
            input[i] = String.valueOf(val);
        }
        String[] expected = Arrays.copyOf(input,input.length);
        Arrays.sort(expected);
        InsertionSortMSD.sort(input, 0, input.length, 0);
        assertArrayEquals(expected, input);
    }


    @Test
    public void sort2() {
        String[] input = "sells seashells seashore sells seashells".split(" ");
        String[] expected = "seashells seashells seashore sells sells".split(" ");

        InsertionSortMSD.sort(input, 0, input.length, 2);
        assertArrayEquals(expected, input);
    }

    @Test
    public void sort3(){
        FileUtil fu = new FileUtil("/Users/ebby/Desktop/Grad School/PSA/INFO6205-Project/src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] in = fu.read();
        FileUtil fo = new FileUtil("/Users/ebby/Desktop/Grad School/PSA/INFO6205-Project/src/main/RandomString/Chinese/sortedChinese.txt");
        String[] out = fo.read();
//        String[] input = "阿兵 阿安 阿冰冰 阿斌 阿滨 阿冰 阿彬".split(" ");
//        String[] expected = "阿安 阿彬 阿斌 阿滨 阿兵 阿冰 阿冰冰".split(" ");
        Arrays.sort(in, Collator.getInstance(Locale.CHINA));
        assertArrayEquals(in, out);
    }
}