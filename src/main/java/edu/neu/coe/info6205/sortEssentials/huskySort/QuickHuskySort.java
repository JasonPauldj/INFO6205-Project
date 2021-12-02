/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sortEssentials.huskySort;

import edu.neu.coe.info6205.sortEssentials.huskySortUtils.HuskyCoder;

import java.text.Collator;
import java.util.Arrays;
import java.util.function.Consumer;

public final class QuickHuskySort<X extends Comparable<X>> extends AbstractHuskySort<X> {

    /**
     * Primary constructor to create an implementation of HuskySort which primarily uses Quicksort.
     *
     * @param name       the name of the sorter (used for the helper).
     * @param n          the number of elements to be sorted (may be 0 if unknown).
     * @param huskyCoder the Husky coder.
     * @param postSorter the post-sorter (i.e. the sort method which will fix any remaining inversions).
     */
    public QuickHuskySort(String name, int n, HuskyCoder<X> huskyCoder, Consumer<X[]> postSorter, boolean makeCopy) {
        super(name, n, huskyCoder, postSorter, makeCopy);
    }

    /**
     * Secondary constructor to create an implementation of HuskySort which primarily uses Quicksort.
     * The number of elements to be sorted is unknown.
     *
     * @param name       the name of the sorter (used for the helper).
     * @param huskyCoder the Husky coder.
     * @param postSorter the post-sorter (i.e. the sort method which will fix any remaining inversions).
     */
    public QuickHuskySort(String name, HuskyCoder<X> huskyCoder, Consumer<X[]> postSorter, boolean makeCopy) {
        this(name, 0, huskyCoder, postSorter, makeCopy);
    }

    /**
     * Secondary constructor to create an implementation of HuskySort which primarily uses Quicksort.
     * The name will be QuickHuskySort/System.
     * The post-sorter will be the System sort.
     *
     * @param huskyCoder the Husky coder.
     */
    public QuickHuskySort(HuskyCoder<X> huskyCoder, boolean makeCopy) {
        this("QuickHuskySort/System", huskyCoder, Arrays::sort, makeCopy);
    }

    /**
     * Primary constructor to create an implementation of HuskySort which primarily uses Quicksort.
     *
     * @param name       the name of the sorter (used for the helper).
     * @param n          the number of elements to be sorted (may be 0 if unknown).
     * @param huskyCoder the Husky coder.
     * @param postSorter the post-sorter (i.e. the sort method which will fix any remaining inversions).
     */
    public QuickHuskySort(String name, int n, HuskyCoder<X> huskyCoder, Consumer<X[]> postSorter) {
        super(name, n, huskyCoder, postSorter);
    }

    /**
     * Secondary constructor to create an implementation of HuskySort which primarily uses Quicksort.
     * The number of elements to be sorted is unknown.
     *
     * @param name       the name of the sorter (used for the helper).
     * @param huskyCoder the Husky coder.
     * @param postSorter the post-sorter (i.e. the sort method which will fix any remaining inversions).
     */
    public QuickHuskySort(String name, HuskyCoder<X> huskyCoder, Consumer<X[]> postSorter) {
        this(name, 0, huskyCoder, postSorter);
    }

    /**
     * Secondary constructor to create an implementation of HuskySort which primarily uses Quicksort.
     * The name will be QuickHuskySort/System.
     * The post-sorter will be the System sort.
     *
     * @param huskyCoder the Husky coder.
     */
    public QuickHuskySort(HuskyCoder<X> huskyCoder) {
        this("QuickHuskySort/System", huskyCoder, Arrays::sort);
    }

    /**
     * Primary sort method, defined in Sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort.
     * @param to   the index of the first element not to sort.
     */
    public void sort(X[] xs, int from, int to) {
        generateLongs(xs);
        quickSort(xs, getHelper().getLongs(), from, to - 1);
        postSort(xs);
    }

    @Override
    public void sortBuiltInCollator(X[] xs, int from, int to, Collator cl) {
        //todo
    }

    @Override
    public void sortIBMCollator(X[] xs, int from, int to, com.ibm.icu.text.Collator cl) {
        //todo
    }

    // CONSIDER inlining this private method
    // CONSIDER redefining to to be one higher.
    @SuppressWarnings({"UnnecessaryLocalVariable"})
    private void quickSort(X[] objects, long[] longs, int from, int to) {
        int lo = from, hi = to;
        if (hi <= lo) return;
        Partition partition = partition(objects, longs, lo, hi);
        quickSort(objects, longs, lo, partition.lt - 1);
        quickSort(objects, longs, partition.gt + 1, hi);
    }

    private Partition partition(X[] objects, long[] longs, int lo, int hi) {
        // CONSIDER creating a method less in order to avoid having direct access to the longs.
        int lt = lo, gt = hi;
        if (longs[lo] > longs[hi]) swap(objects, lo, hi);
        long v = longs[lo];
        int i = lo + 1;
        while (i <= gt) {
            if (longs[i] < v) swap(objects, lt++, i++);
            else if (longs[i] > v) swap(objects, i, gt--);
            else i++;
        }
        return new Partition(lt, gt);
    }

    private static class Partition {
        final int lt;
        final int gt;

        Partition(int lt, int gt) {
            this.lt = lt;
            this.gt = gt;
        }
    }
}
