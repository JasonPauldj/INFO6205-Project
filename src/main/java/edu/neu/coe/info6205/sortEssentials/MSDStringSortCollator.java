package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.sortEssentials.InsertionSortMSD;

import java.text.Collator;
import java.util.*;

/**
 * Class to implement Most significant digit string sort (a radix sort).
 */
public class MSDStringSortCollator {

    /**
     * Sort an array of Strings using MSDStringSort.
     * @param a the array to be sorted.
     * @param cl the java inbuilt collator
     */

    public void sort(String[] a, Collator cl) {
        int n = a.length;
        aux = new String[n];
        sort(a, 0, n, 0, cl);
    }

    /**
     * Sort an array of Strings using MSDStringSort.
     * @param a the array to be sorted.
     * @param cl the java ibm collator
     */

    public void sort(String[] a, com.ibm.icu.text.Collator cl) {
        int n = a.length;
        aux = new String[n];
        sort(a, 0, n, 0, cl);
    }

    /**
     * Sort from a[lo] to a[hi] (exclusive), ignoring the first d characters of each String.
     * This method is recursive.
     *
     * @param a  the array to be sorted.
     * @param lo the low index.
     * @param hi the high index (this index is included in the sort)
     * @param d  the number of characters in each String to be skipped.
     * @param cl the java inbuilt collator
     */
    private static void sort(String[] a, int lo, int hi, int d, Collator cl) {
        if (hi <= lo + cutoff)
            InsertionSortMSD.sort(a, lo, hi, d, cl);
        else {
            if (lo >= hi - 1)
                return;

            Map<String, Integer> map;
            if (cl == null)
                map = new TreeMap<>(Comparator.comparingInt(Integer::valueOf));
            else
                map = new TreeMap<>(cl);
            //            int[] count = new int[radix + 2];

            // Compute frequency counts.
            for (int i = lo; i < hi; i++) {
                String key = (cl == null) ? String.valueOf(charAt(a[i], d)) : strCharAt(a[i], d);
                map.putIfAbsent(key, 0);
                map.put(key, map.get(key) + 1);
            }

//            System.out.println(map.toString());

//                count[charAt(a[i], d) + 2]++;
//            for (int r = 0; r < radix + 1; r++)      // Transform counts to indices.
//                count[r + 1] += count[r];
            //Transforming counts to ending indices of each key
            List<String> keySet = new ArrayList<>(map.keySet());

            //UNSTABLE SORT - map contains the end index of the subarray having 'key' at 'd'.
//            for (int r = 1; r < keySet.size(); r++) {
//                map.put(keySet.get(r), map.get(keySet.get(r - 1)) + map.get(keySet.get(r)));
//            }

            //STABLE SORT - map contains the start index of the subarray having 'key' at 'd'.
            int prevKeyCount = map.get(keySet.get(0));
            map.put(keySet.get(0), 0);
            for (int r = 1; r < keySet.size(); r++) {
                int cnt = map.get(keySet.get(r));
                map.put(keySet.get(r), map.get(keySet.get(r - 1)) + prevKeyCount);
                prevKeyCount = cnt;
            }

//            System.out.println(map.toString());
//            for (int i = lo; i < hi; i++){ // Distribute.
//                String key = String.valueOf(charAt(a[i], d) + 1);
//                map.putIfAbsent(key,0);
//                map.put(key,map.get(key)+1);
//                aux[map.get(key)]  = a[i];
////                aux[count[]++] = a[i];
//            }
            // Distribute.
            for (int i = lo; i < hi; i++) {
                String key = (cl == null) ? String.valueOf(charAt(a[i], d)) : strCharAt(a[i], d);

                //UNSTABLE SORT - populating the array using the end index.
                // aux[map.get(key) - 1] = a[i];
                //map.put(key,map.get(key)-1)

                aux[map.get(key)] = a[i];
                map.put(key, map.get(key) + 1);
            }

//                aux[count[charAt(a[i], d) + 1]++] = a[i];
            // Copy back.
            if (hi - lo >= 0) System.arraycopy(aux, 0, a, lo, hi - lo);

//            System.out.println(map);

            // Recursively sort for each character value.
            // int hiInd;
            if (keySet.get(0) != "-1") {
                ////UNSTABLE SORT.
                // hiInd=(keySet.size()==1) ? hi : lo+map.get(keySet.get(1));
                // sort(a, lo + map.get(keySet.get(0)), hiInd, d + 1, cl);

                sort(a, lo, lo + map.get(keySet.get(0)), d + 1, cl);

            }
            for (int r = 1; r < keySet.size(); r++) {
                //UNSTABlE SORT
                // hiInd = (r == keySet.size() - 1) ? hi : lo + map.get(keySet.get(r + 1));
                // sort(a, lo + map.get(keySet.get(r)), hiInd, d + 1, cl);

                sort(a, lo + map.get(keySet.get(r - 1)), lo + map.get(keySet.get(r)), d + 1, cl);
            }
//            for(int r=0; r < radix;r++){
//                sort(a,lo+count[r],lo+count[r+1],d+1);
//            }
        }
    }

