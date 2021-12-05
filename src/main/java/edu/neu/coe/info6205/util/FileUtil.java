package edu.neu.coe.info6205.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtil {

    private String filepath;

    /**
     *
     * @param filepath is the absolute/relative path to the file
     */
    public FileUtil(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads all the lines in a file and returns them as an array.
     * @return
     */
    public String[] read() {
        try(BufferedReader r = new BufferedReader(new FileReader(this.filepath))) {
            List<String>  retStringList = new ArrayList<>();
            String thisLine=r.readLine();
            while (thisLine != null) {
                retStringList.add(thisLine);
                thisLine= r.readLine();
            }
            String [] retStringArray = new String[retStringList.size()];
            return  retStringList.toArray(retStringArray);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads the specified number of lines(i.e. noOfLines) and returns them as an array.
     * @param noOfLines
     * @return
     */
    public String[] read(int noOfLines) {
        int cnt=0;
        try(BufferedReader r = new BufferedReader(new FileReader(this.filepath))) {
            List<String>  retStringList = new ArrayList<>();
            String thisLine=r.readLine();
            while (thisLine != null && cnt < noOfLines ) {
                retStringList.add(thisLine);
                thisLine= r.readLine();
                cnt++;
            }
            String [] retStringArray = new String[retStringList.size()];
            return  retStringList.toArray(retStringArray);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The method is used to append a string to a file.
     * @param s
     */
    public void writeToFile(String s) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(this.filepath,true))) {
            w.write(s);
            w.newLine();
        }
        catch(IOException e) {
            System.out.println("in exception");
            e.printStackTrace();
        }

    }

//    public void writeAsCsv(List<String> lines){
//        try {
//            String result = "";
//            for(String line: lines ){
//                result += line +"\n";
//            }
//            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
//            writer.write(result);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
