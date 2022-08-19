package bilibili.jihai;

public class TestABC_countdown2 {
    static int i = 1;

    public static void main(String[] args) {

        new Thread(() -> {
            while (i <= 100) {
                if (i % 3 == 1) {
                    System.out.print("A");
                    i++;
                }
            }
        }).start();
        new Thread(() -> {
            while (i <= 100) {
                if (i % 3 == 2) {
                    System.out.print("B");
                    i++;
                }
            }
        }).start();
        new Thread(() -> {
            while (i <= 100) {
                if (i % 3 == 0) {
                    System.out.print("C");
                    i++;
                }
            }
        }).start();
    }

}
