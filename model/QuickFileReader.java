package com.example.rpms.model;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class QuickFileReader {
    public static void read(String FileName) throws IOException {
        try {
            // Read all lines from the file and print them to the console
            Files.lines(Paths.get(FileName)).forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }
}
