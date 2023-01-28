package ru.job4j.concurrent.number5threads;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }

    @Override
    public void run() {
        var process = new char[]{'-', '\\', '|', '/'};
        int counter = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                if (counter == process.length) {
                    counter = 0;
                }
                System.out.print("\r Load " + process[counter++]);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
