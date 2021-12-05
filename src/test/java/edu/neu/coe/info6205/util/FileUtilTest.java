package edu.neu.coe.info6205.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FileUtilTest {

    @Test
    public void readLinesTest(){
        FileUtil fu= new FileUtil("src/main/RandomString/English/randomstrings.txt");
        String[] input = fu.read(1);

        assertEquals("zdqoombt", input[0]);
    }

    @Test
    public void readFileTest(){
        FileUtil fu= new FileUtil("src/main/RandomString/English/randomstrings.txt");
        String[] input = fu.read();
        assertNotEquals(0,input.length);

    }

}