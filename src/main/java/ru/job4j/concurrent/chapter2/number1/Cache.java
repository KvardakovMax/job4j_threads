package ru.job4j.concurrent.chapter2.number1;

public final class Cache {

    private static Cache cache;

    public synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

}
