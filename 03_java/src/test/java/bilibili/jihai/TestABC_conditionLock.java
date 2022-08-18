package bilibili.jihai;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestABC_conditionLock {
    public static int num = 0;
    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Condition condA = lock.newCondition();
        Condition condB = lock.newCondition();
        Condition condC = lock.newCondition();

        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    if (num++ < 100) {
                        System.out.print("A");
                    }
                    condB.signal();
                    condA.await();
                    lock.unlock();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    if (num++ < 100) {
                        System.out.print("B");
                    }
                    condC.signal();
                    condB.await();
                    lock.unlock();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    if (num++ < 100) {
                        System.out.print("C");
                    }
                    condA.signal();
                    condC.await();
                    lock.unlock();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
