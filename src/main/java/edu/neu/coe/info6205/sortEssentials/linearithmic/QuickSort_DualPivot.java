package edu.neu.coe.info6205.sortEssentials.linearithmic;

import edu.neu.coe.info6205.sortEssentials.Helper;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class QuickSort_DualPivot<X extends Comparable<X>> extends QuickSort<X> {

    public static final String DESCRIPTION = "QuickSort dual pivot";

    public QuickSort_DualPivot(String description, int N) {
        super(description, N);
        setPartitioner(createPartitioner());
    }

    /**
     * Constructor for QuickSort_3way
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public QuickSort_DualPivot(Helper<X> helper) {
        super(helper);
        setPartitioner(createPartitioner());
    }

    /**
     * Constructor for QuickSort_3way
     *
     * @param N the number elements we expect to sort.
     */
    public QuickSort_DualPivot(int N) {
        this(DESCRIPTION, N);
    }

    @Override
    public Partitioner<X> createPartitioner() {
        return new Partitioner_DualPivot(getHelper());
    }


    public class Partitioner_DualPivot implements Partitioner<X> {

        public Partitioner_DualPivot(Helper<X> helper) {
            this.helper = helper;
        }

        /**
         * Method to partition the given partition into smaller partitions.
         *
         * @param partition the partition to divide up.
         * @return an array of partitions, whose length depends on the sorting method being used.
         */
        public List<Partition<X>> partition(Partition<X> partition) {
            final X[] xs = partition.xs;
            final int lo = partition.from;
            final int hi = partition.to - 1;
            helper.swapConditional(xs, lo, hi);
            int lt = lo + 1;
            int gt = hi - 1;
            int i = lt;
           {
                while (i <= gt) {
                    X x = xs[i];
                    if (x.compareTo(xs[lo]) < 0) swap(xs, lt++, i++);
                    else if (x.compareTo(xs[hi]) > 0) swap(xs, i, gt--);
                    else i++;
                }
                swap(xs, lo, --lt);
                swap(xs, hi, ++gt);
            }

            List<Partition<X>> partitions = new ArrayList<>();
            partitions.add(new Partition<>(xs, lo, lt));
            partitions.add(new Partition<>(xs, lt + 1, gt));
            partitions.add(new Partition<>(xs, gt + 1, hi + 1));
            return partitions;
        }

        /**
         * Method to partition the given partition into smaller partitions.
         *
         * @param partition the partition to divide up.
         * @return an array of partitions, whose length depends on the sorting method being used.
         */
        public List<Partition<X>> partition(Partition<X> partition, Collator cl) {
            final X[] xs = partition.xs;
            final int lo = partition.from;
            final int hi = partition.to - 1;
            if(cl==null)
                helper.swapConditional(xs, lo, hi);
            else
                helper.swapConditional(xs, lo, hi,cl);
            int lt = lo + 1;
            int gt = hi - 1;
            int i = lt;
            {
                while (i <= gt) {
                    X x = xs[i];
                    if (cl == null) {
                        if (helper.compare(xs, i, lo) < 0) swap(xs, lt++, i++);
                        else if (helper.compare(xs, i, hi) > 0) swap(xs, i, gt--);
                        else i++;
                    } else {
                        if (helper.compare(xs, i, lo, cl) < 0) swap(xs, lt++, i++);
                        else if (helper.compare(xs, i, hi, cl) > 0) swap(xs, i, gt--);
                        else i++;
                    }
                }
                swap(xs, lo, --lt);
                swap(xs, hi, ++gt);
            }

            List<Partition<X>> partitions = new ArrayList<>();
            partitions.add(new Partition<>(xs, lo, lt));
            partitions.add(new Partition<>(xs, lt + 1, gt));
            partitions.add(new Partition<>(xs, gt + 1, hi + 1));
            return partitions;
        }

        @Override
        public List<Partition<X>> partition(Partition<X> partition, com.ibm.icu.text.Collator cl) {
            final X[] xs = partition.xs;
            final int lo = partition.from;
            final int hi = partition.to - 1;
            if(cl==null)
                helper.swapConditional(xs, lo, hi);
            else
                helper.swapConditional(xs, lo, hi,cl);
            int lt = lo + 1;
            int gt = hi - 1;
            int i = lt;
            {
                while (i <= gt) {
                    X x = xs[i];
                    if (cl == null) {
                        if (helper.compare(xs, i, lo) < 0) swap(xs, lt++, i++);
                        else if (helper.compare(xs, i, hi) > 0) swap(xs, i, gt--);
                        else i++;
                    } else {
                        if (helper.compare(xs, i, lo, cl) < 0) swap(xs, lt++, i++);
                        else if (helper.compare(xs, i, hi, cl) > 0) swap(xs, i, gt--);
                        else i++;
                    }
                }
                swap(xs, lo, --lt);
                swap(xs, hi, ++gt);
            }

            List<Partition<X>> partitions = new ArrayList<>();
            partitions.add(new Partition<>(xs, lo, lt));
            partitions.add(new Partition<>(xs, lt + 1, gt));
            partitions.add(new Partition<>(xs, gt + 1, hi + 1));
            return partitions;
        }

        private void swap(X[] ys, int i, int j) {
            X temp = ys[i];
            ys[i] = ys[j];
            ys[j] = temp;
        }

        private final Helper<X> helper;
    }
}

