/**
 * @Author Li_ZW
 * @Date: 2020/06/16
 * @Time: 22:05
 * @Description
 * 两个线程同时打印
 */

public class YieldDemo {
    static class A extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("A");
            }
        }
    }

    static class B extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("B");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        //A a = new A();
        B b = new B();
        //a.start();
        b.start();
    }
}
