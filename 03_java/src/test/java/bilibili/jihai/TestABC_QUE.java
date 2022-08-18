package bilibili.jihai;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

public class TestABC_QUE {
public static int num = 0;
public static Object obj = new Object();
public static void main(String[] args) throws IOException, InterruptedException {
    SynchronousQueue<Object> queA = new SynchronousQueue<>();
    SynchronousQueue<Object> queB = new SynchronousQueue<>();
    SynchronousQueue<Object> queC = new SynchronousQueue<>();
    new Thread(() -> printWord(queA, queB, "A")).start();
    new Thread(() -> printWord(queB, queC, "B")).start();
    new Thread(() -> printWord(queC, queA, "C")).start();
    queA.put(obj);
}
private static void printWord(SynchronousQueue<Object> queA, SynchronousQueue<Object> queB, String A) {
    try {
        while (true) {
            queA.take();
            if (num++ < 100) {
                System.out.print(A);
                queB.put(obj);
            } else {
                System.exit(0);
            }
        }
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
}
