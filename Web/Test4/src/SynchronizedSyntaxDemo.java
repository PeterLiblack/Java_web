/**
 * @Author Li_ZW
 * @Date: 2020/06/18
 * @Time: 21:46
 * @Description
 */
/**
 * 演示 synchronized 关键字的语法
 */
public class SynchronizedSyntaxDemo {
    /**
     * 修饰方法
     */
    public synchronized void method() {
    }

    public static synchronized void staticMethod() {
    }

    /**
     * 修饰代码块
     */
    public void otherMethod() {
        // 括号里跟着指向对象的引用，引用不能是 null
        // 只要是引用就行，没有任何要求
        Object o = new Object();
        synchronized (o) {
        }

        synchronized (this) {
        }

        // 反射中，指向当前类对象
        synchronized (SynchronizedSyntaxDemo.class) {
        }
    }
}

