package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.sortEssentials.MSDStringSort;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class MSDStringSortTest {

    String[] input = "she sells seashells by the seashore the shells she sells are surely seashells".split(" ");
    String[] expected = "are by seashells seashells seashore sells sells she she shells surely the the".split(" ");

    @Test
    public void sort() {
        MSDStringSort.sort(input);
        System.out.println(Arrays.toString(input));
        assertArrayEquals(expected, input);
    }

    @Test
    public void sort1() {
        Random random = new Random();
        String[] input = new String[10];
        for( int i = 0 ; i < 10 ; i++){
            int val = random.nextInt(300);
            input[i] = String.valueOf(val);
        }
        String[] expected = Arrays.copyOf(input,input.length);
        Arrays.sort(expected);
        MSDStringSort.sort(input);
        assertArrayEquals(expected, input);
    }
}