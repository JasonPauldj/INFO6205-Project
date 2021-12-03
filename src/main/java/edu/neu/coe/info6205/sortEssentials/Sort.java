/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sortEssentials;

import java.text.Collator;
import java.util.Arrays;

public interface Sort<X> {

    /**
     * Generic, mutating sort method which operates on a sub-array.
     *
     * @param xs   sort the array xs from "from" until "to" (exclusive of to).
     * @param from the index of the first element to sort.
     * @param to   the index of the first element not to sort.
     */
    void sort(X[] xs, int from, int to);

    /**
     * Generic, mutating sort method which operates on a sub-array and takes the java inbuilt collator.
     *
     * @param xs   sort the array xs from "from" until "to" (exclusive of to).
     * @param from the index of the first element to sort.
     * @param to   the index of the first element not to sort.
     * @param cl   Collator.
     */
    void sortBuiltInCollator(X[] xs, int from, int to, Collator cl);

    /**
     * Generic, mutating sort method which operates on a sub-array and takes the IBM collator.
     *
     * @param xs   sort the array xs from "from" until "to" (exclusive of to).
     * @param from the index of the first element to sort.
     * @param to   the index of the first element not to sort.
     * @param cl   Collator.
     */
    void sortIBMCollator(X[] xs, int from, int to, com.ibm.icu.text.Collator cl);

    /**
     * This method is to be used only with Husky Sort. This method is overriden in the Husky sort implementations.
     * The default method invokes init with the length of the array xs then makes a copy of the array if appropriate.
     *
     * @param xs       the original array to be sorted.
     * @param makeCopy true if we need to work on a copy of the array.
     * @return either the original or a copy of the array.
     */
//    default X[] preSort(X[] xs, boolean makeCopy) {
//        init(xs.length);
//        return makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
//    }

    /**
     * Generic, non-mutating sort method which allows for explicit determination of the makeCopy option.
     *
     * @param xs       sort the array xs, returning the sorted result, leaving xs unchanged.
     * @param makeCopy if set to true, we make a copy first and sort that.
     */
    default X[] sort(X[] xs, boolean makeCopy) {
        init(xs.length);
        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
        sort(result, 0, result.length);
        return result;
    }

    /**
     * Generic, non-mutating sort method which allows for explicit determination of the makeCopy option.
     *
     * @param xs       sort the array xs, returning the sorted result, leaving xs unchanged.
     * @param makeCopy if set to true, we make a copy first and sort that.
     * @param cl passing java inbuilt collator
     */
    default X[] sort(X[] xs, boolean makeCopy, Collator cl) {
        init(xs.length);
        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
        sortBuiltInCollator(result, 0, result.length,cl);
        return result;
    }

    /**
     * Generic, non-mutating sort method which allows for explicit determination of the makeCopy option.
     *
     * @param xs       sort the array xs, returning the sorted result, leaving xs unchanged.
     * @param makeCopy if set to true, we make a copy first and sort that.
     * @param cl passing IBM Collator
     */
    default X[] sort(X[] xs, boolean makeCopy, com.ibm.icu.text.Collator cl) {
        init(xs.length);
        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
        sortIBMCollator(result, 0, result.length,cl);
        return result;
    }

    /**
     * Method to clean up after sorting, invoked by the default implementation of sort(X[], boolean).
     * The default method simply returns the sorted array.
     *
     * @param xs the sorted array.
     * @return the sorted array.
     */
    default X[] postSort(X[] xs) {
        return xs;
    }

    /**
     * Perform initializing step for this Sort.
     * <p>
     * CONSIDER merging this with preProcess logic.
     *
     * @param n the number of elements to be sorted.
     */
    void init(int n);

    /**
     * Perform pre-processing step for this Sort.
     *
     * @param xs the elements to be pre-processed.
     */
    default X[] preProcess(X[] xs) {
        init(xs.length);
        return xs;
    }

    /**
     * Post-process the given array, i.e. after sorting has been completed.
     *
     * @param xs an array of Xs.
     */
    void postProcess(X[] xs);


}
