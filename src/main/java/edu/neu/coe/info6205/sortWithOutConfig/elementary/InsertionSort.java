/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sortWithOutConfig.elementary;

import edu.neu.coe.info6205.sortWithOutConfig.helper.BaseHelper;
import edu.neu.coe.info6205.sortWithOutConfig.helper.Helper;
import edu.neu.coe.info6205.sortWithOutConfig.helper.SortWithHelper;
import edu.neu.coe.info6205.util.Config;

import java.text.Collator;

public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     *
     */
    protected InsertionSort(String description, int N) {
        super(description, N);
    }

    @Override
    public void sort(String[] array, int i, int length, Collator instance) {

    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     *
     */
    public InsertionSort(int N) {
        this(DESCRIPTION, N);
    }

    /*public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION));
    }*/

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(new BaseHelper<>(DESCRIPTION));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        for(int i=from+1;i<to;i++){
            for(int j=i-1;j>=from;j--){
                if(helper.compare(xs[j],xs[j+1])==1)
                    helper.swap(xs,j,j+1);
                else
                    break;
            }
        }

    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }
}
