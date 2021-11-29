package edu.neu.coe.info6205.sortWithOutConfig;

import edu.neu.coe.info6205.sort.Partitioner;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class QuickSortTest {

    @Test
    public void testSort() throws Exception {
        Integer[] xs = new Integer[4];
        xs[0] = 3;
        xs[1] = 4;
        xs[2] = 2;
        xs[3] = 1;
        QuickSort<Integer> s = new QuickSort_DualPivot<>(xs.length);
        Integer[] ys = s.sort(xs);
        assertEquals(Integer.valueOf(1), ys[0]);
        assertEquals(Integer.valueOf(2), ys[1]);
        assertEquals(Integer.valueOf(3), ys[2]);
        assertEquals(Integer.valueOf(4), ys[3]);
    }

    @Test
    public void testSort1() throws Exception {
        String[] input = new String[10];
        Random random = new Random();

        QuickSort<String> s = new QuickSort_DualPivot<>(input.length);
        for( int i = 0 ; i < 10 ; i++){
            int val = random.nextInt(300);
            input[i] = String.valueOf(val);
        }
        String[] expected = Arrays.copyOf(input,input.length);
        String[] ans = s.sort(input);
        Arrays.sort(expected);
        System.out.println(Arrays.toString(input));
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(expected));

        assertArrayEquals (expected, ans);
    }

}