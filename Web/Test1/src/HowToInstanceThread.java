/**
 * @Author Li_ZW
 * @Date: 2020/06/14
 * @Time: 20:13
 * @Description
 */
public class HowToInstanceThread {
    static class A extends Thread {
        @Override
        public void run() {
            System.out.println("我是A类");
        }
    }

    static class B implements Runnable {
        @Override
        public void run() {
            System.out.println("我是B类");
        }
    }

    public static void main(String[] args) {
        Thread thread;
        {
            // 1. 直接 new A 类的对象，本身就是一个 Thread 对象
            A a = new A();
            thread = a;

            thread.start();
        }

        {
            // 2. new B 类的对象，是一个 Runnable，作为任务传递给线程对象
            B b = new B();
            // thread = b; 这个不能指向
            thread = new Thread(b);

            thread.start();
        }

        {
            // 3. new A 类的对象，但把该对象，当作 Runnable 使用
            // 因为 Thread 本来就实现了 Runnable
            A a = new A();
            thread = new Thread(a);

            thread.start();
        }
    }
}

