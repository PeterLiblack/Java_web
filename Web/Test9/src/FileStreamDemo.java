import java.io.*;

/**
 * @Author Li_ZW
 * @Date: 2020/06/26
 * @Time: 20:21
 * @Description
 */

public class FileStreamDemo {
    public static void main1(String[] args) {
        try (InputStream is = new FileInputStream("测试目录\\hello.txt")) {
            byte[] buf = new byte[1024];
            while (true) {
                int n = is.read(buf);
                if (n == -1) {
                    break;
                }

                // 有效数据保存在 buf[0, n)
                for (int i = 0; i < n; i++) {
                    byte b = buf[i];
                    System.out.printf("%c%n", b);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1. 如果文件不存在，会进行文件的创建（失败的条件等同于平时创建文件失败的条件）
        // 2. 如果文件存在，会进行“覆盖”方式的写入
        try (OutputStream os = new FileOutputStream("测试目录\\world.txt")) {
            // 1. 单字节的写入
            /*
            os.write('H');
            os.write('\r');
            os.write('\n');
            os.write('W');
            */

            // 2. 批量的方式写入
            byte[] buf = new byte[8];
            buf[0] = 'H';
            buf[1] = '\r';
            buf[2] = '\n';
            buf[3] = 'W';

            os.write(buf, 0, 4);
            //os.write(buf); == os.write(buf, 0, buf.length);

            // 3. 无论是哪种方式写入，一定需要做 flush() 操作
            os.flush();     // 强制要求把系统（软件部分 JVM/OS) 中缓冲的数据，刷新到真正的硬件中
            // 为了提升速度，很多Output的类实现中，都会包含缓冲区
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main2(String[] args) throws IOException {
        // 带有中文字符的读写
        try (InputStream is = new FileInputStream("测试目录\\中文.txt")) {
            byte[] buf = new byte[1024];
            int n;

            // 效果和上面完全一样
            // 无法直接根据数据，100% 确定数据的编码形式
            while ((n = is.read(buf)) != -1) {
                /*
                for (int i = 0; i < n; i++) {
                    System.out.printf("|%d|%02x|%n", buf[i], buf[i]);
                }
                */

                // 假设 buf 中读取的中文，没有出现被拆断的形式
                String s = new String(buf, 0, n, "UTF-8");
                System.out.println(s);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 1. 还是需要，先有字节流
        try (InputStream is = new FileInputStream("测试目录\\中文.txt")) {
            // 2. 利用字节流作为构造方法，构造出字符流
            try (Reader reader = new InputStreamReader(is, "gb18030")) {
                // 读取的单位变成了 字符 char
                // 已经完成了字符集解析的工作了

                // 单字符读取 vs 批量读取
                //int c = reader.read();  // -1 代表 EOS

                char[] buf = new char[1024];
                int n;
                while ((n = reader.read(buf)) != -1) {
                    for (int i = 0; i < n; i++) {
                        System.out.println(buf[i]);
                    }
                }
            }
        }

        // 字符集默认按照项目的字符集编码来（utf-8）
        try (Reader reader = new FileReader("测试目录\\中文.txt")) {
            char[] buf = new char[1024];
            int n;
            while ((n = reader.read(buf)) != -1) {
                for (int i = 0; i < n; i++) {
                    System.out.println(buf[i]);
                }
            }

        }
    }
}

