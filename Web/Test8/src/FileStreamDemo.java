import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author Li_ZW
 * @Date: 2020/06/24
 * @Time: 21:45
 * @Description
 */

public class FileStreamDemo {
    public static void main(String[] args) {
        inputDemo();
    }

    private static void inputDemo() {
        // 构造的方式：
        // 1. 参数是 File 对象
        // 2. 参数是 String 类型的路径

        // 可以转化为 try-with-resource 的形式
        /*
        InputStream is1 = null;
        try {
             is1 = new FileInputStream("测试目录\\a.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is1 != null) {
                    is1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */

        // 以后基本使用这种形式
        // FileInputStream 就是 InputStream 的一个子类
        // InputStream 是一种 输入 字节 流
        // FileInputStream 是一种 输入 字节 来自文件 流
        /*
        try (InputStream is = new FileInputStream("测试目录\\hello.txt")) {
            // 返回数据流中的下一个字节
            // 为什么要返回 int，因为返回值需要返回 -1，这个和真正的数据做区分
            // 通过返回 -1，通知你，已经读到文件结尾了
            // -1 被称为 EOS（End Of Stream）

            // 统计读到的有效字节数
            int count = 0;
            while (true) {
                int b = is.read();
                if (b == -1) {
                    // 代表文件的内容全部读完了
                    // 所以退出循环
                    break;
                }
                count++;
                System.out.printf("%d%n", b);
                System.out.printf("%02x%n", b);
                System.out.printf("%c%n", b);
                System.out.println("===================");
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        try (InputStream is = new FileInputStream("测试目录\\hello.txt")) {
            byte[] buffer = new byte[16];

            int count = 0;      // read 的调用次数
            while (true) {
                // 最多一次读取 8 个字节，不够 8 个也可以，读到 buffer 的 [0, 8)
                int n = is.read(buffer);
                //is.read(buffer, 0, buffer.length);
                System.out.println("n = " + n);
                // n 代表最终读到的字节个
                // 区分： 最多读 8 个 vs 实际读到 n 个
                // 可能出现比要求读的个数少的情况：这种情况不代表一定就是读到 EOS
                // EOS: -1
                count++;
                if (n == -1) {
                    break;
                }

                for (int i = 0; i < n; i++) {
                    int b = buffer[i];

                    System.out.printf("%d%n", b);
                    System.out.printf("%02x%n", b);
                    System.out.printf("%c%n", b);
                    System.out.println("===================");
                }

                // 下面的处理是错误的
                /*
                if (n < 8) {
                    break;
                }
                */
            }

            System.out.println("read() 一共被调用" + count + "次");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
