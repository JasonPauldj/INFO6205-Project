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

    public void writeAsCsv(List<String> lines){
        try {
            String result = "";
            for(String line: lines ){
                result += line +"\n";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
