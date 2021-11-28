package edu.neu.coe.info6205.sortWithOutConfig.helper;

import edu.neu.coe.info6205.sortWithOutConfig.HelperFactory;
import edu.neu.coe.info6205.sortWithOutConfig.Sort;


/**
 * Base class for Sort with a HelperWithOutConfig.
 * <p>
 * CONSIDER extending GenericSortWithGenericHelper
 *
 * @param <X> underlying type which extends Comparable.
 */
public abstract class SortWithHelper<X extends Comparable<X>> implements Sort<X> {

    public SortWithHelper(Helper<X> Helper) {
        this.Helper = Helper;
    }


    public SortWithHelper(String description, int N) {
        this(HelperFactory.create(description, N));
        closeHelper = true;
    }

    /**
     * Get the HelperWithOutConfig associated with this Sort.
     *
     * @return the HelperWithOutConfig
     */
    public Helper<X> getHelper() {
        return Helper;
    }

    /**
     * Perform initializing step for this Sort.
     *
     * @param n the number of elements to be sorted.
     */
    @Override
    public void init(int n) {
        getHelper().init(n);
    }

    /**
     * Perform pre-processing step for this Sort.
     *
     * @param xs the elements to be pre-processed.
     */
    @Override
    public X[] preProcess(X[] xs) {
        return Helper.preProcess(xs);
    }

    /**
     * Method to post-process an array after sorting.
     * <p>
     * In this implementation, we delegate the post-processing to the HelperWithOutConfig.
     *
     * @param xs the array to be post-processed.
     */
    public void postProcess(X[] xs) {
        Helper.postProcess(xs);
    }

    @Override
    public String toString() {
        return Helper.toString();
    }

    public void close() {
        if (closeHelper) Helper.close();
    }

    private final Helper<X> Helper;
    protected boolean closeHelper = false;

}
