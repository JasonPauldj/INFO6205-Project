package edu.neu.coe.info6205.sortEssentials;

import java.text.Collator;
import java.util.*;

public class LSDStringSortCopy {

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

    /**
     * charAsciiVal method returns ASCII value of particular character in a String.
     *
     * @param str          String input for which ASCII Value need to be found
     * @param charPosition Character position of which ASCII value needs to be found. If character
     *                     doesn't exist then ASCII value of null i.e. 0 is returned
     * @return int Returns ASCII value
     */
    private int charAsciiVal(String str, int charPosition) {
        if (charPosition >= str.length()) {
            return 0;
        }

        return str.charAt(charPosition);
    }

    private String strCharAt(String s, int d) {
        if (d >= s.length()) {
            return String.valueOf(-1);
        }
        return Character.toString(s.charAt(d));
    }

    /**
     * charSort method is implementation of LSD sort algorithm at particular character.
     *
     * @param strArr       It contains an array of String on which LSD char sort needs to be performed
     * @param charPosition This is the character position on which sort would be performed
     * @param from         This is the starting index from which sorting operation will begin
     * @param to           This is the ending index up until which sorting operation will be continued
     */
    private void charSort(String[] strArr, int charPosition, int from, int to) {

        int[] count = new int[ASCII_RANGE + 2];
        String[] result = new String[strArr.length];

        for (int i = from; i <= to; i++) {
            int c = charAsciiVal(strArr[i], charPosition);
            count[c + 2]++;
        }

        // transform counts to indices
        for (int r = 1; r < ASCII_RANGE + 2; r++)
            count[r] += count[r - 1];

        // distribute
        for (int i = from; i <= to; i++) {
            int c = charAsciiVal(strArr[i], charPosition);
            result[count[c + 1]++] = strArr[i];
        }

        // copy back
        if (to + 1 - from >= 0) System.arraycopy(result, 0, strArr, from, to + 1 - from);
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

    /**
     * sort method is implementation of LSD String sort algorithm.
     *
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     * @param from   This is the starting index from which sorting operation will begin
     * @param to     This is the ending index up until which sorting operation will be continued
     */
    public void sort(String[] strArr, int from, int to) {
        aux = new String[strArr.length];
        int maxLength = findMaxLength(strArr);
        for (int i = maxLength - 1; i >= 0; i--)
            charSort(strArr, i, from, to);
    }

    public void sort(String[] strArr, int from, int to, Collator cl) {
        aux = new String[strArr.length];
        int maxLength = findMaxLength(strArr);
        for (int i = maxLength - 1; i >= 0; i--) {
            charSort(strArr, i, from, to, cl);
            int a = 10;
        }

    }

    /**
     * sort method is implementation of LSD String sort algorithm.
     *
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     */
    public void sort(String[] strArr) {
        sort(strArr, 0, strArr.length - 1);
    }

    public void sort(String[] strArr, Collator cl) {
        sort(strArr, 0, strArr.length, cl);
    }
}
