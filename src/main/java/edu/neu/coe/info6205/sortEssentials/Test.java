package edu.neu.coe.info6205.sortEssentials;


import edu.neu.coe.info6205.util.FileUtil;

import java.text.Collator;
import java.util.Locale;

public class Test {
    public static void main(String [] args){
        Collator cl = Collator.getInstance(Locale.ENGLISH);
        System.out.println(cl.compare("a","-1"));
    }
}
