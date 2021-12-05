package edu.neu.coe.info6205.sortEssentials;

import java.text.Collator;
import java.util.*;

public class LSDStringSortCollator {

    private final int ASCII_RANGE = 256;
    private static String[] aux;

    /**
     * findMaxLength method returns maximum length of all available strings in an array
     *
     * @param strArr It contains an array of String from which maximum length needs to be found
     * @return int Returns maximum length value
     */
    private int findMaxLength(String[] strArr) {
        int maxLength = strArr[0].length();
        for (String str : strArr)
            maxLength = Math.max(maxLength, str.length());
        return maxLength;
    }

    private String strCharAt(String s, int d) {
        if (d >= s.length()) {
            return String.valueOf(-1);
        }
        return Character.toString(s.charAt(d));
    }


    private void charSort(String[] strArr, int charPosition, int from, int to, Collator cl) {

        Map<String, Integer> charMap;
        if (cl == null)
            charMap = new TreeMap<>(Comparator.comparingInt(Integer::valueOf));
        else
            charMap = new TreeMap<>(cl);

        //compute frequency counts
        for (int i = from; i < to; i++) {
            String key = strCharAt(strArr[i], charPosition);
            charMap.putIfAbsent(key, 0);
            charMap.put(key, charMap.get(key) + 1);
        }

        //System.out.println(charMap);

        List<String> keySet = new ArrayList<>(charMap.keySet());

        //USING STARTINDICES ARRAY
        // int [] startindIndices = new int[keySet.size()+1];
//        for (int r = 1; r < keySet.size() ; r++) {
//            charMap.put(keySet.get(r), charMap.get(keySet.get(r - 1)) + charMap.get(keySet.get(r)));
//        }

        //Transform counts to indices.
        int prevKeyCount = charMap.get(keySet.get(0));
        charMap.put(keySet.get(0), 0);
        for (int r = 1; r < keySet.size(); r++) {
            int cnt = charMap.get(keySet.get(r));
            charMap.put(keySet.get(r), charMap.get(keySet.get(r - 1)) + prevKeyCount);
            prevKeyCount = cnt;
        }

        //USING STARTINDICES ARRAY
//        for (int r = 1; r < startindIndices.length ; r++) {
//            startindIndices[r] += (startindIndices[r - 1] + charMap.get(keySet.get(r - 1)));
//            charMap.put(keySet.get(r-1),r-1);
//        }

        //distribute
        for (int i = from; i < to; i++) {
            String key = strCharAt(strArr[i], charPosition);

            //USING STARTINDICES ARRAY
            //  aux[startindIndices[charMap.get(key)]] = strArr[i];
            //startindIndices[charMap.get(key)]++;

            aux[charMap.get(key)] = strArr[i];
            charMap.put(key, charMap.get(key) + 1);
            //charMap.put(key, charMap.get(key) + 1);
        }


        if (to - from >= 0) System.arraycopy(aux, 0, strArr, 0, to);

    }

    private void charSort(String[] strArr, int charPosition, int from, int to, com.ibm.icu.text.Collator cl) {

        Map<String, Integer> charMap;
        if (cl == null)
            charMap = new TreeMap<>(Comparator.comparingInt(Integer::valueOf));
        else
            charMap = new TreeMap<>(cl);

        //compute frequency counts
        for (int i = from; i < to; i++) {
            String key = strCharAt(strArr[i], charPosition);
            charMap.putIfAbsent(key, 0);
            charMap.put(key, charMap.get(key) + 1);
        }

        //System.out.println(charMap);

        List<String> keySet = new ArrayList<>(charMap.keySet());

        //USING STARTINDICES ARRAY
        // int [] startindIndices = new int[keySet.size()+1];
//        for (int r = 1; r < keySet.size() ; r++) {
//            charMap.put(keySet.get(r), charMap.get(keySet.get(r - 1)) + charMap.get(keySet.get(r)));
//        }

        //Transform counts to indices.
        int prevKeyCount = charMap.get(keySet.get(0));
        charMap.put(keySet.get(0), 0);
        for (int r = 1; r < keySet.size(); r++) {
            int cnt = charMap.get(keySet.get(r));
            charMap.put(keySet.get(r), charMap.get(keySet.get(r - 1)) + prevKeyCount);
            prevKeyCount = cnt;
        }

        //USING STARTINDICES ARRAY
//        for (int r = 1; r < startindIndices.length ; r++) {
//            startindIndices[r] += (startindIndices[r - 1] + charMap.get(keySet.get(r - 1)));
//            charMap.put(keySet.get(r-1),r-1);
//        }

        //distribute
        for (int i = from; i < to; i++) {
            String key = strCharAt(strArr[i], charPosition);

            //USING STARTINDICES ARRAY
            //  aux[startindIndices[charMap.get(key)]] = strArr[i];
            //startindIndices[charMap.get(key)]++;

            aux[charMap.get(key)] = strArr[i];
            charMap.put(key, charMap.get(key) + 1);
            //charMap.put(key, charMap.get(key) + 1);
        }


        if (to - from >= 0) System.arraycopy(aux, 0, strArr, 0, to);

    }


    public void sort(String[] strArr, int from, int to, Collator cl) {
        aux = new String[strArr.length];
        int maxLength = findMaxLength(strArr);
        for (int i = maxLength - 1; i >= 0; i--) {
            charSort(strArr, i, from, to, cl);
            int a = 10;
        }

    }

    public void sort(String[] strArr, int from, int to, com.ibm.icu.text.Collator cl) {
        aux = new String[strArr.length];
        int maxLength = findMaxLength(strArr);
        for (int i = maxLength - 1; i >= 0; i--) {
            charSort(strArr, i, from, to, cl);
            int a = 10;
        }

    }

    /**
     * sort method is implementation of LSD String sort algorithm.
     * @param cl the java inubilt collator
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     */
    public void sort(String[] strArr, Collator cl) {
        sort(strArr, 0, strArr.length, cl);
    }

    /**
     *
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     * @param cl the java IBM collator
     */
    public void sort(String[] strArr, com.ibm.icu.text.Collator cl) {
        sort(strArr, 0, strArr.length, cl);
    }
}
