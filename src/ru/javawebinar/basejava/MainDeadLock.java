package ru.javawebinar.basejava;

public class MainDeadLock {

    public static final Object FIST_LOCK = new Object();
    public static final Object SECOND_LOCK = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (FIST_LOCK) {
                System.out.println(Thread.currentThread().getName() + " was started");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (SECOND_LOCK) {
                    System.out.println(Thread.currentThread().getName() + " was finished");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (SECOND_LOCK) {
                System.out.println(Thread.currentThread().getName() + " was started");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (FIST_LOCK) {
                    System.out.println(Thread.currentThread().getName() + " was finished");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
