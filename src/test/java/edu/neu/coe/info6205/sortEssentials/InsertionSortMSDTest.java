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


}