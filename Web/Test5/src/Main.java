/**
 * @Author Li_ZW
 * @Date: 2020/06/20
 * @Time: 20:40
 * @Description
 */
public class Main {
    static class SubThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                {
                    SingletonStarve instance = SingletonStarve.getInstance();
                    System.out.println(instance);
                }

                {
                    SingletonLazy instance = SingletonLazy.getInstance();
                    System.out.println(instance);
                }
                // 问，在多线程环境下，也能保证只会构造出唯一的对象来么？
                // 有没有线程安全问题！
                // 没有线程安全问题
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        for (int i = 0; i < 1000; i++) {
            new SubThread().start();
        }


        // 由于 SingletonStarve 构造方法是 private 的
        // 所以，会有语法错误
        // SingletonStarve mistake = new SingletonStarve();

        // 如果要用到该类的对象
        SingletonStarve right = SingletonStarve.getInstance();

        SingletonLazy rightLazy = SingletonLazy.getInstance();

        // 小人途径，走后门构造一个新对象出来
        // 利用反射可以构造
        // 了解即可
        SingletonStarve 强行构造出来的一个对象 = SingletonStarve.class.newInstance();
    }
}

