package ru.job4j.concurrent.number4threads;

public class Wget {

    public static void main(String[] args) {
        Thread firstThread = new Thread(
                () -> {
                    for (int i = 0; i <= 100; i++) {
                        try {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + i + "%");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        firstThread.start();
    }

}
