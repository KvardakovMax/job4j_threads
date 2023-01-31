package ru.job4j.concurrent.number3threads;

public class ThreadState {

    public static void main(String[] args) {
        Thread firstThread = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        firstThread.start();
        Thread secondThread = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        secondThread.start();
        while (firstThread.getState() != Thread.State.TERMINATED && secondThread.getState() != Thread.State.TERMINATED) {
            System.out.println("waiting");
        }
        System.out.println(Thread.currentThread().getName() + " - " + firstThread.getState());
    }

}
