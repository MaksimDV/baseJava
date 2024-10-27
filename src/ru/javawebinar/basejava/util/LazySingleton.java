package ru.javawebinar.basejava.util;

public class LazySingleton {
    static int i;
    volatile private static LazySingleton INSTANCE;

    double sin = Math.sin(13.);

    private LazySingleton() {
    }

    private static class LazySingletonHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return LazySingletonHolder.INSTANCE;
    }
//    public static LazySingleton getINSTANCE() {
//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class) {
//                if (INSTANCE == null) {
//                    i = 10;
//                    INSTANCE = new LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }
}
