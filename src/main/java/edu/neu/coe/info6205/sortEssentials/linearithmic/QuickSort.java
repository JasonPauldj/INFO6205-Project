package edu.neu.coe.info6205.sortEssentials.linearithmic;

import edu.neu.coe.info6205.sortEssentials.Helper;
import edu.neu.coe.info6205.sortEssentials.SortWithHelper;
import edu.neu.coe.info6205.sortEssentials.elementary.InsertionSort;
import edu.neu.coe.info6205.util.LazyLogger;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collection;

public abstract class QuickSort<X extends Comparable<X>> extends SortWithHelper<X> {

    public QuickSort(String description, int N) {
        super(description, N);
        insertionSort = new InsertionSort<>(getHelper());
    }

    public QuickSort(Helper<X> helper) {
        super(helper);
        insertionSort = new InsertionSort<>(helper);
    }

    /**
     * Method to create a Partitioner.
     *
     * @return a Partitioner of X which is suitable for the quicksort method being used.
     */
    public abstract Partitioner<X> createPartitioner();

    /**
     * Method to set the partitioner.
     * <p>
     * NOTE: it would be much nicer if we could do this immutably but this isn't Scala, it's Java.
     *
     * @param partitioner the partitioner to be used.
     */
    public void setPartitioner(Partitioner<X> partitioner) {
        this.partitioner = partitioner;
    }

    /**
     * Method to sort.
     *
     * @param xs       sort the array xs, returning the sorted result, leaving xs unchanged.
     * @param makeCopy if set to true, we make a copy first and sort that.
     * @return the result (sorted version of xs).
     */
    @Override
    public X[] sort(X[] xs, boolean makeCopy) {
        getHelper().init(xs.length);
        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
        sort(result, 0, result.length, 0);
        return result;
    }


    /**
     * Sort the sub-array xs[from] .. xs[to-1]
     *
     * @param xs    the complete array from which this sub-array derives.
     * @param from  the index of the first element to sort.
     * @param to    the index of the first element not to sort.
     * @param depth the depth of the recursion.
     */
    public void sort(X[] xs, int from, int to, int depth) {
        if (terminator(xs, from, to, depth)) return;
        getHelper().registerDepth(depth);
        Partition<X> partition = createPartition(xs, from, to);
        if (partitioner == null) throw new RuntimeException("partitioner not set");
        Collection<Partition<X>> partitions = partitioner.partition(partition);
        partitions.forEach(p ->
                sort(p.xs, p.from, p.to, depth + 1));
    }

    /**
     * Sort the sub-array xs[from] .. xs[to-1]
     *
     * @param xs   the complete array from which this sub-array derives.
     * @param from the index of the first element to sort.
     * @param to   the index of the first element not to sort.
     */
    @Override
    public void sort(X[] xs, int from, int to) {
        throw new RuntimeException("This sort signature is not used for Quicksort");
    }

    /**
     * Sort the sub-array xs[from] .. xs[to-1]
     *
     * @param xs    the complete array from which this sub-array derives.
     * @param from  the index of the first element to sort.
     * @param to    the index of the first element not to sort.
     * @param cl the java in built collator.
     */
    @Override
    public void sortBuiltInCollator(X[] xs, int from, int to, Collator cl) {
        if (terminator(xs, from, to,cl))
            return;
        Partition<X> partition = createPartition(xs, from, to);
        if (partitioner == null) throw new RuntimeException("partitioner not set");
        Collection<Partition<X>> partitions = partitioner.partition(partition,cl);
        partitions.forEach(p ->{
//                System.out.println(p);
                sortBuiltInCollator(p.xs, p.from, p.to,cl);

        });
    }

    /**
     * Sort the sub-array xs[from] .. xs[to-1]
     *
     * @param xs    the complete array from which this sub-array derives.
     * @param from  the index of the first element to sort.
     * @param to    the index of the first element not to sort.
     * @param cl the IBM collator.
     */
    @Override
    public void sortIBMCollator(X[] xs, int from, int to, com.ibm.icu.text.Collator cl) {
        if (terminator(xs, from, to,cl))
            return;
        Partition<X> partition = createPartition(xs, from, to);
        if (partitioner == null) throw new RuntimeException("partitioner not set");
        Collection<Partition<X>> partitions = partitioner.partition(partition,cl);
        partitions.forEach(p ->{
//            System.out.println(p);
            sortIBMCollator(p.xs, p.from, p.to,cl);

        });
    }



    /**
     * Protected method to determine to terminate the recursion of this quick sort.
     * NOTE that in this implementation, the depth is ignored.
     *
     * @param xs    the complete array from which this sub-array derives.
     * @param from  the index of the first element to sort.
     * @param to    the index of the first element not to sort.
     * @param depth the current depth of the recursion.
     * @return true if there is no further work to be done.
     */
    protected boolean terminator(X[] xs, int from, int to, int depth) {
        @SuppressWarnings("UnnecessaryLocalVariable") int lo = from;
        if (to <= lo + getHelper().cutoff()) {
            insertionSort.sort(xs, from, to);
            return true;
        }
        return false;
    }

    /**
     * Protected method to determine to terminate the recursion of this quick sort.
     * NOTE that in this implementation, the depth is ignored.
     *
     * @param xs    the complete array from which this sub-array derives.
     * @param from  the index of the first element to sort.
     * @param to    the index of the first element not to sort.
     * @return true if there is no further work to be done.
     */
    protected boolean terminator(X[] xs, int from, int to, Collator cl) {
        @SuppressWarnings("UnnecessaryLocalVariable") int lo = from;
        if (to <= lo + getHelper().cutoff()) {
            insertionSort.sortBuiltInCollator(xs, from, to,cl);
            return true;
        }
        return false;
    }

    /**
     * Protected method to determine to terminate the recursion of this quick sort.
     * NOTE that in this implementation, the depth is ignored.
     *
     * @param xs    the complete array from which this sub-array derives.
     * @param from  the index of the first element to sort.
     * @param to    the index of the first element not to sort.
     * @return true if there is no further work to be done.
     */
    protected boolean terminator(X[] xs, int from, int to, com.ibm.icu.text.Collator cl) {
        @SuppressWarnings("UnnecessaryLocalVariable") int lo = from;
        if (to <= lo + getHelper().cutoff()) {
            insertionSort.sortIBMCollator(xs, from, to,cl);
            return true;
        }
        return false;
    }



    public InsertionSort<X> getInsertionSort() {
        return insertionSort;
    }

    /**
     * Create a partition on ys from "from" to "to".
     *
     * @param ys   the array to partition
     * @param from the index of the first element to partition.
     * @param to   the index of the first element NOT to partition.
     * @param <Y>  the underlying type of ys.
     * @return a Partition of Y.
     */
    public static <Y extends Comparable<Y>> Partition<Y> createPartition(Y[] ys, int from, int to) {
        return new Partition<>(ys, from, to);
    }

    public static <Y extends Comparable<Y>> Partition<Y> createPartition(Y[] ys) {
        return createPartition(ys, 0, ys.length);
    }

    private final InsertionSort<X> insertionSort;

    protected Partitioner<X> partitioner;

    final static LazyLogger logger = new LazyLogger(QuickSort.class);
}
