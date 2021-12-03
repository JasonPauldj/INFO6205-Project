package edu.neu.coe.info6205.sortEssentials;

import edu.neu.coe.info6205.util.FileUtil;
import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;

public class LSDStringSortCollatorTest {


    @Test
    public void sortRandom() {
        Random random = new Random();
        String[] input = new String[20];
        for( int i = 0 ; i < 20 ; i++){
            int val = random.nextInt(300);
            input[i] = val + "a";
        }
        System.out.println("Input " + Arrays.toString(input));
        String[] expected = Arrays.copyOf(input,input.length);
        Arrays.sort(expected);
        System.out.println("Expected " + Arrays.toString(expected));
        new LSDStringSortCollator().sort(input,Collator.getInstance(Locale.ENGLISH));
        assertArrayEquals(expected, input);
    }

    @Test
    public void sortInBuiltCollatorChineseBasic() {

        String[] input =  "阿兵 阿安 阿冰冰 阿斌 阿滨 阿冰 阿彬".split(" ");
        String[] expected = "阿安 阿彬 阿斌 阿滨 阿兵 阿冰 阿冰冰".split(" ");

        System.out.println(Arrays.toString(input));
        new LSDStringSortCollator().sort(input, Collator.getInstance(Locale.CHINESE));
        System.out.println("Sorted Array " + Arrays.toString(input));
        System.out.println("Expected Array " + Arrays.toString(expected));
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSortInBuiltCollatorEnglish(){
        FileUtil fu= new FileUtil("src/main/RandomString/English/randomstrings.txt");
        String[] input = fu.read(100);

        String[] expected = Arrays.copyOf(input,input.length);
        new LSDStringSortCollator().sort(input,Collator.getInstance(Locale.ENGLISH));
        Arrays.sort(expected);
        assertArrayEquals(expected, input);

    }

    @Test
    public void testSortChineseIBMCollator(){
        FileUtil fu = new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] in = fu.read();
        FileUtil fo = new FileUtil("src/main/SortedString/Chinese/IBMsortedChinese.txt");
        String[] out = fo.read();

        LSDStringSortCollator lsd = new LSDStringSortCollator();
        lsd.sort(in, com.ibm.icu.text.Collator.getInstance(Locale.CHINESE));
        assertArrayEquals(in, out);
    }

    @Test
    public void testSortChineseInBuiltCollator(){
        FileUtil fu= new FileUtil("src/main/RandomString/Chinese/shuffledChinese.txt");
        String[] input = fu.read();
        FileUtil fo = new FileUtil("src/main/SortedString/Chinese/sortedChinese.txt");
        String[] expected = fo.read();

        LSDStringSortCollator lsd = new LSDStringSortCollator();
        lsd.sort(input,Collator.getInstance(Locale.CHINESE));

        assertArrayEquals(expected,input);
    }

}