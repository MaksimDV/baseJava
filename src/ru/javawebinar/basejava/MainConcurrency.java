package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {

    private static int count;
    private static final Object LOCK = new Object();
    private static final int THREAD_NUMBER = 10000;
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(() -> System.out.println((Thread.currentThread().getName() + ", " + Thread.currentThread().getState()))).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threadList = new ArrayList<>(THREAD_NUMBER);
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread thread1 = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread1.start();
            threadList.add(thread1);

        }

        threadList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        //Thread.sleep(500);
        System.out.println(count);
    }

    private synchronized void inc(){
//        synchronized (this) {
            count++;
        }
//    }
}
