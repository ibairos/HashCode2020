package util;


import model.Library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {


    public static Scanner readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        return new Scanner(file);
    }

    public static void writeOutputFile(ArrayList<Library> libraries, String outputPath) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(outputPath);

            fileWriter.write(libraries.size() + "\n");

            for (Library l : libraries) {
                fileWriter.write(l.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
