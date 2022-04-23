package com.tuling.test.jvm._01_loadclass;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _05_测试堆栈 {
    static ThreadPoolExecutor exe = new ThreadPoolExecutor(10, 10, 1, TimeUnit.DAYS, new ArrayBlockingQueue<>(10));
    public static void main(String[] args) {
        testMethod();
    }

    private static void testMethod() {
        RuntimeException test = new RuntimeException("test");
        exe.submit(() -> {
            try {
                throw new RuntimeException("test", test);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
