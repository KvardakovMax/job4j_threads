package ru.job4j.concurrent.number8thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {

    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        String[] urlPart = url.split("/");
        String fileName = urlPart[urlPart.length - 1];
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int downloadData = 0;
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                if (speed <= downloadData) {
                    long end = System.currentTimeMillis();
                    if (end - start < 1000) {
                        Thread.sleep(1000 - (end - start));
                    }
                    downloadData = 0;
                    start = end;
                }
                downloadData += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0 || args[0] == null || Integer.parseInt(args[1]) <= 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

}
