package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;

public class MSDStringSortTest {

    @Test
    public void testSortRandom() {
        Random random = new Random();
        String[] input = new String[100];
        for( int i = 0 ; i < 100 ; i++){
            int val = random.nextInt(300);
            input[i] = String.valueOf(val);
        }
        String[] expected = Arrays.copyOf(input,input.length);
        new MSDStringSort().sort(input);
        Arrays.sort(expected);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSortEnglish() {

        FileUtil fu= new FileUtil("src/main/RandomString/English/randomstrings.txt");
        String[] input = fu.read(100);


        String[] expected = Arrays.copyOf(input,input.length);
        new MSDStringSort().sort(input);
        Arrays.sort(expected);
        assertArrayEquals(expected, input);
    }

}