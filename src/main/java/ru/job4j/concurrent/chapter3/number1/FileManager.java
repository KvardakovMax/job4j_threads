package ru.job4j.concurrent.chapter3.number1;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {

    private final File file;

    public FileManager(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
