package edu.neu.coe.info6205.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private String filepath;

    public FileUtil(String filepath) {
        this.filepath = filepath;
    }

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
}
