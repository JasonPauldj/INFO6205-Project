package edu.neu.coe.info6205.sortEssentials.huskySortUtils;

import edu.neu.coe.info6205.sortEssentials.BaseHelper;
import edu.neu.coe.info6205.sortEssentials.Helper;


import java.text.Collator;
import java.util.function.Consumer;


/**
 * Helper class for sorting methods with special technique of HuskySort.
 * IF you want to count compares and swaps then you need to extend InstrumentedHelper.
 * <p>
 *
 * @param <X> the underlying type (must be Comparable).
 */
public class HuskyHelper<X extends Comparable<X>> implements Helper<X> {

    /**
     * @return the post-sorter.
     */
    public Consumer<X[]> getPostSorter() {
        return postSorter;
    }

    /**
     * @return the value of makeCopy.
     */
    public boolean isMakeCopy() {
        return makeCopy;
    }

    /**
     * CONSIDER eliminating this method
     *
     * @return the array of longs.
     */
    public long[] getLongs() {
        return coding.longs;
    }

    /**
     * @return the Helper.
     */
    public Helper<X> getHelper() {
        return helper;
    }

    /**
     * @param v the first value.
     * @param w the second value.
     * @return true if v is less than w.
     */
    public boolean less(X v, X w) {
        return helper.less(v, w);
    }

    /**
     * @param xs an array of Xs.
     * @return true if xs is sorted.
     */
    public boolean sorted(X[] xs) {
        return helper.sorted(xs);
    }

    /**
     * @param xs an array of Xs.
     * @return the number of inversions in xs.
     */
    public int inversions(X[] xs) {
        return helper.inversions(xs);
    }

    /**
     * @param xs the array that has been sorted.
     */
    public void postProcess(X[] xs) {
        helper.postProcess(xs);
    }

    /**
     * Method to perform a stable swap, but only if xs[i] is less than xs[i-1], i.e. out of order.
     *
     * @param xs the array of elements under consideration
     * @param i  the index of the lower element.
     * @param j  the index of the upper element.
     * @return true if there was an inversion (i.e. the order was wrong and had to be be fixed).
     */
    @Override
    public boolean swapConditional(X[] xs, int i, int j) {
        return helper.swapConditional(xs, i, j);
    }

    /**
     * Method to perform a stable swap, but only if xs[i] is less than xs[i-1], i.e. out of order.
     *
     * @param xs the array of elements under consideration
     * @param i  the index of the upper element.
     * @return true if there was an inversion (i.e. the order was wrong and had to be be fixed).
     */
    @Override
    public boolean swapStableConditional(X[] xs, int i) {
        return helper.swapStableConditional(xs, i);
    }


    /**
     * Method to do any required preProcessing.
     *
     * @param xs the array to be sorted.
     * @return the array after any pre-processing.
     */
    @Override
    public X[] preProcess(X[] xs) {
        return helper.preProcess(xs);
    }


    /**
     * Method to register the current recursion depth.
     *
     * @param depth the depth.
     */
    @Override
    public void registerDepth(int depth) {
        helper.registerDepth(depth);
    }

    /**
     * Get the maximum depth so far registered.
     *
     * @return the maximum recursion depth.
     */
    @Override
    public int maxDepth() {
        return helper.maxDepth();
    }

    /*
     * @param clazz the class of X.
     * @param f     a function which takes a Random and generates a random value of X.
     * @return an array of randomly chosen X values.
     */
//    public X[] random(Class<X> clazz, Function<Random, X> f) {
//        return helper.random(clazz, f);
//    }

    /**
     * @return the description.
     */
    public String getDescription() {
        return helper.getDescription();
    }

    /**
     * @return the number of elements.
     */
    public int getN() {
        return helper.getN();
    }




    /**
     * @param xs the array.
     * @param i  one of the indices.
     * @param j  the other index.
     * @return the result of comparing xs[i] with xs[j].
     */
    public int compare(X[] xs, int i, int j) {
        return helper.compare(xs, i, j);
    }

    @Override
    public int compare(X[] xs, int i, int j, Collator cl) {
        return cl.compare(xs[i],xs[j]);
    }

    @Override
    public int compare(X[] xs, int i, int j, com.ibm.icu.text.Collator cl) {
        return cl.compare(xs[i],xs[j]);
    }

    /**
     * @param v the first value.
     * @param w the second value.
     * @return The result of comparing v with w.
     */
    public int compare(X v, X w) {
        return helper.compare(v, w);
    }

