package edu.neu.coe.info6205.sortEssentials;


import java.text.Collator;
import java.util.Comparator;


import static java.util.Arrays.binarySearch;

/**
 * Helper interface.
 * <p>
 * A Helper provides all of the utilities that are needed by sort methods, for example, compare and swap.
 * <p>
 * CONSIDER having the concept of a current sub-array, then we could dispense with the lo, hi parameters.
 *
 * @param <X>
 */
public interface Helper<X extends Comparable<X>>  {

    /*
     * Method to generate an array of randomly chosen X elements.
     *
     * @param clazz the class of X.
     * @param f     a function which takes a Random and generates a random value of X.
     * @return an array of X of length determined by the current value according to setN.
     */
//    X[] random(Class<X> clazz, Function<Random, X> f);

    /**
     * @return the description of this Helper.
     */
    String getDescription();

    /**
     * Get the current value of N i.e number of elements in the array.
     *
     * @return the value of N.
     */
    int getN();

    /**
     * Copy the element at source[j] into target[i]
     *
     * @param source the source array.
     * @param i      the target index.
     * @param target the target array.
     * @param j      the source index.
     */
    void copy(X[] source, int i, X[] target, int j);

    /**
     * Compare elements i and j of xs within the subarray lo..hi
     *
     * @param xs the array.
     * @param i  one of the indices.
     * @param j  the other index.
     * @return the result of comparing xs[i] to xs[j]
     */
    int compare(X[] xs, int i, int j);


    /**
     * Compare elements i and j of xs within the subarray lo..hi. Uses collator to compare the strings.
     *
     * @param xs the array.
     * @param i  one of the indices.
     * @param j  the other index.
     * @param cl Collator.
     * @return the result of comparing xs[i] to xs[j]
     */
    int compare(X[] xs, int i, int j, Collator cl);

    /**
     * Compare elements i and j of xs within the subarray lo..hi. Uses collator to compare the strings.
     *
     * @param xs the array.
     * @param i  one of the indices.
     * @param j  the other index.
     * @param cl Collator from IBM.
     * @return the result of comparing xs[i] to xs[j]
     */
    int compare(X[] xs, int i, int j, com.ibm.icu.text.Collator cl);


    /**
     * Compare values v and w and return true if v is less than w.
     *
     * @param v the first value.
     * @param w the second value.
     * @return true if v is less than w.
     */
    boolean less(X v, X w);

    /**
     * Compare value v with value w.
     *
     * @param v the first value.
     * @param w the second value.
     * @return -1 if v is less than w; 1 if v is greater than w; otherwise 0.
     */
    int compare(X v, X w);

    /**
     * Compare value v with value w. Uses the collator to do the comparison
     *
     * @param v the first value.
     * @param w the second value.
     * @return -1 if v is less than w; 1 if v is greater than w; otherwise 0.
     */
    int compare(X v, X w, Collator cl);

    /**
     * Compare value v with value w. Uses the IBM collator to do the comparison
     *
     * @param v the first value.
     * @param w the second value.
     * @return -1 if v is less than w; 1 if v is greater than w; otherwise 0.
     */
    int compare(X v, X w, com.ibm.icu.text.Collator cl);

    /**
     * Method to perform a general swap, i.e. between xs[i] and xs[j]
     *
     * @param xs the array of X elements.
     * @param i  the index of the lower of the elements to be swapped.
     * @param j  the index of the higher of the elements to be swapped.
     */
    default void swap(X[] xs, int i, int j)  {
        X x = xs[j];
        xs[j] = xs[i];
        xs[i] = x;
    }

    /**
     * Method to perform a swap, but only if xs[i] and xs[j], i.e. out of order.
     *
     * @param xs the array of elements under consideration
     * @param i  the index of the lower element.
     * @param j  the index of the upper element.
     * @return true if there was an inversion (i.e. the order was wrong and had to be be fixed).
     */
    default boolean swapConditional(X[] xs, int i, int j) {
        final X v = xs[i];
        final X w = xs[j];
        boolean result = v.compareTo(w) > 0;
        if (result) {
            xs[i] = w;
            xs[j] = v;
        }
        return result;
    }

