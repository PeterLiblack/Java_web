import java.util.Scanner;

/**
 * @Author Li_ZW
 * @Date: 2020/06/21
 * @Time: 20:21
 * @Description
 * 5 个生产者，一个消费者
 */

public class MyBlockingQueueMain2 {
    private static final MyBlockingQueue queue = new MyBlockingQueue();

    static class Producer extends Thread {
        Producer(int i) {
            super("生产者-" + i);
        }

        @Override
        public void run() {
            try {
                for (int i = 0; true; i++) {
                    queue.push(i);
                    System.out.println(getName() + ": 放入: " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 预期是，5 个线程加起来一共放 16 个数据之后就会全部阻塞，直到被消费
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Producer(i).start();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            //System.out.print("输入回车，进行消费");
            //scanner.nextLine();
            int i = queue.pop();
            System.out.println("消费者取出了: " + i);
        }
    }
}