    @Override
    public int compare(X v, X w, Collator cl) {
        return helper.compare(v,w,cl);
    }

    @Override
    public int compare(X v, X w, com.ibm.icu.text.Collator cl) {
        return helper.compare(v,w,cl);
    }

    // CONSIDER having a method less which compares the longs rather than having direct access to the longs array in sub-classes
    public void swap(X[] xs, int i, int j) {
        long[] longs = coding.longs;
        // Swap longs
        long temp1 = longs[i];
        longs[i] = longs[j];
        longs[j] = temp1;
        // CONSIDER incrementing the swaps here since we are in fact doing two swaps.
        helper.swap(xs, i, j);
    }

    /**
     * Method to perform a stable swap, i.e. between xs[i] and xs[i-1]
     *
     * @param xs the array of Y elements.
     * @param i  the index of the higher of the adjacent elements to be swapped.
     */
    public void swapStable(X[] xs, int i) {
        helper.swapStableConditional(xs, i);
    }

    /**
     * Method to perform a stable swap, i.e. between xs[i] and xs[i-1] using inbuilt collator.
     *
     * @param xs the array of Y elements.
     * @param i  the index of the higher of the adjacent elements to be swapped.
     */
    public void swapStable(X[] xs, int i, Collator cl) {
        helper.swapStableConditional(xs, i,cl);
    }

    /**
     * Method to perform a stable swap, i.e. between xs[i] and xs[i-1] using IBM collator
     *
     * @param xs the array of Y elements.
     * @param i  the index of the higher of the adjacent elements to be swapped.
     */
    public void swapStable(X[] xs, int i, com.ibm.icu.text.Collator cl) {
        helper.swapStableConditional(xs, i,cl);
    }


    /**
     * Copy the element at source[j] into target[i]
     *
     * @param source the source array.
     * @param i      the target index.
     * @param target the target array.
     * @param j      the source index.
     */
    @Override
    public void copy(X[] source, int i, X[] target, int j) {
        helper.copy(source, i, target, j);
    }

    /**
     * @return the Husky coder.
     */
    public HuskyCoder<X> getCoder() {
        return coder;
    }

    /**
     * @param n the size to be managed.
     */
    public void init(int n) {
        helper.init(n);
    }

    /**
     *
     * @param array the array from which we build a long array by encoding.
     */
    public void doCoding(X[] array) {
        coding = coder.huskyEncode(array);
    }

    public Coding getCoding() {
        return coding;
    }

    /**
     * Constructor to create a HuskyHelper
     *
     * @param helper     the Helper.
     * @param coder      the coder to be used.
     * @param postSorter the postSorter Consumer function.
     * @param makeCopy   explicit setting of the makeCopy value used in sort(Y[] xs)
     */
    public HuskyHelper(Helper<X> helper, HuskyCoder<X> coder, Consumer<X[]> postSorter, boolean makeCopy) {
        this.helper = helper;
        this.coder = coder;
        this.postSorter = postSorter;
        this.makeCopy = makeCopy;
    }

    /**
     * Constructor to create an uninstrumented Husky Helper with explicit seed.
     * <p>
     * NOTE used by unit tests only.
     *
     * @param description the description of this Helper (for humans).
     * @param n           the number of elements expected to be sorted. The field n is mutable so can be set after the constructor.
     * @param seed        the seed for the random number generator
     * @param makeCopy    explicit setting of the makeCopy value used in sort(Y[] xs)
     */
    public HuskyHelper(String description, int n, HuskyCoder<X> coder, Consumer<X[]> postSorter, long seed, boolean makeCopy) {
        this(new BaseHelper<>(description, n, seed), coder, postSorter, makeCopy);
    }

    /**
     * Constructor to create an uninstrumented Husky Helper with random seed.
     * <p>
     * NOTE used by unit tests only.
     *
     * @param description the description of this Helper (for humans).
     * @param n           the number of elements expected to be sorted. The field n is mutable so can be set after the constructor.
     */
    public HuskyHelper(String description, int n, HuskyCoder<X> coder, Consumer<X[]> postSorter) {
        this(description, n, coder, postSorter, System.currentTimeMillis(), false);
    }

    protected final Helper<X> helper;
    private final HuskyCoder<X> coder;
    private final Consumer<X[]> postSorter;
    private final boolean makeCopy;
    private Coding coding;
}