    /**
     * Method to perform a swap, but only if xs[i] and xs[j], i.e. out of order.
     *
     * @param xs the array of elements under consideration
     * @param i  the index of the lower element.
     * @param j  the index of the upper element.
     * @param cl the collator to be used for comparing.
     * @return true if there was an inversion (i.e. the order was wrong and had to be be fixed).
     */
    default boolean swapConditional(X[] xs, int i, int j,Collator cl) {
        final X v = xs[i];
        final X w = xs[j];
        boolean result = cl.compare(v,w) > 0;
        if (result) {
            // CONSIDER invoking swap
            xs[i] = w;
            xs[j] = v;
        }
        return result;
    }

    /**
     * Method to perform a swap, but only if xs[i] and xs[j], i.e. out of order.
     *
     * @param xs the array of elements under consideration
     * @param i  the index of the lower element.
     * @param j  the index of the upper element.
     * @param cl the collator to be used for comparing
     * @return true if there was an inversion (i.e. the order was wrong and had to be be fixed).
     */
    default boolean swapConditional(X[] xs, int i, int j, com.ibm.icu.text.Collator cl) {
        final X v = xs[i];
        final X w = xs[j];
        boolean result = cl.compare(v,w) > 0;
        if (result) {
            // CONSIDER invoking swap
            xs[i] = w;
            xs[j] = v;
        }
        return result;
    }

    /**
     * Method to perform a stable swap, but only if xs[i] is less than xs[i-1], i.e. out of order.
     *
     * @param xs the array of elements under consideration
     * @param i  the index of the upper element.
     * @return true if there was an inversion (i.e. the order was wrong and had to be be fixed).
     */
    default boolean swapStableConditional(X[] xs, int i) {
        final X v = xs[i];
        final X w = xs[i - 1];
        boolean result = v.compareTo(w) < 0;
        if (result) {
            xs[i] = w;
            xs[i - 1] = v;
        }
        return result;
    }

    /**
     * Method to perform a stable swap, but only if xs[i] is less than xs[i-1], i.e. out of order.
     *
     * @param xs the array of elements under consideration
     * @param i  the index of the upper element.
     * @param cl the collator to be used for comparing
     * @return true if there was an inversion (i.e. the order was wrong and had to be be fixed).
     */
    default boolean swapStableConditional(X[] xs, int i,Collator cl) {
        final X v = xs[i];
        final X w = xs[i - 1];
        boolean result = cl.compare(v,w) < 0;
        if (result) {
            xs[i] = w;
            xs[i - 1] = v;
        }
        return result;
    }

    /**
     * Method to perform a stable swap, but only if xs[i] is less than xs[i-1], i.e. out of order.
     *
     * @param xs the array of elements under consideration
     * @param i  the index of the upper element.
     * @param cl the collator to be used for comparing
     * @return true if there was an inversion (i.e. the order was wrong and had to be be fixed).
     */
    default boolean swapStableConditional(X[] xs, int i, com.ibm.icu.text.Collator cl) {
        final X v = xs[i];
        final X w = xs[i - 1];
        boolean result = cl.compare(v,w) < 0;
        if (result) {
            xs[i] = w;
            xs[i - 1] = v;
        }
        return result;
    }

    /**
     * Return true if xs is sorted, i.e. has no inversions.
     *
     * @param xs an array of Xs.
     * @return true if there are no inversions, else false.
     */
    boolean sorted(X[] xs);

    /**
     * Count the number of inversions of this array.
     *
     * @param xs an array of Xs.
     * @return the number of inversions.
     */
    int inversions(X[] xs);

    /**
     * Method to post-process the array xs after sorting.
     *
     * @param xs the array that has been sorted.
     */
    void postProcess(X[] xs);

    default int cutoff() {
        return 7;
    }

    /**
     * Initialize this Helper with the size of the array to be managed.
     *
     * @param n the size to be managed.
     */
    void init(int n);

    /**
     * Method to do any required preProcessing.
     *
     * @param xs the array to be sorted.
     * @return the array after any pre-processing.
     */
    default X[] preProcess(X[] xs) {
        return xs;
    }

    default void registerDepth(int depth) {
    }

    default int maxDepth() {
        return 0;
    }
}
