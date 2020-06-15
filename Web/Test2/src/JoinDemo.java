import sun.awt.windows.ThemeReader;
/**
 * @Author Li_ZW
 * @Date: 2020/06/15
 * @Time: 22:19
 * @Description
 */

public class JoinDemo {
    static class B extends Thread {
        @Override
        public void run() {
            long r = 0;
            for (long i = 0; i < 100_0000_0000L; i++) {
                r += i;
            }
            System.out.println("B 停止运行");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        B b = new B();
        b.start();

        System.out.println("我不走了，什么时候 b 停止，我才继续");
        b.join();   // main 线程会放弃 CPU，直到 B 停止，才重新进入就绪队列
        System.out.println("B 结束了，我就继续");
    }
}

