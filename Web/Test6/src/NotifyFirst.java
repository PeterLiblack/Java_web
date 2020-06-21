/**
 * @Author Li_ZW
 * @Date: 2020/06/21
 * @Time: 20:21
 * @Description
 */
/**
 * notify 没有状态保存的
 * notify 早于 wait 之前，什么都唤不醒
 */
public class NotifyFirst {
    static class SubThread extends Thread {
        @Override
        public void run() {
            try {
                synchronized (this) {
                    // this.wait();
                    wait();
                    System.out.println("子线程被唤醒");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // s 明明是一个局部变量，但还是共享的
        SubThread s = new SubThread();

        // 先调用 notify，然后再让子线程运行，调用wait
        // s 指向的对象，就是子线程代码中的 this 指向的对象
        synchronized (s) {
            s.notify();
        }

        s.start();
    }
}