    /**
     * Sort from a[lo] to a[hi] (exclusive), ignoring the first d characters of each String.
     * This method is recursive.
     *
     * @param a  the array to be sorted.
     * @param lo the low index.
     * @param hi the high index (this index is included in the sort)
     * @param d  the number of characters in each String to be skipped.
     * @param cl the java IBM collator
     */
    private static void sort(String[] a, int lo, int hi, int d, com.ibm.icu.text.Collator cl) {
        if (hi <= lo + cutoff)
            InsertionSortMSD.sort(a, lo, hi, d, cl);
        else {
            if (lo >= hi - 1)
                return;

            Map<String, Integer> map;
            map = new TreeMap<>(cl);

            // Compute frequency counts.
            for (int i = lo; i < hi; i++) {
                String key = (cl == null) ? String.valueOf(charAt(a[i], d)) : strCharAt(a[i], d);
                map.putIfAbsent(key, 0);
                map.put(key, map.get(key) + 1);
            }



            List<String> keySet = new ArrayList<>(map.keySet());
            //Transforming counts to ending indices of each key
            //UNSTABLE SORT - map contains the end index of the subarray having 'key' at 'd'.
//            for (int r = 1; r < keySet.size(); r++) {
//                map.put(keySet.get(r), map.get(keySet.get(r - 1)) + map.get(keySet.get(r)));
//            }

            //Transforming counts to ending indices of each key
            //STABLE SORT - map contains the start index of the subarray having 'key' at 'd'.
            int prevKeyCount = map.get(keySet.get(0));
            map.put(keySet.get(0), 0);
            for (int r = 1; r < keySet.size(); r++) {
                int cnt = map.get(keySet.get(r));
                map.put(keySet.get(r), map.get(keySet.get(r - 1)) + prevKeyCount);
                prevKeyCount = cnt;
            }


            // Distribute.
            for (int i = lo; i < hi; i++) {
                String key = (cl == null) ? String.valueOf(charAt(a[i], d)) : strCharAt(a[i], d);

                //UNSTABLE SORT - populating the array using the end index.
                // aux[map.get(key) - 1] = a[i];
                //map.put(key,map.get(key)-1)

                aux[map.get(key)] = a[i];
                map.put(key, map.get(key) + 1);
            }

            // Copy back.
            if (hi - lo >= 0) System.arraycopy(aux, 0, a, lo, hi - lo);

//            System.out.println(map);

            // Recursively sort for each character value.
            // int hiInd;
            if (keySet.get(0) != "-1") {
                ////UNSTABLE SORT.
                // hiInd=(keySet.size()==1) ? hi : lo+map.get(keySet.get(1));
                // sort(a, lo + map.get(keySet.get(0)), hiInd, d + 1, cl);

                sort(a, lo, lo + map.get(keySet.get(0)), d + 1, cl);

            }
            for (int r = 1; r < keySet.size(); r++) {
                //UNSTABlE SORT
                // hiInd = (r == keySet.size() - 1) ? hi : lo + map.get(keySet.get(r + 1));
                // sort(a, lo + map.get(keySet.get(r)), hiInd, d + 1, cl);

                sort(a, lo + map.get(keySet.get(r - 1)), lo + map.get(keySet.get(r)), d + 1, cl);
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

    private static String strCharAt(String s, int d) {
//        if(s.equals("阿冰冰"))
//            System.out.println(s);
        if (d < s.length()) return Character.toString(s.charAt(d));
        else return String.valueOf(-1);
    }

    //private static final int radix = 256;
    private static final int cutoff = 15;
    private static String[] aux;       // auxiliary array for distribution
}