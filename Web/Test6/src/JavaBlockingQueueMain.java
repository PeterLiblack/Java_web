import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Author Li_ZW
 * @Date: 2020/06/21
 * @Time: 20:20
 * @Description
 */

public class JavaBlockingQueueMain {
    static BlockingQueue<Integer> q1 = new ArrayBlockingQueue<>(16);
    static BlockingQueue<Integer> q2 = new LinkedBlockingQueue<>();
    static BlockingQueue<Integer> q3 = new PriorityBlockingQueue<>();

    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; true; i++) {
                    q1.put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Producer().start();
        }

        while (true) {
            Integer take = q1.take();
            System.out.println(take);
        }
    }
}
