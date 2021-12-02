package edu.neu.coe.info6205.sortEssentials.linearithmic;

import java.text.Collator;
import java.util.List;

public interface Partitioner<X extends Comparable<X>> {

    /**
     * Method to partition the given partition into smaller partitions.
     *
     * @param partition the partition to divide up.
     * @return an array of partitions, whose length depends on the sorting method being used.
     */
    List<Partition<X>> partition(Partition<X> partition);

    /**
     * Method to partition the given partition into smaller partitions.
     *
     * @param partition the partition to divide up.
     * @param cl the inbuilt java collator
     * @return an array of partitions, whose length depends on the sorting method being used.
     */
    List<Partition<X>> partition(Partition<X> partition, Collator cl);

    /**
     * Method to partition the given partition into smaller partitions.
     *
     * @param partition the partition to divide up.
     * @param cl the ibm collator
     * @return an array of partitions, whose length depends on the sorting method being used.
     */
    List<Partition<X>> partition(Partition<X> partition, com.ibm.icu.text.Collator cl);
}
