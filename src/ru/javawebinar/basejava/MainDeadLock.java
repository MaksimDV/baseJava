package ru.javawebinar.basejava;

public class MainDeadLock {

    public static final Object FIRST_LOCK = new Object();
    public static final Object SECOND_LOCK = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> executeSynchronizedTask(FIRST_LOCK, SECOND_LOCK));
        Thread thread2 = new Thread(() -> executeSynchronizedTask(SECOND_LOCK, FIRST_LOCK));

        thread1.start();
        thread2.start();
    }

    private static void executeSynchronizedTask(Object lock1, Object lock2) {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " was started");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " was finished");
            }
        }
    }
}
