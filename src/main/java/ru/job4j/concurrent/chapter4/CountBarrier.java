package ru.job4j.concurrent.chapter4;

public class CountBarrier {

    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        monitor.notifyAll();
        count++;
    }

    public void await() throws InterruptedException {
        while (count < total) {
            monitor.wait();
        }
        monitor.notifyAll();
    }

}
