/**
 * @Author Li_ZW
 * @Date: 2020/06/18
 * @Time: 21:46
 * @Description
 */
/**
 * 1. 都加锁
 * 2. 同一个对象
 */
public class FixUnsafe {
    private static final long N = 1_0000_0000L;
    private static long v = 0;

    static class Add extends Thread {
        @Override
        public void run() {
            synchronized (Thread.currentThread()) {
                for (long i = 0; i < N; i++) {
                    v++;
                }
            }
        }
    }

    static class Sub extends Thread {
        @Override
        public void run() {
            synchronized (Thread.currentThread()) {
                for (long i = 0; i < N; i++) {
                    v--;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Add a = new Add();
        Sub s = new Sub();

        a.start();
        s.start();

        // 为什么这里需要 join？
        a.join();
        s.join();

        // 修复以上代码，保证这里一定打印 0
        System.out.println(v);
    }
}

