package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    public static void showAllFiles (File file) throws IOException {
        if (file.isDirectory()) {
            System.out.println("\n" + file.getCanonicalFile());
            for (String name : Objects.requireNonNull(file.list())) {
                String nestedDirPath = file.getAbsolutePath() + "/" + name;
                File nestedFile = new File(nestedDirPath);

                if (nestedFile.isDirectory()) {
                    showAllFiles(nestedFile);
                } else {
                    System.out.println(name);
                }
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

        showAllFiles(root);

    }
}
