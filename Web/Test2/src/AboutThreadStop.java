import java.util.Scanner;

/**
 * @Author Li_ZW
 * @Date: 2020/06/15
 * @Time: 22:17
 * @Description
 * 主线程中创建新的线程（B）
 *
 * 主线程负责通知让（B）停止运行
 */

public class AboutThreadStop {
    static class B extends Thread {
        B() {
            super("B");
        }

        @Override
        public void run() {
            Thread t = Thread.currentThread();
            try {
                while (true) {
                    System.out.println("我正在挖煤");
                    Thread.sleep(1000);
                    //boolean interrupted = Thread.interrupted();
                    boolean interrupted = t.isInterrupted();
                    System.out.println("是否有人让我停止运行: " + interrupted);
                    if (interrupted == true) {
                        // 干很多事情
                        break;  // break、return 都可以
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("有人让我停止运行，但因为我正在 sleep，所以我收到了这个异常");
                // 结束后跳出循环了，所以 run 方法正常结束
            }
        }
    }

    public static void main(String[] args) {
        B b = new B();
        b.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("准备通知 B 线程停止运行");
        b.interrupt();  // 通知 B 停止运行
        System.out.println("已经通知 B 线程停止运行");
    }
}

