package ru.job4j.concurrent.chapter2.number2;

public class DCLSingleton {

    public static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                inst = new DCLSingleton();
            }
        }
        return inst;
    }

    public DCLSingleton() {
    }

}
