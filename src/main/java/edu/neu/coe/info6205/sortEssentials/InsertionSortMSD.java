package edu.neu.coe.info6205.sortEssentials;

import java.text.Collator;
import java.util.Arrays;

/**
 * This is a basic implementation of insertion sort.
 * It does not extend Sort, nor does it employ any optimizations.
 */
public class InsertionSortMSD {

    public static void sort(String[] a, int lo, int hi, int d, Collator cl) {
        for (int i = lo; i < hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1], d, cl); j--)
                swap(a, j, j - 1);
//        System.out.println(Arrays.toString(a));
    }

    private static boolean less(String v, String w, int d, Collator cl) {
        if (cl == null)
            return v.substring(d).compareTo(w.substring(d)) < 0;
        else
            return cl.compare(v.substring(d),w.substring(d)) < 0;
    }

    private static void swap(Object[] a, int j, int i) {
        Object temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
}
