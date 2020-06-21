import java.util.concurrent.*;

/**
 * @Author Li_ZW
 * @Date: 2020/06/21
 * @Time: 20:20
 * @Description
 */

public class JavaThreadPoolMain {
    static class MyThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "我随便起的名字");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);

        // 创建线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                10,
                10,
                0,
                TimeUnit.SECONDS,
                queue,
                new MyThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; true; i++) {
            // 创建让线程池执行的任务
            Runnable target = new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            // 等同于把任务提交给线程池
            // 线程池内部会选择一个空闲的线程去执行该任务
            System.out.println("提交第 " + i + " 个任务");
            System.out.println(queue.size());
            threadPool.execute(target);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

