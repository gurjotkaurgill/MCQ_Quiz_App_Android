package com.example.assignment3;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileManager {
    String fileName = "quizResults.txt";
    void writeResultToFile(Context context, int result){
        try {
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_APPEND);
            fos.write((result+"\n").getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void deleteAllResultsFromFile(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            fos.write(("").getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<Integer> getResultsFromFile(Context context) {
        ArrayList<Integer> list = new ArrayList<>(0);
        File file = new File(context.getFilesDir(),fileName);
        if(file.exists()) {
            FileInputStream fis;
            try {
                fis = context.openFileInput(fileName);
                InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    list.add(Integer.parseInt(line));
                    line = reader.readLine();
                }
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

}
