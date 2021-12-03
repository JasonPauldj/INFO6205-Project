package edu.neu.coe.info6205.sortEssentials;

import java.text.Collator;
import java.util.*;

/**
 * Class to implement Most significant digit string sort (a radix sort).
 */
public class MSDStringSortCopy {

    /**
     * Sort an array of Strings using MSDStringSort.
     *
     * @param a the array to be sorted.
     */
    public static void sort(String[] a) {
        int n = a.length;
        aux = new String[n];
        sort(a, 0, n, 0,null);
    }

    public static void sort(String[] a,Collator cl) {
        int n = a.length;
        aux = new String[n];
        sort(a, 0, n, 0,cl);
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
//            System.out.println("c1" + Arrays.toString(count));
//            for (int r = 0; r < radix + 1; r++)      // Transform counts to indices.
//                count[r + 1] += count[r];
//            System.out.println("c2"+Arrays.toString(count));
//            for (int i = lo; i < hi; i++)     // Distribute.
//                aux[count[charAt(a[i], d) + 1]++] = a[i];
//            System.out.println("aux" + Arrays.toString(aux));
//            // Copy back.
//            if (hi - lo >= 0) System.arraycopy(aux, 0, a, lo, hi - lo);
//
//            // Recursively sort for each character value.
//            for(int r=0; r < radix;r++){
//                sort(a,lo+count[r],lo+count[r+1],d+1);
//            }
//        }
//    }

//    private static void sort(String[] a, int lo, int hi, int d) {
//        if (hi <= lo + cutoff ) InsertionSortMSD.sort(a, lo, hi, d,null);
//        else {
////            Map<String,Integer> map = new TreeMap<String,Integer>(Collator.getInstance(Locale.ENGLISH));
//            Map<String,Integer> map = new TreeMap<String,Integer>((o1, o2) -> Integer.valueOf(o1) -Integer.valueOf(o2));
//            //            int[] count = new int[radix + 2];        // Compute frequency counts.
//            for (int i = lo; i < hi; i++){
//                int k = charAt(a[i],d)  ;//+2
//                String key = String.valueOf( k);
//                map.putIfAbsent(key,0);
//                map.put(key,map.get(key)+1);
//            }
//            System.out.println(map.toString());
//
////                count[charAt(a[i], d) + 2]++;
////            for (int r = 0; r < radix + 1; r++)      // Transform counts to indices.
////                count[r + 1] += count[r];
//            List<String> keySet = new ArrayList<String>(map.keySet());
//            for(int r =1 ; r <keySet.size() ; r++){
//                map.put(keySet.get(r),map.get(keySet.get(r-1)) + map.get(keySet.get(r)));
//            }
//            System.out.println(map.toString());
////            for (int i = lo; i < hi; i++){ // Distribute.
////                String key = String.valueOf(charAt(a[i], d) + 1);
////                map.putIfAbsent(key,0);
////                map.put(key,map.get(key)+1);
////                aux[map.get(key)]  = a[i];
//////                aux[count[]++] = a[i];
////            }
//            for (int i = lo; i < hi; i++){ // Distribute.
//                String key = String.valueOf(charAt(a[i], d) );
//                aux[map.get(key)-1]  = a[i];
//                map.put(key,map.get(key)-1);
////                aux[count[]++] = a[i];
//            }
//            System.out.println("aux" + Arrays.toString(aux));
//
////                aux[count[charAt(a[i], d) + 1]++] = a[i];
//            // Copy back.
//            if (hi - lo >= 0) System.arraycopy(aux, 0, a, lo, hi - lo);
//
//            // Recursively sort for each character value.
//            for(int r = 0 ; r<keySet.size()-1; r++){
//                sort(a,lo+map.get(keySet.get(r)),lo+map.get(keySet.get(r+1)),d+1);
//            }
////            for(int r=0; r < radix;r++){
////                sort(a,lo+count[r],lo+count[r+1],d+1);
////            }
//        }
//    }


    private static void sort(String[] a, int lo, int hi, int d, Collator cl) {
//        if (hi <= lo + cutoff )
//            InsertionSortMSD.sort(a, lo, hi, d,cl);
//        else
        if(lo>=hi-1)
                return;
            {
//            Map<String,Integer> map = new TreeMap<String,Integer>(Collator.getInstance(Locale.ENGLISH));
            Map<String,Integer> map;
            if(cl==null)
                map = new TreeMap<String,Integer>((o1, o2) -> Integer.valueOf(o1) -Integer.valueOf(o2));
            else
                map = new TreeMap<String,Integer>(cl);
            //            int[] count = new int[radix + 2];        // Compute frequency counts.
            for (int i = lo; i < hi; i++){
                String key = (cl==null)?String.valueOf( charAt(a[i],d)) : strCharAt(a[i],d);
                map.putIfAbsent(key,0);
                map.put(key,map.get(key)+1);
            }
            System.out.println(map.toString());

//                count[charAt(a[i], d) + 2]++;
//            for (int r = 0; r < radix + 1; r++)      // Transform counts to indices.
//                count[r + 1] += count[r];
            List<String> keySet = new ArrayList<String>(map.keySet());
            for(int r =1 ; r <keySet.size() ; r++){
                map.put(keySet.get(r),map.get(keySet.get(r-1)) + map.get(keySet.get(r)));
            }
            System.out.println(map.toString());
//            for (int i = lo; i < hi; i++){ // Distribute.
//                String key = String.valueOf(charAt(a[i], d) + 1);
//                map.putIfAbsent(key,0);
//                map.put(key,map.get(key)+1);
//                aux[map.get(key)]  = a[i];
////                aux[count[]++] = a[i];
//            }
            int carry = (keySet.size() ==1)?map.get(keySet.get(0)):0;
            for (int i = lo; i < hi; i++){ // Distribute.
                String key = (cl==null)?String.valueOf( charAt(a[i],d)) : strCharAt(a[i],d);
//                        String.valueOf(charAt(a[i], d) );
                aux[map.get(key)-1]  = a[i];
                map.put(key,map.get(key)-1);
//                aux[count[]++] = a[i];
            }
            System.out.println("aux" + Arrays.toString(aux));

//                aux[count[charAt(a[i], d) + 1]++] = a[i];
            // Copy back.
            if (hi - lo >= 0) System.arraycopy(aux, 0, a, lo, hi - lo);

            // Recursively sort for each character value.
            if(keySet.size()>1) {
                System.out.println(map);
                if(keySet.get(0)!="-1")
                    sort(a, lo + map.get(keySet.get(0)) , lo + map.get(keySet.get(1)), d + 1, cl);
                for (int r = 1; r < keySet.size() ; r++) {
                    int hiInd = (r== keySet.size() -1) ? hi : lo + map.get(keySet.get(r+1));
                    sort(a, lo + map.get(keySet.get(r)) , hiInd, d + 1, cl);
                }
            }
            else
                sort(a,lo,hi,d+1,cl);
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
    private static final int radix = 256;
    private static final int cutoff = 3;
    private static String[] aux;       // auxiliary array for distribution
}