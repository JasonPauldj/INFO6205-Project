package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class LSDStringSortTest {

    @Test
    public void sort2() {
        Random random = new Random();
        String[] input = new String[30];
        for( int i = 0 ; i < 30 ; i++){
            int val = random.nextInt(300);
            input[i] = String.valueOf(val);
        }
        String[] expected = Arrays.copyOf(input,input.length);
        new MSDStringSort().sort(input);
        System.out.println("Sorted Array " + Arrays.toString(input));
        Arrays.sort(expected);
        System.out.println("Expected Array " + Arrays.toString(expected));
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSortEnglish() {
        FileUtil fu= new FileUtil("src/main/RandomString/English/randomstrings.txt");
        String[] input = fu.read(100);

        String[] expected = Arrays.copyOf(input,input.length);
        new LSDStringSort().sort(input);
        Arrays.sort(expected);
        assertArrayEquals(expected, input);
    }

}