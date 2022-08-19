package bilibili.jihai;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestABC_countdown {
    public static volatile int count = 100;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                if (count % 3 == 1 && count != 0) {
                    System.out.print("A");
                    count--;
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                if (count % 3 == 0 && count != 0) {
                    System.out.print("B");
                    count--;
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                if (count % 3 == 2 && count != 0) {
                    System.out.print("C");
                    count--;
                }
            }
        }).start();
        while (count != 0) {}
        System.exit(0);
    }
}
