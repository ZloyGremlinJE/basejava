package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * gkislin
 * 21.07.2016
 */
public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        outputFilesRecursively(dir);
    }

    public static void outputFilesRecursively(File directory) {
        if (directory.isDirectory()) {
            List<File> fileList = Arrays.asList(directory.listFiles());
            for (File file : fileList) {
                if (file.isFile()) {
                    System.out.println("File name: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("Directory name: " + file.getName());
                    outputFilesRecursively(file);
                }
            }
        }
    }

}
