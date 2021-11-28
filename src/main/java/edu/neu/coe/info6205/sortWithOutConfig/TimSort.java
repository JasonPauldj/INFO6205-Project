/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sortWithOutConfig;


import edu.neu.coe.info6205.sortWithOutConfig.helper.BaseHelper;
import edu.neu.coe.info6205.sortWithOutConfig.helper.Helper;
import edu.neu.coe.info6205.sortWithOutConfig.helper.SortWithHelper;

import java.io.IOException;
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
     *
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

    public static final String DESCRIPTION = "Timsort";
}

