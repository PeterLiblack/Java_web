/**
 * @Author Li_ZW
 * @Date: 2020/06/15
 * @Time: 22:18
 * @Description
 */

public class CreateThreadWithNiMingLei {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("我是子线程");
            }
        });
        t.start();

        while (true) {
            System.out.println("我是 main 线程");
        }
    }
}

