package edu.neu.coe.info6205.sortEssentials;

import com.sun.xml.internal.rngom.parse.host.Base;
import edu.neu.coe.info6205.sortEssentials.Helper;
import edu.neu.coe.info6205.sortEssentials.Sort;

/**
 * Base class for Sort with a Helper.
 * <p>
 * CONSIDER extending GenericSortWithGenericHelper
 *
 * @param <X> underlying type which extends Comparable.
 */
public abstract class SortWithHelper<X extends Comparable<X>> implements Sort<X> {

    public SortWithHelper(Helper<X> helper) {
        this.helper = helper;
    }

    public SortWithHelper(String description, int N) {
        this(BaseHelper.getHelper());
    }

    /**
     * Get the Helper associated with this Sort.
     *
     * @return the Helper
     */
    public Helper<X> getHelper() {
        return helper;
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
        return helper.preProcess(xs);
    }

    /**
     * Method to post-process an array after sorting.
     * <p>
     * In this implementation, we delegate the post-processing to the helper.
     *
     * @param xs the array to be post-processed.
     */
    public void postProcess(X[] xs) {
        helper.postProcess(xs);
    }

    @Override
    public String toString() {
        return helper.toString();
    }


    private final Helper<X> helper;


}
