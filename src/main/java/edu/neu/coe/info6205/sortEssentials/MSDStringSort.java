package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.sortWithOutConfig.InsertionSortMSD;

import java.text.Collator;
import java.util.*;

/**
 * Class to implement Most significant digit string sort (a radix sort).
 */
public class MSDStringSort {

    /**
     * Sort an array of Strings using MSDStringSort.
     *
     * @param a the array to be sorted.
     */
    public static void sort(String[] a) {
        int n = a.length;
        aux = new String[n];
        sort(a, 0, n, 0);
    }

    /**
     * Sort from a[lo] to a[hi] (exclusive), ignoring the first d characters of each String.
     * This method is recursive.
     *
     * @param a the array to be sorted.
     * @param lo the low index.
     * @param hi the high index (this index is included in the sort)
     * @param d the number of characters in each String to be skipped.
     */
//    private static void sort(String[] a, int lo, int hi, int d) {
//        if (hi <= lo + cutoff ) InsertionSortMSD.sort(a, lo, hi, d);
//        else {
//            int[] count = new int[radix + 2];        // Compute frequency counts.
//            for (int i = lo; i < hi; i++)
//                count[charAt(a[i], d) + 2]++;
//            for (int r = 0; r < radix + 1; r++)      // Transform counts to indices.
//                count[r + 1] += count[r];
//            for (int i = lo; i < hi; i++)     // Distribute.
//                aux[count[charAt(a[i], d) + 1]++] = a[i];
//            // Copy back.
//            if (hi - lo >= 0) System.arraycopy(aux, 0, a, lo, hi - lo);
//
//            // Recursively sort for each character value.
//            for(int r=0; r < radix;r++){
//                sort(a,lo+count[r],lo+count[r+1],d+1);
//            }
//        }
//    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + cutoff ) InsertionSortMSD.sort(a, lo, hi, d);
        else {
//            Map<String,Integer> map = new TreeMap<String,Integer>(Collator.getInstance(Locale.CHINA));
            Map<String,Integer> map = new TreeMap<String,Integer>();
            //            int[] count = new int[radix + 2];        // Compute frequency counts.
            for (int i = lo; i < hi; i++){
                int k = charAt(a[i],d) +2 ;//+2
                String key = String.valueOf( k);
                map.putIfAbsent(key,0);
                map.put(key,map.get(key)+1);
            }

//                count[charAt(a[i], d) + 2]++;
//            for (int r = 0; r < radix + 1; r++)      // Transform counts to indices.
//                count[r + 1] += count[r];
            List<String> keySet = new ArrayList<String>(map.keySet());
            for(int r =1 ; r <keySet.size() ; r++){
                map.put(keySet.get(r),map.get(keySet.get(r-1)) + map.get(keySet.get(r)));
            }
            for (int i = lo; i < hi; i++){ // Distribute.
                String key = String.valueOf(charAt(a[i], d) + 1);
                map.putIfAbsent(key,0);
                map.put(key,map.get(key)+1);
                aux[map.get(key)]  = a[i];
//                aux[count[]++] = a[i];
            }
//                aux[count[charAt(a[i], d) + 1]++] = a[i];
            // Copy back.
            if (hi - lo >= 0) System.arraycopy(aux, 0, a, lo, hi - lo);

            // Recursively sort for each character value.
            for(int r = 0 ; r<keySet.size()-1; r++){
                sort(a,lo+map.get(keySet.get(r)),lo+map.get(keySet.get(r+1)),d+1);
            }
//            for(int r=0; r < radix;r++){
//                sort(a,lo+count[r],lo+count[r+1],d+1);
//            }
        }
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    private static final int radix = 256;
    private static final int cutoff = 15;
    private static String[] aux;       // auxiliary array for distribution
}