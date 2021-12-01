/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.sortEssentials.BaseHelper;
import edu.neu.coe.info6205.sortEssentials.Helper;
import edu.neu.coe.info6205.sortEssentials.SortWithHelper;
import edu.neu.coe.info6205.util.Config;

import java.io.IOException;
import java.text.Collator;
import java.util.Arrays;

/**
 * Sorter which delegates to Timsort via Arrays.sort.
 *
 * @param <X>
 */
public class TimSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for TimSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public TimSort(Helper<X> helper) {
        super(helper);
    }

    /**
     * Constructor for TimSort
     *
     * @param N      the number elements we expect to sort.
     */
    public TimSort(int N) {
        super(DESCRIPTION, N);
    }

    public TimSort() throws IOException {
        this(new BaseHelper<>(DESCRIPTION));
    }

    public void sort(X[] xs, int from, int to) {
        Arrays.sort(xs, from, to);
    }
    public void sort(X[] xs, int from, int to, Collator cl) {
        Arrays.sort(xs, from, to, cl);
    }

    public static final String DESCRIPTION = "Timsort";
}

