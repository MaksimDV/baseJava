package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    public static void showAllFilesDeeply(File file, int dirPosition) {
        for (int i = 0; i < dirPosition; i++) {
            System.out.print("   ");
        }

        if (file.isDirectory()) {
            System.out.println("|-- [Dir] " + file.getName());
        } else {
            System.out.println("|-- [File] " + file.getName());
        }

        if (file.isDirectory()) {
            for (File itr : Objects.requireNonNull(file.listFiles())) {
                showAllFilesDeeply(itr, dirPosition + 1);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        String filePath = "/Users/maksimd/dev/basejava/.gitignore";

        File file = new File("./gitignore");
        System.out.println(file.getCanonicalFile());

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.getCanonicalFile());
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

        System.out.println();
        System.out.println("*************");
        System.out.println();
        File root = new File("/Users/maksimd/dev/basejava/src");
        System.out.println(root);

        showAllFilesDeeply(root, 0);

    }
}
