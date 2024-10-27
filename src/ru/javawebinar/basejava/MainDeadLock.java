package ru.javawebinar.basejava;

public class MainDeadLock {

    public static Thread thread1;
    public static Thread thread2;


    public static void main(String[] args) {
        thread1 = new Thread(() -> {
            System.out.println("First thread was started");
            thread2.start();
        });

        thread2 = new Thread(() -> {
            System.out.println("Second thread was started");
            thread1.start();
        });


        thread1.start();
        thread2.start();
    }
}
