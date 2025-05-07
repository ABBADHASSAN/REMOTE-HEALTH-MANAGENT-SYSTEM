package com.example.rpms.model;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleFileWriter {
    public static void writeFile( String filename, String data) {


        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(data + "\n");  // \n adds new line
            System.out.println("Successfully wrote to file!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
