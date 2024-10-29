package ru.javawebinar.basejava;

public class MainDeadLock {

    public static final Object firstLock = new Object();
    public static final Object secondLock = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (firstLock) {
                System.out.println(Thread.currentThread().getName() + " was started");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (secondLock) {
                    System.out.println(Thread.currentThread().getName() + " was finished");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (secondLock) {
                System.out.println(Thread.currentThread().getName() + " was started");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (firstLock) {
                    System.out.println(Thread.currentThread().getName() + " was finished");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
