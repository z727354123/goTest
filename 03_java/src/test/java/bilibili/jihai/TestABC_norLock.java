package bilibili.jihai;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

public class TestABC_norLock {
    public static int num = 0;
    public static Object obj = new Object();

    public static void main(String[] args) throws IOException, InterruptedException {
        new Thread(() -> {
            try {
                while (true) {
                    synchronized (obj) {
                        if (num % 3 == 0) {
                            System.out.print("A");
                            num++;
                            if (num >= 100) {
                                System.exit(0);
                                return;
                            }
                        }
                        obj.notifyAll();
                        obj.wait();
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                while (true) {
                    synchronized (obj) {
                        if (num % 3 == 1) {
                            System.out.print("B");
                            num++;
                            if (num >= 100) {
                                System.exit(0);
                                return;
                            }
                        }
                        obj.notifyAll();
                        obj.wait();
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                while (true) {
                    synchronized (obj) {
                        if (num % 3 == 2) {
                            System.out.print("C");
                            num++;
                            if (num >= 100) {
                                System.exit(0);
                                return;
                            }
                        }
                        obj.notifyAll();
                        obj.wait();
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
